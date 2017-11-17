package cn.myself.day5.gof.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 
 */
public class MyInvocationHandler implements InvocationHandler {
	
	//Ŀ�����
	private Object target;

	public MyInvocationHandler(Object o){
		this.target = o ;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("hello world");
		return method.invoke(target,args);
	}

}
