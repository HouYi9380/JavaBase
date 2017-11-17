package cn.myself.day5.gof.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by WY on 2017/11/17.
 */
public class MyServer {

    public static void main(String[] args) throws Exception {
        ByteBuffer buf = ByteBuffer.allocate( 1024 );
        //开启挑选器
        Selector sel = Selector.open();
        //开启ServerSocket通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定端口
        ssc.socket().bind( new InetSocketAddress( "localhost" ,8888) );

        //设置非阻塞
        ssc.configureBlocking( false );

        //在挑选器中注册通道（服务器通道）
        ssc.register( sel, SelectionKey.OP_ACCEPT );
        //SelectableChannel是ServerSocketChannel和SocketChannel的父类
        SelectableChannel sc =null;
        while (true){
            //这里是阻塞的
            sel.select();
            //迭代挑选出key的集合，能进入这个集合的一定是发生了注册的操作发生了触犯触发，被放进这个集合等待处理
            Iterator<SelectionKey> it = sel.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                try {
                    //是可接受的事情
                    if(key.isAcceptable()){
                        System.out.println("服务端连接到客户端");
                        sc = key.channel();
                        SocketChannel sc0 = ((ServerSocketChannel)sc).accept();
                        //设置socketChannel为非阻塞
                        sc0.configureBlocking( false );
                        //注册读事件
                        sc0.register( sel,SelectionKey.OP_READ );
                    }
                    //是可读的
                    if(key.isReadable()){
                        sc = key.channel();
                        SocketChannel sc1 = (SocketChannel) sc;

                        byte[] bytes = "hello world".getBytes();
//                        buf.put( bytes );
                        buf.put( bytes,0,bytes.length );

                        while (sc1.read( buf ) != 0){
                            System.out.println("服务端收到消息并转发："+buf.toString());
                            buf.flip();
                            sc1.write( buf );
                            buf.clear();
                        }

                    }

                }catch (Exception e){
                    sel.keys().remove( key );
                    e.printStackTrace();
                }

            }
            //清除所有挑选出来的key
            sel.selectedKeys().clear();
        }


    }
}
