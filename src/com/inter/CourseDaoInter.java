/**   
* @Title: CourseDaoInter.java 
* @Package com.inter 
* @Description: TODO(描述CourseDaoInter类) 
* @author zx583   
* @date 2017年4月16日 下午3:16:49 
* @version V1.0   
*/
package com.inter;

import java.util.List;

import com.bean.Course;
import com.bean.Teacher;

/** 
* @ClassName: CourseDaoInter 
* @Description: TODO(课程表特有的Dao方法接口) 
* @author zx583 
* @date 2017年4月16日 下午3:16:49 
*  
*/
public interface CourseDaoInter {
	/** 
	* @Title: queryTeacherOfCourse 
	* @Description: TODO(查询一个课程的任课老师) 
	* @param @param course
	* @param @return - 任课老师列表 
	* @return List<Teacher> - 返回类型 
	* @throws 
	*/
	public abstract List<Teacher> queryTeacherOfCourse(Course course);
	
	/** 
	* @Title: queryCourseOfTeacher 
	* @Description: TODO(查询以为老师所任的课程) 
	* @param @param teacher
	* @param @return - 任课列表 
	* @return List<Course> - 返回类型 
	* @throws 
	*/
	public abstract List<Course> queryCourseOfTeacher(Teacher teacher);
	
	/** 
	* @Title: insertTeacherForCourse 
	* @Description: TODO(为一门课添加任课老师) 
	* @param @param teacher
	* @param @param course
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract int insertTeacherForCourse(Teacher teacher, Course course);
	
	/** 
	* @Title: insertTeacherForCourse 
	* @Description: TODO(为一门课添加多个任课老师) 
	* @param @param teacher
	* @param @param course
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract int insertTeachersForCourse(List<Teacher> teachers, Course course);
	
	/** 
	* @Title: deleteByTeacherCourse 
	* @Description: TODO(根据教师和课程删除任课老师记录) 
	* @param @param course
	* @param @param teacher
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract int deleteByTeacherCourse(Course course, Teacher teacher);
	
	/** 
	* @Title: deleteByCourse 
	* @Description: TODO(根据课程删除课程老师记录) 
	* @param @param course
	* @param @param teacher
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract int deleteByCourse(Course course);
}
