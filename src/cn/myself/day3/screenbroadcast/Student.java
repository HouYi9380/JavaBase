package cn.myself.day3.screenbroadcast;

/**udp接收方
 * Created by WY on 2017/11/14.
 */
public class Student {
    public static void main(String[] args) {
        //1,接受数据
        StudentUI ui = new StudentUI();
        new Receiver(ui).start();

        //2.组装数据
    }
}
