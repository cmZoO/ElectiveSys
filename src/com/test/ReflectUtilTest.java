/**   
* @Title: ReflectUtilTest.java 
* @Package com.test 
* @Description: TODO(����ReflectUtilTest��) 
* @author zx583   
* @date 2017��4��15�� ����4:30:44 
* @version V1.0   
*/
package com.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.util.ReflectUtil;

/** 
* @ClassName: ReflectUtilTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��15�� ����4:30:44 
*  
*/
class newb {
	int a;
	int ab;
	int ABc;
	int Abc;
	int aBc;
	int _a;
	/** 
	* @return a 
	*/
	public int getA() {
		return a;
	}
	/** 
	* @param a Ҫ���õ� a 
	*/
	public void setA(int a) {
		this.a = a;
	}
	/** 
	* @return ab 
	*/
	public int getAb() {
		return ab;
	}
	/** 
	* @param ab Ҫ���õ� ab 
	*/
	public void setAb(int ab) {
		this.ab = ab;
	}
	/** 
	* @return aBc 
	*/
	public int getABc() {
		return ABc;
	}
	/** 
	* @param aBc Ҫ���õ� aBc 
	*/
	public void setABc(int aBc) {
		ABc = aBc;
	}
	/** 
	* @return abc 
	*/
	public int getAbc() {
		return Abc;
	}
	/** 
	* @param abc Ҫ���õ� abc 
	*/
	public void setAbc(int abc) {
		Abc = abc;
	}
	/** 
	* @return aBc 
	*/
	public int getaBc() {
		return aBc;
	}
	/** 
	* @param aBc Ҫ���õ� aBc 
	*/
	public void setaBc(int aBc) {
		this.aBc = aBc;
	}
	/** 
	* @return _a 
	*/
	public int get_a() {
		return _a;
	}
	/** 
	* @param _a Ҫ���õ� _a 
	*/
	public void set_a(int _a) {
		this._a = _a;
	}
}
public class ReflectUtilTest {
	public static void main(String[] args) throws Exception{
	
		Field[] fields = Class.forName("com.test.newb").getDeclaredFields();
		for (int i = 0; i < fields.length ; i++) {
			System.out.println(ReflectUtil.getMethodForFied(fields[i]));
		}
	}
}
