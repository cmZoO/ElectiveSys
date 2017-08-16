package com.util;

import java.util.regex.Pattern;

/** 
* @ClassName: RegexUtil 
* @Description: TODO(字符串正则表达式判断工具类) 
* @author zx583 
* @date 2017年4月24日 下午9:49:03 
*  
*/
public class RegexUtil {
	
	/** 
	* @Fields allNum : TODO(只有且有数字) 
	*/ 
	public static String allNum = "[0-9]+";
	
	
	/** 
	* @Title: isAllNum 
	* @Description: TODO(判断str是否全数字) 
	* @param @param str
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public static boolean isAllNum(String str) {
		return Check(str, allNum);
	}
	
	
	/** 
	* @Title: Check 
	* @Description: TODO(判断str自否满足正则表达式regEx) 
	* @param @param str
	* @param @param regEx
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public static boolean Check(String str, String regEx) {
		if (CheckUtil.nullCheck(str, regEx)) {
			return false;
		}
		
		return Pattern.compile(regEx).matcher(str).matches();
	}
}
