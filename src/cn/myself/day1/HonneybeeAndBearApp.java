package cn.myself.day1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2017/11/12.
 */
class  HonneybeeAndBearApp{
    public static void main(String[] args) {
        HenneyPotPool pool = new HenneyPotPool();

        for(int i =0;i<100;i++) {
            HonenyThread p1 = new HonenyThread("蜜蜂", pool);
            p1.setName("henneybee:"+i);
            p1.start();
        }

        BearThread c1 = new BearThread("熊熊",pool);
        c1.setName("bear:0");
        BearThread c2 = new BearThread("熊熊",pool);
        c2.setName("bear:1");

        c1.start();
        c2.start();
    }
}

//生产者，蜜蜂
class  HonenyThread extends Thread{
    static int i =0;
    private String name;
    private HenneyPotPool pool;

    public HonenyThread(String name, HenneyPotPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while (true){
            pool.add(i++);
        }
    }
}
//消费者
class BearThread extends Thread{
    private String name;
    private HenneyPotPool pool;

    public BearThread(String name, HenneyPotPool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while (true){
            pool.remove();
        }
    }
}
class HenneyPotPool{
    //池
    private List<Integer> list = new ArrayList<Integer>();
    //容量最大值
    private  int MAX = 1000;
    //添加元素
    public void add(int n){
        synchronized (this){
            try{
                String name = Thread.currentThread().getName();
                while (list.size() == MAX){
                    System.out.println(name +".wait()");
                    this.wait(1000);
                }
                list.add(n);
                System.out.println(name +"+ ："+n);
                System.out.println(name + ".notify()");
                this.notify();
//                this.notifyAll();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    //删除元素
    public void remove(){
        synchronized (this){
            try{
                String name = Thread.currentThread().getName();
                while (list.size() <20){
                    System.out.println(name +".wait()");
                    this.wait(1000);
                }
                for(int i =0;i<20;i++) {
                    list.remove(0);
                }
                System.out.println(name +"+ ："+20);
                System.out.println(name + ".notify()");
                this.notify();
//                this.notifyAll();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}