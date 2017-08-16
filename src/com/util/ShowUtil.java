/**   
* @Title: ShowUtil.java 
* @Package com.util 
* @Description: TODO(描述ShowUtil类) 
* @author zx583   
* @date 2017年4月19日 下午9:21:17 
* @version V1.0   
*/
package com.util;

import java.util.List;

/** 
* @ClassName: ShowUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月19日 下午9:21:17 
*  
*/
public class ShowUtil {
	public static void showList(List list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
