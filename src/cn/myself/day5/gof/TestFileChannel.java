package cn.myself.day5.gof;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by WY on 2017/11/15.
 */
public class TestFileChannel {

    private static int bufSize = 512 * 1 ;

    @Test
    public void testAll() {
        copyFileNIO();
        copyFileIO();
    }


    public  void copyFileNIO() {

        try {
            //输入
            FileInputStream fis = new FileInputStream( "/Users/zhuxinyi/Desktop/IDEAFile/arch/b.pdf" );
            //获得文件通道
            FileChannel fcIn = fis.getChannel();

            //输出
            FileOutputStream fos = new FileOutputStream( "/Users/zhuxinyi/Desktop/IDEAFile/arch/1.pdf" );
            FileChannel fcOut = fos.getChannel();

            long start = System.currentTimeMillis() ;
            ByteBuffer buf = ByteBuffer.allocate( bufSize );

            while (fcIn.read( buf ) != -1){
                buf.flip();
                fcOut.write( buf );
                buf.clear();
            }

            fcIn.close();
            fcOut.close();
            System.out.println("nio : " + (System.currentTimeMillis() - start));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void copyFileIO(){
        try {
            FileInputStream fis = new FileInputStream( "/Users/zhuxinyi/Desktop/IDEAFile/arch/b.pdf" );
            FileOutputStream fos = new FileOutputStream( "/Users/zhuxinyi/Desktop/IDEAFile/arch/2.pdf" );

            long start = System.currentTimeMillis();
            byte[] buf = new byte[bufSize];
            int  len = -1;
            while ((len = fis.read(buf)) != -1){

                fos.write( buf,0,len );
            }
            fis.close();
            fos.close();
            System.out.println("io : " + (System.currentTimeMillis() - start));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fileMappingMemory(){
        try {
            File f = new File("/Users/zhuxinyi/Desktop/IDEAFile/a.txt");
            RandomAccessFile raf = new RandomAccessFile(f,"rw");
            MappedByteBuffer buffer = raf.getChannel().map( FileChannel.MapMode.READ_WRITE, 1, 9 );

            System.out.println((char)buffer.get(0));
            System.out.println((char)buffer.get(8));
            System.out.println(buffer.capacity());
            buffer.put(0, (byte)'x');

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试离堆缓冲区
     */
    @Test
    public void testOffHeapBuf(){
        System.out.println("xxx");
        ByteBuffer.allocate(500 * 1024 * 1024);
        ByteBuffer.allocateDirect(500 * 1024 * 1024);
        System.out.println("xxx");
    }

    @Test
    public void testOffHeapBuf2() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024*1024*1024);
        //得到类描述符
        Class clazz = Class.forName( "java.nio.DirectByteBuffer" );
        //通过类描述符查找制定字段的描述
        Field f = clazz.getDeclaredField( "cleaner" );
        //设置可访问属性
        f.setAccessible( true );

        //取得f在buf上的值
        Object cleaner = f.get( buffer );

        Class clazz2 = Class.forName( "sun.misc.Cleaner" );
        Method m = clazz2.getDeclaredMethod( "clean");
        m.invoke( cleaner );

        //非常时刻用反射

        System.out.println("kkkkk");
        System.out.println("kkkkk");
        System.out.println("kkkkk");

        System.out.println(buffer.get(0));
        System.out.println("kkkkk");

    }
}
