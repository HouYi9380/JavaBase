package cn.myself.day1;

/**售票员
 * Created by WY on 2017/11/11.
 */
public class Saler extends Thread {
    private  String name;
    private  TicketPool pool;
    public Saler(String name,TicketPool pool){
        super();
        this.name =name;
        this.pool =pool;
    }

    @Override
    public void run() {
        while (true){
            int tno =pool.getTickNo();
            if(tno ==0){
                return;
            }

            System.out.println(name +":"+tno);
        }
    }
}
