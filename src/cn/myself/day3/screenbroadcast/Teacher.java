package cn.myself.day3.screenbroadcast;

import cn.myself.day1.Util;
import org.omg.CORBA.FREE_MEM;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**udp发送方
 * Created by WY on 2017/11/14.
 */
public class Teacher {
    //定义每一帧数据的最大值
    public final static int FRAME_UNIT_MAX = 60 *1024;
    private static ScreenBroadCastSender tsender;

    static {
        try {
            tsender = new ScreenBroadCastSender();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        while (true){
            sendOneScreenData();
        }
    }

    /***
     * 1,截屏
     * 2，切屏
     * 3，组装UdpPack(时间戳+切屏总数+当前切屏序号+数据长度+数据)
     *   字节数       8       1       1           4       n(60k)
     */
    private static void sendOneScreenData() throws IOException {
        byte[] frame = ScreenUtil.captureScreen();
        frame = ScreenUtil.zipData( frame );//压缩需要调用
        List<FrameUnit> units = splitFrame(frame);

        tsender.send( units );
    }

    /***
     * 截屏方法
     * @param frame
     * @return
     */
    private static List<FrameUnit> splitFrame(byte[] frame) {
        List<FrameUnit> units = new ArrayList<FrameUnit>( );

        int count =0;
        if(frame.length % FRAME_UNIT_MAX ==0){
            count = frame.length / FRAME_UNIT_MAX;
        }
        else{
            count = frame.length /FRAME_UNIT_MAX +1;
        }

        FrameUnit unit = null;
        long frameid = System.currentTimeMillis();
        for(int i =0; i< count ; i++){
            unit = new FrameUnit();
            unit.setFrameid( frameid );
            unit.setUnitCont( count );
            unit.setUnitNo( i );;
            //最后一个
            if(i ==(count -1)){
                if(frame.length % FRAME_UNIT_MAX ==0){
                    unit.setDataLen( FRAME_UNIT_MAX );
                    byte[] data = new byte[FRAME_UNIT_MAX];
                    System.arraycopy( frame,i*FRAME_UNIT_MAX,data,0,FRAME_UNIT_MAX );
                    unit.setData( data );
                }
                else{
                    unit.setDataLen( frame.length % FRAME_UNIT_MAX );
                    byte[] data = new byte[frame.length % FRAME_UNIT_MAX];
                    System.arraycopy( frame,i*FRAME_UNIT_MAX,data,0,frame.length % FRAME_UNIT_MAX );
                    unit.setData( data );
                }
            }
            else {
                unit.setDataLen( FRAME_UNIT_MAX );
                byte[] data = new byte[FRAME_UNIT_MAX];
                System.arraycopy( frame,i*FRAME_UNIT_MAX,data,0,FRAME_UNIT_MAX );
                unit.setData( data );
            }

            units.add( unit );
        }

        return  units;
    }
}
