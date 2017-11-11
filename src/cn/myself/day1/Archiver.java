package cn.myself.day1;

import java.io.*;

/**文件归档的设计书写
 * Created by WY on 2017/11/10.
 */
public class Archiver {
    public static void main(String[] args) throws Exception {
        //归档生成到某个地方
        FileOutputStream fos = new FileOutputStream("/Users/zhuxinyi/Desktop/IDEAFile/arch/x.xar") ;
        fos.write(addFile("/Users/zhuxinyi/Desktop/IDEAFile/arch/a.jpg"));
        fos.write(addFile("/Users/zhuxinyi/Desktop/IDEAFile/arch/b.pdf"));
        fos.write(addFile("/Users/zhuxinyi/Desktop/IDEAFile/arch/c.txt"));

        fos.close();

    }

    public static byte[] addFile(String path) throws Exception {
        //获取文件
        File f = new File(path);
        //获取文件的名称
        String fname = f.getName();
        //获取文件名数组
        byte[] fnameBytes = fname.getBytes();
        //文件内容的长度
        int len = (int) f.length();
        //读取文件内容到数组中
          //教你一个内存存放数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(f);
        byte[] buf =new byte[1024];
        int len0 =0;
        while (((len0 = fis.read(buf)) != -1)){
            baos.write(buf, 0 ,len0);
        }
        fis.close();

        byte[] fcontentArr =baos.toByteArray();

        //数据总长度
        int total = 4 + fnameBytes.length +4 +len;
        //初始化总数组
        byte[] bytes = new byte[total];

        //写入文件长度
        byte[] fnameLenArr = Util.int2Bytes(fnameBytes.length);
        System.arraycopy(fnameLenArr,0,bytes,0,4);
        //写入文件名称数据
        System.arraycopy(fnameBytes,0,bytes,4,fnameBytes.length);
        //写入内容长度
        byte[] fcontentLenArr =Util.int2Bytes(fcontentArr.length);
        //写入内容
        System.arraycopy(fcontentLenArr,0,bytes,4+fnameBytes.length,4);
        System.arraycopy(fcontentArr,0,bytes,4+fnameBytes.length+4,fcontentArr.length);

        return  bytes;
    }


}
