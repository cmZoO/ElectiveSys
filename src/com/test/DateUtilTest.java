/**   
* @Title: DateUtilTest.java 
* @Package com.test 
* @Description: TODO(����DateUtilTest��) 
* @author zx583   
* @date 2017��4��23�� ����10:40:57 
* @version V1.0   
*/
package com.test;

import java.util.Date;

import org.junit.Test;

import com.util.DateUtil;

/** 
* @ClassName: DateUtilTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��23�� ����10:40:57 
*  
*/
public class DateUtilTest {
	@Test
	public void isOdd() {
		Date date = new Date(2017, 4, 23, 13, 59);
		
		System.out.println(DateUtil.isOdd(date.getTime(), 60));
	}
}
