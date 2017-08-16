/**   
* @Title: DateUtil.java 
* @Package com.util 
* @Description: TODO(描述DateUtil类) 
* @author zx583   
* @date 2017年4月19日 上午10:24:05 
* @version V1.0   
*/
package com.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @ClassName: DateUtil 
* @Description: TODO(时间工具类) 
* @author zx583 
* @date 2017年4月19日 上午10:24:05 
*  
*/
public class DateUtil {
	/** 
	* @Fields mmOfMinute : TODO(一分钟的毫秒数) 
	*/ 
	public static final Integer mmOfMinute = 60000;
	
	/** 
	* @Title: numToWeekDay 
	* @Description: TODO(数字转星期) 
	* @param @param num
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public static String numToWeekDay(Integer num) {
		if (num == null) {
			return "";
		}
		
		switch (num) {
		case 1:
			return "星期一";
		case 2:
			return "星期二";
		case 3:
			return "星期三";
		case 4:
			return "星期四";
		case 5:
			return "星期五";
		case 6:
			return "星期六";
		case 7:
			return "星期天";
		default:
			return "";
		}
	}
	
	/** 
	* @Title: getLongTime 
	* @Description: TODO(获取当前时间的毫秒值) 
	* @param @return - 设定文件 
	* @return Long - 返回类型 
	* @throws 
	*/
	public static Long getLongTime() {
		return new Date().getTime();
	}
	
	/** 
	* @Title: isOdd 
	* @Description: TODO(判断当前时间是否是minute分钟的奇数倍) 
	* @param @param minute - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public static boolean isOdd(Integer minute) {
		return isOdd(getLongTime(), minute);
	}
	
	/** 
	* @Title: isOdd 
	* @Description: TODO(判断时间time是否是minute分钟的奇数倍) 
	* @param @param minute - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public static boolean isOdd(Long time, Integer minute) {
		return (time / mmOfMinute / minute) % 2 > 0;
	}
	
	/** 
	* @Title: getDate 
	* @Description: TODO(根据字符串获取时间yyyy-MM-dd) 
	* @param @param date
	* @param @return - 设定文件 
	* @return java.sql.Date - 返回类型 
	* @throws 
	*/
	public static java.sql.Date getDate(String date) {
		try {
			return new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new java.sql.Date(getLongTime());
	}
	
	public static Timestamp getTimestamp(String time) {
		try {
			return new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Timestamp(getLongTime());
	}
	
	/** 
	* @Title: getTimePark 
	* @Description: TODO(获取当前时间所属时间段) 
	* @param @param time
	* @param @return - 设定文件 
	* @return Long - 返回类型 
	* @throws 
	*/
	public static Long getTimePark(Integer minute) {
		return getLongTime() / mmOfMinute / minute;
	}
}
