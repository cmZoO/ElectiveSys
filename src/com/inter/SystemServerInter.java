/**   
* @Title: SystemServerInter.java 
* @Package com.inter 
* @Description: TODO(描述SystemServerInter类) 
* @author zx583   
* @date 2017年4月21日 下午10:12:13 
* @version V1.0   
*/
package com.inter;

import java.util.List;

import com.bean.Course;
import com.bean.Elective_time;

/** 
* @ClassName: SystemServerInter 
* @Description: TODO(系统服务功能) 
* @author zx583 
* @date 2017年4月21日 下午10:12:13 
*  
*/
public interface SystemServerInter {
	/** 
	* @Title: getCourses 
	* @Description: TODO(获取当前挂载计划的所有课程) 
	* @param @return - 设定文件 
	* @return List<Course> - 返回类型 
	* @throws 
	*/
	public abstract List<Course> getCourses();
	/** 
	* @Title: getPlan 
	* @Description: TODO(获取系统挂载的Plan对象的id) 
	* @param @return - 设定文件 
	* @return Plan - 返回类型 
	* @throws 
	*/
	public abstract Integer getPlan_id();
	/** 
	* @Title: onLoadPlan 
	* @Description: TODO(将选课计划加载到系统中) 
	* @param  - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public abstract void onLoadPlan(String plan_id);
	
	/** 
	* @Title: deLoadPlan 
	* @Description: TODO(下载当前系统中的选课计划) 
	* @param  - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public abstract void deLoadPlan();
	
	/** 
	* @Title: isLoaded 
	* @Description: TODO(当前系统有无挂载计划) 
	* @param @return - 设定文件 
	* @return bllean - 返回类型 
	* @throws 
	*/
	public abstract boolean isLoaded();
	
	/** 
	* @Title: getDelay_drop_time 
	* @Description: TODO(获取当前挂载的计划的延迟退选时间) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public abstract Integer getDelay_drop_time();
	
	/** 
	* @Title: getElective_Times 
	* @Description: TODO(获取当前系统挂载的计划的选课时间段) 
	* @param @return - 设定文件 
	* @return List<Elective_time> - 返回类型 
	* @throws 
	*/
	public abstract List<Elective_time> getElective_Times();
	
	/** 
	* @Title: getElective_servers 
	* @Description: TODO(获取但钱系统所有的选课服务) 
	* @param @return - 设定文件 
	* @return List<ElectiveServer> - 返回类型 
	* @throws 
	*/
	public abstract List<ElectiveServer> getElective_servers();
	
	/** 
	* @Title: getPlan_name 
	* @Description: TODO(获取当前系统挂载的计划的选课计划名称) 
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public abstract String getPlan_name();
	
	/** 
	* @Title: getElectiveServer 
	* @Description: TODO(根据course_id获取该课程对应的选课服务) 
	* @param @param course_id
	* @param @return - 设定文件 
	* @return ElectiveServer - 返回类型 
	* @throws 
	*/
	public abstract ElectiveServer getElectiveServer(String course_id);
	
	/** 
	* @Title: getCourse 
	* @Description: TODO(根据course_id获取该课程对应的课程) 
	* @param @param course_id
	* @param @return - 设定文件 
	* @return Course - 返回类型 
	* @throws 
	*/
	public abstract Course getCourse(String course_id);
	
	/** 
	* @Title: isOpen 
	* @Description: TODO(系统是否已经打开选课) 
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean isOpen();
	
	/** 
	* @Title: changOpen 
	* @Description: TODO(修改选课功能状态) 
	* @param @param open
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract void changOpen(boolean open);
	
	/** 
	* @Title: reload 
	* @Description: TODO(重新加载计划) 
	* @param  - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public abstract void reload();
}
