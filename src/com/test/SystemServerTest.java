/**   
* @Title: SystemServerTest.java 
* @Package com.test 
* @Description: TODO(描述SystemServerTest类) 
* @author zx583   
* @date 2017年4月23日 上午9:46:10 
* @version V1.0   
*/
package com.test;

import java.util.Date;

import org.junit.Test;

import com.server.SystemServer;

/** 
* @ClassName: SystemServerTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月23日 上午9:46:10 
*  
*/

class MyThread extends Thread {
	/** 
	* Title: 
	* Description: TODO(这里用一句话描述这个方法的作用) 
	* @param string 
	*/
	public MyThread(String string) {
		super(string);
	}

	@Override
	public void run() {
		System.out.println("开始下线" + Thread.currentThread().getName() + new Date());
		new SystemServer().deLoadPlan();
		System.out.println("结束下线" + Thread.currentThread().getName() + new Date());
	}
}

public class SystemServerTest {
	public static void main(String[] args) {
		onLoadPlan();
	}
	
	@Test
	public static void onLoadPlan() {
		Thread myThread1 = new MyThread("线程1");     // 创建一个新的线程  myThread1  此线程进入新建状态
		Thread myThread2 = new MyThread("线程2");     // 创建一个新的线程 myThread2 此线程进入新建状态
		System.out.println("线程1开始");
		myThread1.start();                     // 调用start()方法使得线程进入就绪状态
		System.out.println("线程2开始");
		myThread2.start();       
	}
}
