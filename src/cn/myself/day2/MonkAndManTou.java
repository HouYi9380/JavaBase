package cn.myself.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Collections.*;

/**
 * Created by WY on 2017/11/13.
 */
class MonkAndManTou {
    public static void main(String[] args) {

        Box box = new Box();

        for(int i =0;i<30;i++){
            Monk m = new Monk(box,"monk:"+i);
            m.start();
        }

        //字符排序数据

        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(new Random().nextInt(5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Singleton.getInstance().getSize() ==30){
                        Singleton.getInstance().sord();
                        return;
                    }

                }
            }
        }.start();
    }
}

/***
 * 定义一个和尚
 */

class Monk extends Thread{
    private  int count = 0;//和尚当前吃的个数
    private  Box box;//箱子
    private String name ;//和尚名字

    public String getMonkName() {
        return name;
    }

    public Monk(Box box, String name) {
        this.box = box;
        this.name = name;
    }

    public void run(){
        while (true){
            int n = box.getMantou(this);
            try {
                Thread.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(n == 0){
                String info = name +"吃了:"+count+"个馒头";
                System.out.println(info);
                Singleton.getInstance().add(info);
                return;
            }
            else if(n ==-1){
               ;
            }
            else {
                count ++;
            }

        }
    }

    public int getCount(){
        return count;
    }
}
class Box {
    private  int sum =100;
    private List<String> list = new ArrayList<String>();

    public synchronized int getMantou(Monk m){
        if(!list.contains(m.getMonkName())){
            list.add(m.getMonkName());
        }

        if(list.size()!=30){

            if(sum >30){
                if(m.getCount()<6) {
                    sum--;
                    return sum;
                }else{
                    return  0;
                }
            }
            else {
                return  -1;
            }
        }
        else {
            if(sum>0){
                if(m.getCount()<6) {
                    sum--;
                    return sum;
                }else{
                    return  0;
                }
            }
            else{
                return 0;
            }
        }


    }
}

/***
 *创建一个单例类来存储数据
 */
class Singleton{
    private static Singleton instance;
    private List<String> list = new ArrayList<String>();
    //隐藏构造器
    private Singleton(){}
    //获得单例对象
    public static Singleton getInstance(){
        if(instance == null){
            instance  = new Singleton();
        }

        return instance;
    }

    public void  add(String s){
        list.add(s);
    }

    public int getSize(){
        return list.size();
    }

    public void sord(){
        System.out.println("___________________________________________");
       Collections.sort( list );
        System.out.println(list);

    }


}