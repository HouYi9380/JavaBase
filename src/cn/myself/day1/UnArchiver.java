package cn.myself.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2017/11/11.
 */
public class UnArchiver {

    public static void main(String[] args) throws Exception {
        //存储文件内容
        List<FileBean> files = new ArrayList<FileBean>();
        //取得输入流
        FileInputStream fis = new FileInputStream("/Users/zhuxinyi/Desktop/IDEAFile/arch/x.xar");
        FileBean fileBean =null;
        while ((fileBean =readNextFile(fis)) != null){
            files.add(fileBean);
        }
        fis.close();

        //输出文件
        FileOutputStream fos = null;
        for(FileBean fb : files){

            fos = new FileOutputStream("/Users/zhuxinyi/Desktop/IDEAFile/arch/unarch/"+fb.getFileName());
            fos.write(fb.getFileContent());
            fos.close();
        }

        //徐老师这个例子里存在问题，如果归档的文档过大，在解档是出现问题。

    }



    /***
     * 从流中读取一个文件
     */
    public static FileBean readNextFile(FileInputStream fis) throws Exception {
        byte[] bytes4 = new byte[4];
        //读取4个字节
        int res =fis.read(bytes4);
        if(res == -1){
            return  null;
        }
        //文件名长度
        int fnameLen = Util.bytes2Int(bytes4);

        //文件数组及文件名获取
        byte[] fileNamesBytes = new byte[fnameLen];
        fis.read(fileNamesBytes);

        String fname = new String(fileNamesBytes);
        System.out.println(fname);

        //再去4个字节，作为文件内容的长度
        fis.read(bytes4);
        int fconLen = Util.bytes2Int(bytes4);

        byte[] fcontBytes = new byte[fconLen];

        fis.read(fcontBytes);

        return  new FileBean(fname,fcontBytes);

    }
}
