package cn.myself.day1;

import org.junit.Test;

/**
 * Created by WY on 2017/11/11.
 */
public class TestThread {

    class MyThread extends Thread{
        private  String name;

        public MyThread(String name) {
            this.name = name;
        }

        public void  run(){
            for(int i =0;i <= 100;i++){
                System.out.println(name +":"+i);
                yield();
            }
        }
    }
    @Test
    public void  test1(){
        new  MyThread("T1").start();
        new  MyThread("T2").start();

    }
}
