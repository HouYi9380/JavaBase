package cn.myself.day3.screenbroadcast;

import com.sun.org.apache.regexp.internal.RE;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WY on 2017/11/14.
 */
public class Receiver extends Thread{
    private DatagramSocket socket;
    private DatagramPacket packet;

    private Map<Integer,FrameUnit>  unitMap = new HashMap<Integer,FrameUnit>();
    private StudentUI ui;



    private byte[] buf = new byte[Teacher.FRAME_UNIT_MAX+14];
    public Receiver(StudentUI ui)
    {
        try {
            this.ui =ui;
            socket = new DatagramSocket( 8888 );
            packet = new DatagramPacket(buf,buf.length);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long currendFrameId =0;
        try {
            while (true) {
                socket.receive( packet );
                int len = packet.getLength();
                FrameUnit unit = converData2FrameUnit(buf,0,len);
                long newFrameId = unit.getFrameid();
                if(newFrameId == currendFrameId){
                    unitMap.put( unit.getUnitNo(),unit );

                }
                else if(newFrameId > currendFrameId){
                    currendFrameId = newFrameId;
                    unitMap.clear();
                    unitMap.put( unit.getUnitNo(),unit );

                }
                else{
                    ;//来的晚的帧直接丢掉
                }
                BufferedImage bufferedImage = processFrame();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理一帧数据，处理整个unit集合
     */
    private BufferedImage processFrame() {
        try {
            int unitCont = unitMap.values().iterator().next().getUnitCont();
            if(unitMap.size() == unitCont){
                //可以处理数据了，已经收齐了
                ByteArrayOutputStream baos = new ByteArrayOutputStream(  );

                for (int i=0;i<unitCont;i++){
                    FrameUnit unit = unitMap.get( i );
                    baos.write( unit.getData());
                }
                unitMap.clear();
//                ui.updateIcon( baos.toByteArray() );
                ui.updateIcon( ScreenUtil.unzipData( baos.toByteArray() ) );
                baos.close();
//                return ImageIO.read( new ByteArrayInputStream( baos.toByteArray() ) );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 转换每个FrameUnit
     * @param buf
     * @param i
     * @param len
     * @return
     */
    private FrameUnit converData2FrameUnit(byte[] buf, int i, int len) {
        byte[] bytes = new byte[len];
        System.arraycopy( buf,0,bytes,0,len );

        FrameUnit unit = new FrameUnit();
        unit.setFrameid( ScreenUtil.byte2Long( bytes ) );
        unit.setUnitCont( bytes[8] );
        unit.setUnitNo( bytes[9] );

        unit.setDataLen( ScreenUtil.bytes2Int(bytes,10));

        byte[] data = new byte[len -14];
        System.arraycopy( buf,14,data,0,len-14 );

        unit.setData(data);

        return  unit;
    }

}
