package cn.myself.day3;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

/**
 * Created by WY on 2017/11/14.
 */
public class TestCapture {
    @Test
    public void test1() throws Exception {
        Robot r = new Robot();

        BufferedImage image = r.createScreenCapture( new Rectangle( 0, 0, 1280, 800) );

        ImageIO.write(image,"jpg",new FileOutputStream("/Users/zhuxinyi/Desktop/IDEAFile/arch/1.jpg"));


    }
}
