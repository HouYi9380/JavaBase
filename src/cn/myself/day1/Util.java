package cn.myself.day1;


/**
 * Created by WY on 2017/11/11.
 */
public class Util {


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
    public static int bytes2Int(byte[] bytes){
        int i0 = bytes[0] ;
        int i1 = (bytes[1] & 0xFF) << 8 ;
        int i2 = (bytes[2] & 0xFF) << 16 ;
        int i3 = (bytes[3] & 0xFF) << 24 ;

        return  i0 | i1 | i2 | i3 ;
    }

}
