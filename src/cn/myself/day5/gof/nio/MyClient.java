package cn.myself.day5.gof.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by WY on 2017/11/17.
 */
public class MyClient {
    public static void main(String[] args) throws Exception {

        ByteBuffer buf = ByteBuffer.allocate( 1024 * 8 );

        //开启一个挑选器
        Selector sel = Selector.open();
        //连接套接字
        SocketChannel sc = SocketChannel.open(new InetSocketAddress( "localhost",8888 ));
//        sc.connect( new InetSocketAddress( "localhost",8888 ) );
        sc.configureBlocking( false );
        sc.register( sel, SelectionKey.OP_READ );

        new Thread(  ){
            @Override
            public void run() {
                int i =0;
                ByteBuffer buf2 =ByteBuffer.allocate( 1024*8 );

                while (true){
                    try {
                        byte[] bytes = "wangyan".getBytes();
                        buf2.put( bytes);
                        buf2.flip();
                        sc.write( buf2 );
                        buf2.clear();
                        Thread.sleep( 5000 );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        while (true){
            sel.select();
            ByteArrayOutputStream bas = new ByteArrayOutputStream(  );
            while (sc.read( buf ) > 0){
                buf.flip();
                bas.write( buf.array(),0,buf.limit() );
                buf.clear();
            }
            System.out.println(new String(bas.toString()));
            bas.close();
            sel.selectedKeys().clear();
        }

//自己试验的代码
//        ByteBuffer buf = ByteBuffer.allocate( 1024 );
//
//        SocketChannel sc = SocketChannel.open( new InetSocketAddress( "localhost",9999 ) );
//        sc.socket().bind( new InetSocketAddress( "localhost",8888 ) );
//
//        byte[] bytes = "你好".getBytes();
//        buf.put( bytes,0,bytes.length );
//
//        buf.flip();
//        sc.write( buf );
//        buf.clear();



    }
}
