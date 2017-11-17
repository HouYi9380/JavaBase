package cn.myself.day5.gof;

/**饿汉式单例
 * Created by WY on 2017/11/15.
 */
public class GargageBox {
    private static GargageBox instance = new GargageBox();

    public static GargageBox getInstance(){
        return  instance;
    }

    private GargageBox(){

    }
}

///**懒汉式单例1 synchronized可以避免线程开始，但是效率不高
// * Created by WY on 2017/11/15.
// */
//public class GargageBoxLazy {
//    private static GargageBoxLazy instance;
//
//    public synchronized static GargageBoxLazy getInstance(){
//        if(instance == null){
//            instance = new GargageBoxLazy();
//        }
//        return  instance;
//    }
//
//    private GargageBoxLazy(){
//
//    }
//}
///**懒汉式单例2 synchronized这种比较好
// * Created by WY on 2017/11/15.
// */
//public class GargageBoxLazy {
//    private static GargageBoxLazy instance;
//
//    public static GargageBoxLazy getInstance(){
//        if(instance == null){
//            synchronized (GargageBoxLazy.class){
//                if(instance ==null){
//                    instance = new GargageBoxLazy();
//                }
//            }
//        }
//        return  instance;
//    }
//
//    private GargageBoxLazy(){
//
//    }
//}
