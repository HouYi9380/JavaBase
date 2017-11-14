package cn.myself.day3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by WY on 2017/11/14.
 */
public class UDPSender {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket( 9999 );
        int i =0;

        while (true){
            //构建数据缓存数据，形成数据报包
            byte[] bytes =("hello world "+ i++ ).getBytes();
            DatagramPacket pack = new DatagramPacket(bytes,bytes.length);
            pack.setSocketAddress( new InetSocketAddress( "localhost",8888 ) );
            socket.send( pack );
            Thread.sleep( 500 );
        }
    }
}
