package cn.myself.day1;

import java.util.ArrayList;

/**
 * Created by WY on 2017/11/11.
 */
public class ScalerApp {
    public static void main(String[] args) {
        TicketPool pool = new TicketPool();
        Saler s1 = new Saler("Bob",pool);
        Saler s2 = new Saler("tom",pool);

        s1.start();
        s2.start();


    }

}
