/**   
* @Title: ElectiveServer.java 
* @Package com.inter 
* @Description: TODO(描述ElectiveServer类) 
* @author zx583   
* @date 2017年4月21日 下午9:12:50 
* @version V1.0   
*/
package com.inter;

import com.bean.Course;

/** 
* @ClassName: ElectiveServer 
* @Description: TODO(选课服务功能) 
* @author zx583 
* @date 2017年4月21日 下午9:12:50 
*  
*/
public interface ElectiveServer {
	public static final String illegal = "illegal";
	public static final String success = "success";
	public static final String fail = "fail";
	
	/** 
	* @Title: initServer 
	* @Description: TODO(初始化服务) 
	* @param  - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public abstract void initServer(Course course);
	/** 
	* @Title: choice 
	* @Description: TODO(学生stu_id选择该课) 
	* @param @param stu_id
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public abstract String choice(String stu_id);
	
	/** 
	* @Title: dechoice 
	* @Description: TODO(学生stu_id退选该课) 
	* @param @param stu_id
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public abstract String dechoice(String stu_id);
	
	/** 
	* @Title: getTotal 
	* @Description: TODO(获取当前可选总数) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public Integer getTotal();
	
	/** 
	* @Title: getCourse_id 
	* @Description: TODO(获取服务对应的CourseID) 
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public String getCourse_id();
	
	/** 
	* @Title: getNextToal 
	* @Description: TODO(获取下一阶段可选总数) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public Integer getNextToal();
}
