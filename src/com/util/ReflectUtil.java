/**   
* @Title: ReflectUtil.java 
* @Package com.util 
* @Description: TODO(描述ReflectUtil类) 
* @author zx583   
* @date 2017年4月15日 下午4:17:04 
* @version V1.0   
*/
package com.util;

import java.lang.reflect.Field;

/** 
* @ClassName: ReflectUtil 
* @Description: TODO(反射工具类) 
* @author zx583 
* @date 2017年4月15日 下午4:17:04 
*  
*/
public class ReflectUtil {
	/**
	 * 
	* @Title: getMethodForFied 
	* @Description: TODO(根据属性获取该属性对应的get方法) 
	* @param @param f
	* @param @return - 一二字母为大写直接返回get+属性名 否则返回get+属性名(第一位字符变大写) 
	* @return String - 返回类型 
	* @throws
	 */
	public static String getMethodForFied(Field f) {
		if (f == null || !(f instanceof Field)) {
			return null;
		}
		
		String name = f.getName();
		
		if (Character.isUpperCase(name.charAt(0)) || //属性名第一位为大写
		    (name.length() > 1 && Character.isUpperCase(name.charAt(1)))) { //属性名第二位为大写
			return "get" + name;
		}
		
		return "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	
	/**
	 * 
	* @Title: setMethodForFied 
	* @Description: TODO(根据属性获取该属性对应的set方法) 
	* @param @param f
	* @param @return - 一二字母为大写直接返回set+属性名 否则返回set+属性名(第一位字符变大写) 
	* @return String - 返回类型 
	* @throws
	 */
	public static String setMethodForFied(Field f) {
		if (f == null || !(f instanceof Field)) {
			return null;
		}
		
		String name = f.getName();
		
		if (Character.isUpperCase(name.charAt(0)) || //属性名第一位为大写
		    (name.length() > 1 && Character.isUpperCase(name.charAt(1)))) { //属性名第二位为大写
			return "set" + name;
		}
		
		return "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
}
