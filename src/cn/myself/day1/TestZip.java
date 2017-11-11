package cn.myself.day1;

import org.junit.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by WY on 2017/11/11.
 */
public class TestZip {
    //压缩
    @Test
    public  void zip() throws Exception {

        //文件输出流
        FileOutputStream fos = new FileOutputStream("/Users/zhuxinyi/Desktop/IDEAFile/arch/xxx.xar");
        //压缩流
        ZipOutputStream zos = new ZipOutputStream(fos);

        String[] arr ={"/Users/zhuxinyi/Desktop/IDEAFile/arch/a.jpg","/Users/zhuxinyi/Desktop/IDEAFile/arch/b.pdf","/Users/zhuxinyi/Desktop/IDEAFile/arch/c.txt"};

        for(String s : arr){
            addFile(zos,s);
        }
        zos.close();
        fos.close();
        System.out.println("over!");
    }
    /***
     * 像zos中添加条目，
     */

    public static void addFile(ZipOutputStream zos, String path) throws Exception {
        File f = new File(path);
        //增加条目名称
        zos.putNextEntry(new ZipEntry(f.getName()));
        FileInputStream fis = new FileInputStream(f);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);

        fis.close();

        zos.write(bytes);
        zos.closeEntry();

    }

    //解压缩
    @Test
    public void  unzip() throws Exception {
        FileInputStream fis = new FileInputStream("/Users/zhuxinyi/Desktop/IDEAFile/arch/xxx.xar");
        ZipInputStream zis = new ZipInputStream(fis);

        ZipEntry entry =null ;
        byte[] buf = new byte[1024];
        int len =0 ;
        while ((entry = zis.getNextEntry())!= null){
            String name = entry.getName();
            FileOutputStream fos = new FileOutputStream("/Users/zhuxinyi/Desktop/IDEAFile/arch/unzip/"+name);
            while ((len =zis.read(buf)) != -1){
                fos.write(buf,0,len);
            }

            fos.close();
        }

        zis.close();
        fis.close();
    }
}
