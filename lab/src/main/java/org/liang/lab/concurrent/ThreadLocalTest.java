/**
 * 
 */
package org.liang.lab.concurrent;

import java.util.Date;

/**
 * @author miaol
 *
 */
public class ThreadLocalTest implements Runnable {
	
	public int count = 2;

	private static ThreadLocal<Object> obj = new ThreadLocal<Object>();
	
	public void setValue(Object object){
		obj.set(object);
	}
	
	public Object getValue(){
		return obj.get();
	}

	public void run(){
		while(count > 0){
			setValue(Thread.currentThread().getName() + " @ " + new Date());
			count--;
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " ==> " + getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
