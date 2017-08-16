/**   
* @Title: ElectiveObjectTest.java 
* @Package com.test 
* @Description: TODO(����ElectiveObjectTest��) 
* @author zx583   
* @date 2017��4��23�� ����11:33:17 
* @version V1.0   
*/
package com.test;

import java.util.Date;

import org.junit.Test;

import com.inter.ElectiveServer;
import com.server.ElectiveObject;
import com.server.SystemServer;

/** 
* @ClassName: ElectiveObjectTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��23�� ����11:33:17 
*  
*/
public class ElectiveObjectTest {
	@Test
	public void intTest () {
		Integer a,b;
		a = b = new Integer(1);
		b = a;
		b++;
		System.out.println(a);
		System.out.println(b);
	}
	
	@Test
	public void test() {
		Integer a = 1;
		intTest2(a);
		
		System.out.println(a);
	}
	
	public void intTest2(Integer i) {
		i++;
	}
	
	@Test
	public void choice() {
		SystemServer sys = new SystemServer();
		sys.onLoadPlan("13");
		ElectiveServer server = sys.getElectiveServer("5");
		System.out.println(server.choice("12"));
	}
	
	//ģ��11 12  16 13��14���γ�5
	public static void main(String[] args) throws InterruptedException {
		//��ʼ��ѡ�λ���
		SystemServer sys = new SystemServer();
		sys.onLoadPlan("13");
		
		System.out.println("ϵͳ��ʼ�����");
		
		System.out.println("ϵͳ����״̬" + sys.isLoaded());
		
		Thread.sleep(1000);
		
		new MThread("11").start();
		new MThread("12").start();
		new MThread("13").start();
		new MThread("14").start();
		new MThread("16").start();
	}
}

class MThread extends Thread {
	/** 
	* Title: 
	* Description: TODO(������һ�仰�����������������) 
	* @param string 
	*/
	public MThread(String string) {
		super(string);
	}

	@Override
	public void run() {
		System.out.println("��ʼѡ��" + Thread.currentThread().getName() + new Date());
		ElectiveServer server = new SystemServer().getElectiveServer("5");
		System.out.println(Thread.currentThread().getName() + server.choice(Thread.currentThread().getName()));
		System.out.println("����ѡ��" + Thread.currentThread().getName() + new Date());
	}
}