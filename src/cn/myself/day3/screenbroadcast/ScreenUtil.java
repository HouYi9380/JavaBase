package cn.myself.day3.screenbroadcast;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by WY on 2017/11/14.
 */
public class ScreenUtil {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /***
     * 抓图
     * @return
     */
    public static byte[] captureScreen() throws IOException {

        BufferedImage image = robot.createScreenCapture( new Rectangle( 0,0,1280,800 ) );
        ByteArrayOutputStream baos = new ByteArrayOutputStream(  );
        ImageIO.write( image,"jpg",baos );

        return baos.toByteArray();
    }

    /***
     * 长整型转换为字节数组
     * @param l
     * @return
     */
    public static byte[] long2Bytes(long l){
        byte[] bytes = new byte[8];
        bytes[0] = (byte)l;
        bytes[1] = (byte)(l >> 8);
        bytes[2] = (byte)(l >> 16);
        bytes[3] = (byte)(l >> 24);
        bytes[4] = (byte)(l >> 32);
        bytes[5] = (byte)(l >> 40);
        bytes[6] = (byte)(l >> 48);
        bytes[7] = (byte)(l >> 56);

        return  bytes;
    }

    /***
     * 字节数组转换为长整型
     * @param bytes
     * @return
     */
    public static long byte2Long(byte[] bytes){
        return ((long) (bytes[0] & 0xFF)) |
                ((long) (bytes[1] & 0xFF) << 8 ) |
                ((long) (bytes[2] & 0xFF) << 16 ) |
                ((long) (bytes[3] & 0xFF) << 24 ) |
                ((long) (bytes[4] & 0xFF) << 32 ) |
                ((long) (bytes[5] & 0xFF) << 40 ) |
                ((long) (bytes[6] & 0xFF) << 48 ) |
                ((long) (bytes[7] & 0xFF) << 56 ) ;
    }
    /***
     * 将整数转换为字节数组
     */
    public static byte[] int2Bytes(int i){
        byte[] arr = new byte[4] ;
        arr[0] = (byte)i ;
        arr[1] = (byte)(i >> 8) ;
        arr[2] = (byte)(i >> 16) ;
        arr[3] = (byte)(i >> 24) ;

        return  arr;
    }

    /***
     * 将字节数组转换为整形
     */
    public static int bytes2Int(byte[] bytes , int offset){
        int i0 = bytes[0 + offset];
        int i1 = (bytes[1 + offset] & 0xFF) << 8 ;
        int i2 = (bytes[2 + offset] & 0xFF) << 16 ;
        int i3 = (bytes[3 + offset] & 0xFF) << 24 ;

        return  i0 | i1 | i2 | i3 ;
    }

    /***
     * 返回压缩后的数据
     * @param rawData
     * @return
     */
    public static  byte[] zipData(byte[] rawData){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream( baos );
            zos.putNextEntry( new ZipEntry( "one" ) );
            zos.write(rawData);
            zos.close();
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    /***
     * 解压缩格式
     * @param
     * @return
     */
    public static  byte[] unzipData(byte[] zipData){
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream( zipData );
            ZipInputStream zis = new ZipInputStream( bais );

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            zis.getNextEntry();
            byte[] buf = new byte[1024];
            int len =0;
            while ((len =zis.read( buf )) != -1){
                baos.write( buf,0,len );
            }
            baos.close();
            zis.close();
            bais.close();
            return baos.toByteArray();

        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
