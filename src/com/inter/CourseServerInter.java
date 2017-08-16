/**   
* @Title: CourseServerInter.java 
* @Package com.inter 
* @Description: TODO(描述CourseServerInter类) 
* @author zx583   
* @date 2017年4月21日 下午4:18:16 
* @version V1.0   
*/
package com.inter;

import java.util.List;

import com.bean.Course;
import com.bean.Plan;
import com.bean.Teacher;

/** 
* @ClassName: CourseServerInter 
* @Description: TODO(课程服务的功能) 
* @author zx583 
* @date 2017年4月21日 下午4:18:16 
*  
*/
public interface CourseServerInter {
	/** 
	* @Title: getAllCourse 
	* @Description: TODO(获取所有课程) 
	* @param @return - 设定文件 
	* @return List<Course> - 返回类型 
	* @throws 
	*/
	public abstract List<Course> getAllCourse();
	
	/** 
	* @Title: getCourse 
	* @Description: TODO(根据课程id获取课程) 
	* @param @param course_id
	* @param @return - 设定文件 
	* @return Course - 返回类型 
	* @throws 
	*/
	public abstract Course getCourse(String course_id);
	
	/** 
	* @Title: getCourseByPlan 
	* @Description: TODO(根据选课计划id获取课程) 
	* @param @param plan_id
	* @param @return - 设定文件 
	* @return List<Course> - 返回类型 
	* @throws 
	*/
	public abstract List<Course> getCourseByPlan(String plan_id);
	
	/** 
	* @Title: deleteCourseByplan 
	* @Description: TODO(删除计划id为plan_id的所有课程) 
	* @param @param plan_id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteCourseByplan(String plan_id);
	
	/** 
	* @Title: deleteCourseById 
	* @Description: TODO(删除课程ID为id的课程) 
	* @param @param course_id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteCourseById(String course_id);
	
	/** 
	* @Title: deleteAllCourse 
	* @Description: TODO(删除所有课程) 
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteAllCourse();
	
	/** 
	* @Title: updateCourse 
	* @Description: TODO(用course更新id为course_id的计划) 
	* @param @param course
	* @param @param course_id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean updateCourse(Course course, String course_id);
	
	/** 
	* @Title: insertCourse 
	* @Description: TODO(插入一个新的课程) 
	* @param @param course
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean insertCourse(Course course);
	
	/** 
	* @Title: deleteTeacherForCourse 
	* @Description: TODO(删除课程course的所有任课老师) 
	* @param @param course
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteTeacherForCourse(Course course);
	
	/** 
	* @Title: addTeachersForCourse 
	* @Description: TODO(为课程course添加任课老师) 
	* @param @param teachers
	* @param @param course
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean addTeachersForCourse(List<Teacher> teachers, Course course);
	
	/** 
	* @Title: getCourseByTeacPlan 
	* @Description: TODO(根据教师与计划获取课程) 
	* @param @param teacher
	* @param @param plan
	* @param @return - 设定文件 
	* @return List<Course> - 返回类型 
	* @throws 
	*/
	public abstract List<Course> getCourseByTeacPlan(Teacher teacher, Plan plan);
}
