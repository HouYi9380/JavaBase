package cn.myself.day3.screenbroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.List;

/**屏广发送器
 * Created by WY on 2017/11/14.
 */
public class ScreenBroadCastSender {
    private InetSocketAddress bcAdrr;
    private DatagramSocket socket;

    public ScreenBroadCastSender() throws SocketException {
        socket = new DatagramSocket(9999);
        bcAdrr = new InetSocketAddress( "localhost",8888 );
    }

    public void  send(List<FrameUnit> unitList) throws IOException {
        for(FrameUnit unit : unitList) {
            byte[] packData = popFrameUnit( unit );



            //发送每个FrameUnit
            DatagramPacket packet = new DatagramPacket( packData, packData.length );
            packet.setSocketAddress( bcAdrr );
            socket.send( packet );
        }
    }

    private byte[] popFrameUnit(FrameUnit unit){
        byte[] bytes = new byte[unit.getDataLen()+14];
        //frameid
        System.arraycopy( ScreenUtil.long2Bytes(unit.getFrameid()), 0 , bytes ,0 , 8 );
        //总数及序号
        bytes[8] = (byte) unit.getUnitCont();
        bytes[9] = (byte) unit.getUnitNo();
        //数据长度
        System.arraycopy( ScreenUtil.long2Bytes(unit.getDataLen()), 0 , bytes ,10 , 4 );
        //数据长度
        System.arraycopy(unit.getData(), 0 , bytes ,14 , unit.getDataLen() );

        return bytes;


    }

}
