/**   
* @Title: CheckUtil.java 
* @Package com.util 
* @Description: TODO(描述CheckUtil类) 
* @author zx583   
* @date 2017年4月15日 下午5:24:17 
* @version V1.0   
*/
package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.bean.Course;

/** 
* @ClassName: CheckUtil 
* @Description: TODO(检查工具类) 
* @author zx583 
* @date 2017年4月15日 下午5:24:17 
*  
*/
public class CheckUtil {
	/** 
	* @Title: nullCheck 
	* @Description: TODO(检查是否含有空值) 
	* @param @param rs
	* @param @param c
	* @param @param obj
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public static boolean nullCheck(Object ...obj) {
		for (Object o : obj) {
			if (o == null) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/** 
	* @Title: allNullBeanCheck 
	* @Description: TODO(检查一个bean的属性是否全空) 
	* @param @param obj
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public static boolean allNullBeanCheck(Object obj) {
		if (nullCheck(obj)) {
			return true;
		}
		
		Class c = obj.getClass();
		
		for (Field f : c.getDeclaredFields()) {
			Method getMethod = null;
			try {
				getMethod = c.getDeclaredMethod(ReflectUtil.getMethodForFied(f));
			} catch (SecurityException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			try {
				if (getMethod.invoke(obj) != null) {
					return false;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}


	/** 
	* @Title: nullBeanCheck 
	* @Description: TODO(检查一个bean的属性是否存在空) 
	* @param @param course
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public static boolean nullBeanCheck(Object obj) {
		if (nullCheck(obj)) {
			return true;
		}
		
		Class c = obj.getClass();
		
		for (Field f : c.getDeclaredFields()) {
			Method getMethod = null;
			try {
				getMethod = c.getDeclaredMethod(ReflectUtil.getMethodForFied(f));
			} catch (SecurityException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			try {
				if (getMethod.invoke(obj) == null) {
					return true;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
}
