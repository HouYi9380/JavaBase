package cn.myself.day1;

/**票池
 * Created by WY on 2017/11/11.
 */
public class TicketPool {
    private  int tickNo =100;

    /***
     *加同步锁
     *
     * @return
     */
    public synchronized int getTickNo(){
        int tmp =tickNo;
        if(tmp ==0){
            return  0;
        }

        tickNo --;

        return  tmp;

    }
}
