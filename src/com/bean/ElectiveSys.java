/**   
* @Title: ElectiveSys.java 
* @Package com.server 
* @Description: TODO(描述ElectiveSys类) 
* @author zx583   
* @date 2017年4月21日 上午10:00:58 
* @version V1.0   
*/
package com.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inter.ElectiveServer;

/** 
* @ClassName: ElectiveSys 
* @Description: TODO(选课系统类) 
* @author zx583 
* @date 2017年4月21日 上午10:00:58 
*  
*/
public class ElectiveSys {
	/** 
	* @Fields plan : TODO(表示系统当前挂载的选课计划) 
	*/ 
	public static Plan plan = null;
	
	/** 
	* @Fields electives : TODO(系统当前挂载的选课服务) 
	*/ 
	public static Map<String, ElectiveServer> electives = new HashMap<String, ElectiveServer>();
	
	/** 
	* @Fields courses : TODO(系统当前挂载的课程) 
	*/ 
	public static Map<String, Course> courses = new HashMap<String, Course>();
	
	/** 
	* @Fields isOpen : TODO(系统是否打开了选课) 
	*/ 
	public static boolean isOpen = false;

}
