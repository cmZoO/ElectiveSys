/**   
* @Title: reflectionTest.java 
* @Package com.util 
* @Description: TODO(����reflectionTest��) 
* @author zx583   
* @date 2017��4��15�� ����3:58:41 
* @version V1.0   
*/
package com.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import com.bean.Student;

/** 
* @ClassName: reflectionTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��15�� ����3:58:41 
*  
*/
public class reflectionTest {
	public static void main(String[] args) throws Exception{
		System.out.println(new Date().toString());
		Class c = Class.forName("com.bean.Student");
		
		Field[] fields = c.getDeclaredFields();
		
//		Object s = new Student(11, null, null);
		
//		for (Field f : fields) {
//			System.out.println(f.getName());
//			
//			System.out.println(f.getType());
//			
//			System.out.println(Modifier.toString(f.getModifiers()));
//		}
		
//		for (Method m : c.getDeclaredMethods()) {
//			System.out.println(m.getName());
//		}
//		
//		for (Field f : fields) {
//			f.setAccessible(true);
//			System.out.println(f.get(s));
//		}
		
		Method m = c.getMethod("getStu_id");
		
//		System.out.println(m.invoke(s));
	}
}
