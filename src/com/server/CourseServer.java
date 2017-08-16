/**   
* @Title: CourseServer.java 
* @Package com.server 
* @Description: TODO(描述CourseServer类) 
* @author zx583   
* @date 2017年4月21日 下午4:41:17 
* @version V1.0   
*/
package com.server;

import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.Plan;
import com.bean.Teacher;
import com.dao.CourseDao;
import com.inter.CourseServerInter;
import com.util.CheckUtil;
import com.util.RegexUtil;

/** 
* @ClassName: CourseServer 
* @Description: TODO(课程服务) 
* @author zx583 
* @date 2017年4月21日 下午4:41:17 
*  
*/
public class CourseServer implements CourseServerInter {
	/** 
	* @Fields dao : TODO(课程Dao操作类) 
	*/ 
	CourseDao dao = new CourseDao();
	/* (非 Javadoc) 
	* Title: getAllCourse
	* Description:
	* @return 所有课程集合
	* @see com.inter.CourseServerInter#getAllCourse() 
	*/
	@Override
	public List<Course> getAllCourse() {
		return dao.query(new Course(), null);
	}

	/* (非 Javadoc) 
	* Title: getCourse
	* Description:
	* @param course_id
	* @return 有符合的记录返回该对象 否则null
	* @see com.inter.CourseServerInter#getCourse(java.lang.String) 
	*/
	@Override
	public Course getCourse(String course_id) {
		//course_id 空值判断
		if (CheckUtil.nullCheck(course_id)) {
			return null;
		}
		
		//course_id格式判断
		if (!RegexUtil.isAllNum(course_id)) {
			return null;
		}
		
		//查询并获取结果
		List<Course> result = dao.query(new Course(Integer.parseInt(course_id)), null);
		
		//有该结果，返回对象
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	/* (非 Javadoc) 
	* Title: getCourseByPlan
	* Description:
	* @param plan_id
	* @return 
	* @see com.inter.CourseServerInter#getCourseByPlan(java.lang.String) 
	*/
	@Override
	public List<Course> getCourseByPlan(String plan_id) {
		//非法plan_id判断
		if (CheckUtil.nullCheck(plan_id) || !RegexUtil.isAllNum(plan_id)) {
			return new ArrayList<Course>();
		}
		
		//构造查询条件
		Course course = new Course();
		course.setPlan(new Plan(Integer.parseInt(plan_id)));
		
		return dao.query(course, null);
	}

	/* (非 Javadoc) 
	* Title: deleteCourseById
	* Description:
	* @param course_id
	* @return 
	* @see com.inter.CourseServerInter#deleteCourseById(java.lang.String) 
	*/
	@Override
	public boolean deleteCourseById(String course_id) {
		//非法course_id判断
		if (CheckUtil.nullCheck(course_id) || !RegexUtil.isAllNum(course_id)) {
			return false;
		}
		
		return dao.delete(new Course(Integer.parseInt(course_id))) == 1;
	}

	/* (非 Javadoc) 
	* Title: deleteAllCourse
	* Description:
	* @return 
	* @see com.inter.CourseServerInter#deleteAllCourse() 
	*/
	@Override
	public boolean deleteAllCourse() {
		return dao.delete(new Course()) > 0;
	}

	/* (非 Javadoc) 
	* Title: updateCourse
	* Description:
	* @param course
	* @param course_id
	* @return 
	* @see com.inter.CourseServerInter#updateCourse(com.bean.Course, java.lang.String) 
	*/
	@Override
	public boolean updateCourse(Course course, String course_id) {
		//非法course_id,course判断
		if (CheckUtil.nullCheck(course_id, course) || !RegexUtil.isAllNum(course_id)) {
			return false;
		}
		
		course.setCourse_id(Integer.parseInt(course_id));
		
		return dao.update(course) == 1;
	}

	/* (非 Javadoc) 
	* Title: insertCourse
	* Description:
	* @param course
	* @return 
	* @see com.inter.CourseServerInter#insertCourse(com.bean.Course) 
	*/
	@Override
	public boolean insertCourse(Course course) {
		//course非法判断
		if (CheckUtil.nullCheck(course)) {
			return false;
		}
		
		return dao.insert(course) == 1;
	}

	/* (非 Javadoc) 
	* Title: deleteTeacherForCourse
	* Description:
	* @param course
	* @return 
	* @see com.inter.CourseServerInter#deleteTeacherForCourse(com.bean.Course) 
	*/
	@Override
	public boolean deleteTeacherForCourse(Course course) {
		return dao.deleteByCourse(course) >= 0;
	}

	/* (非 Javadoc) 
	* Title: addTeachersForCourse
	* Description:
	* @param teachers
	* @param course
	* @return 
	* @see com.inter.CourseServerInter#addTeachersForCourse(java.util.List, com.bean.Course) 
	*/
	@Override
	public boolean addTeachersForCourse(List<Teacher> teachers, Course course) {
		return dao.insertTeachersForCourse(teachers, course) > 0;
	}

	/* (非 Javadoc) 
	* Title: deleteCourseByplan
	* Description:
	* @param plan_id
	* @return 
	* @see com.inter.CourseServerInter#deleteCourseByplan(java.lang.String) 
	*/
	@Override
	public boolean deleteCourseByplan(String plan_id) {
		List<Course> courses = getCourseByPlan(plan_id);
		boolean result = true;
		for (Course c : courses) {
			dao.deleteByCourse(c);
			result = dao.delete(c) == 1;
		}
		
		return result;
	}

	/* (非 Javadoc) 
	* Title: getCourseByTeacPlan
	* Description:
	* @param teacher
	* @param plan
	* @return 
	* @see com.inter.CourseServerInter#getCourseByTeacPlan(com.bean.Teacher, com.bean.Plan) 
	*/
	@Override
	public List<Course> getCourseByTeacPlan(Teacher teacher, Plan plan) {
		List<Course> result = dao.queryCourseOfTeacher(teacher);

		if (teacher == null) {
			return result;
		}
		
		if (plan != null) {
			for (int i = 0; i < result.size(); i++) {
				if (result.get(i).getPlan().getPlan_id().compareTo(plan.getPlan_id()) != 0) {
					result.remove(i--);
				}
			}
		}
		
		return result;
	}

}
