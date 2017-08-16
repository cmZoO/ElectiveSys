/**   
* @Title: SystemServerTest.java 
* @Package com.test 
* @Description: TODO(����SystemServerTest��) 
* @author zx583   
* @date 2017��4��23�� ����9:46:10 
* @version V1.0   
*/
package com.test;

import java.util.Date;

import org.junit.Test;

import com.server.SystemServer;

/** 
* @ClassName: SystemServerTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��23�� ����9:46:10 
*  
*/

class MyThread extends Thread {
	/** 
	* Title: 
	* Description: TODO(������һ�仰�����������������) 
	* @param string 
	*/
	public MyThread(String string) {
		super(string);
	}

	@Override
	public void run() {
		System.out.println("��ʼ����" + Thread.currentThread().getName() + new Date());
		new SystemServer().deLoadPlan();
		System.out.println("��������" + Thread.currentThread().getName() + new Date());
	}
}

public class SystemServerTest {
	public static void main(String[] args) {
		onLoadPlan();
	}
	
	@Test
	public static void onLoadPlan() {
		Thread myThread1 = new MyThread("�߳�1");     // ����һ���µ��߳�  myThread1  ���߳̽����½�״̬
		Thread myThread2 = new MyThread("�߳�2");     // ����һ���µ��߳� myThread2 ���߳̽����½�״̬
		System.out.println("�߳�1��ʼ");
		myThread1.start();                     // ����start()����ʹ���߳̽������״̬
		System.out.println("�߳�2��ʼ");
		myThread2.start();       
	}
}
