package cn.myself.day1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2017/11/12.
 */
class DeadLockApp {
    public static void main(String[] args) {
        Pool pool = new Pool();

        Productor p1 = new Productor("生产者1",pool);
        p1.setName("p1");

        Consumer c1 = new Consumer("消费者",pool);
        c1.setName("c1");
        Consumer c2 = new Consumer("消费者",pool);
        c2.setName("c2");

        p1.start();
        c1.start();
        c2.start();
    }
}

//生产者
class Productor extends Thread{
    static int i =0;
    private String name;
    private Pool pool;

    public Productor(String name, Pool pool) {
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
class Consumer extends Thread{
    private String name;
    private Pool pool;

    public Consumer(String name, Pool pool) {
        this.name = name;
        this.pool = pool;
    }

    public void run(){
        while (true){
            pool.remove();
        }
    }
}
class Pool{
    //池
    private List<Integer> list = new ArrayList<Integer>();
    //容量最大值
    private  int MAX = 1;
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
    public int remove(){
        synchronized (this){
            try{
                String name = Thread.currentThread().getName();
                while (list.size() == 0){
                    System.out.println(name +".wait()");
                    this.wait(1000);
                }
                int i = list.remove(0);
                System.out.println(name +"+ ："+i);
                System.out.println(name + ".notify()");
                this.notify();
//                this.notifyAll();
                return  i;
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return -1;
        }
    }

}
/**
 * 备注 上叙 如果this.wait(); this.notify();指向一个等待队列的线程发出通知，会出现都在等待的死状态
 * 如果this.wait(); this.notifyAll();可以解除死状态，向所有等待队列的线程发消息。但是CPU消耗过大
 * this.wait(10000);this.notify();向一个等待队列发消息，但是他是锁死状态，1秒没有响应，可以继续跳出等待。
 * 在wait状态会释放synchronized，跳出等待，会继续synchronized。
 *
 * 注意：synchronized解决了每次只能访问一个的问题，但是不能解决生产和消费出现溢出问题。使用wait和notify可依据我解决溢出问题。
 * */