/**   
* @Title: PlanDaoInter.java 
* @Package com.inter 
* @Description: TODO(描述PlanDaoInter类) 
* @author zx583   
* @date 2017年4月16日 下午4:44:46 
* @version V1.0   
*/
package com.inter;

import java.util.List;

import com.bean.Course;
import com.bean.Elective_time;
import com.bean.Plan;

/** 
* @ClassName: PlanDaoInter 
* @Description: TODO(选课计划表特有的Dao方法接口) 
* @author zx583 
* @date 2017年4月16日 下午4:44:46 
*  
*/
public interface PlanDaoInter {
	/** 
	* @Title: queryElective_timesOfPlan 
	* @Description: TODO(查询一个计划的选课时间集合) 
	* @param @param plan
	* @param @return - 设定文件 
	* @return List<Elective_time> - 返回类型 
	* @throws 
	*/
	public abstract List<Elective_time> queryElective_timesOfPlan(Plan plan);
	
	/** 
	* @Title: queryCoursesOfPlan 
	* @Description: TODO(查询一个计划的课程集合) 
	* @param @param plan
	* @param @return - 设定文件 
	* @return List<Course> - 返回类型 
	* @throws 
	*/
	public abstract List<Course> queryCoursesOfPlan(Plan plan);
}
