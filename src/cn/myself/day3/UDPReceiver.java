package cn.myself.day3;

import java.io.IOException;
import java.net.*;

/**
 * Created by WY on 2017/11/14.
 */
public class UDPReceiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket( 8888 );

        byte[] buf = new byte[1024];

        DatagramPacket packet = new DatagramPacket( buf,buf.length );

        while (true){
            socket.receive( packet );

            int dataLen = packet.getLength() ;
            String str = new String( buf,0,dataLen );
            System.out.println("收到了:"+ str);
        }

    }
}
