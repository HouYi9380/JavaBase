package cn.myself.day5.gof.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by WY on 2017/11/17.
 */
public class App {
    @Test
    public void testProxy(){
        IWelcomeService w = new WelcomeServiceImpl();
        MyInvocationHandler h = new MyInvocationHandler(w);
        //创建代理对象
        IWelcomeService proxy = (IWelcomeService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IWelcomeService.class}, h);
        proxy.m1();
        proxy.m2();
        proxy.m3();
    }
}
