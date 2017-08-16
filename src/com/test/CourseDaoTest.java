/**   
* @Title: CourseDaoTest.java 
* @Package com.test 
* @Description: TODO(描述CourseDaoTest类) 
* @author zx583   
* @date 2017年4月16日 下午3:34:23 
* @version V1.0   
*/
package com.test;

import java.util.List;

import org.junit.Test;

import com.bean.Course;
import com.bean.Plan;
import com.bean.Teacher;
import com.dao.CourseDao;
import com.dao.PlanDao;
import com.dao.TeacherDao;
import com.util.ShowUtil;

/** 
* @ClassName: CourseDaoTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月16日 下午3:34:23 
*  
*/
public class CourseDaoTest {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		totalTest();
	}

	/** 
	* @Title: totalTest 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param  - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private static void totalTest() {
		// TODO Auto-generated method stub
		System.out.println(new CourseDao().getTotal());
	}
	
	CourseDao dao = new CourseDao();

	@Test
	public void insert() {
		Plan plan = new PlanDao().query(new Plan(13), null).get(0);
		
		Course course = new Course();
		course.setPlan(plan);
		course.setCourse_name("吱吱吱");
		course.setClass_time(1);
		course.setTotal(200);
		System.out.println(dao.insert(course));
		
		query();
	}
	
	@Test
	public void query() {
		Course course = new Course();
		
		ShowUtil.showList(dao.query(course, null));
	}
	
	@Test
	public void delete() {
		Course course = new Course();
		course.setPlan(new PlanDao().query(new Plan(13), null).get(0));
		System.out.println(dao.delete(course));
		
		query();
	}
	
	@Test
	public void update() {
		Course course = new Course(4);
		course.setPlan(new PlanDao().query(new Plan(15), null).get(0));
		
		course.setCourse_name("xing");
		
		System.out.println(dao.update(course));
		
		query();
		
	}
	@Test
	public void insertTeacherForCourse() {
		Teacher teacher = new TeacherDao().query(new Teacher(12), null).get(0);
		Course course = dao.query(new Course(4), null).get(0);
		
		System.out.println(teacher);
		System.out.println(course);
		
		System.out.println(dao.insertTeacherForCourse(teacher, course));
		
	}
	@Test
	public void insertTeachersForCourse() {
		Course course = dao.query(new Course(4), null).get(0);
		List<Teacher> teachers = new TeacherDao().query(new Teacher(), null);
		
		System.out.println(dao.insertTeachersForCourse(teachers, course));
	}
	@Test
	public void queryTeacherOfCourse() {
		Course course = dao.query(new Course(5), null).get(0);
		
		ShowUtil.showList(dao.queryTeacherOfCourse(course));
	}
	@Test
	public void queryCourseOfTeacher() {
		Teacher teacher = new TeacherDao().query(new Teacher(13), null).get(0);
		
		ShowUtil.showList(dao.queryCourseOfTeacher(teacher));
	}
	@Test
	public void deleteTeacherOfCourse() {
		Course course = dao.query(new Course(4), null).get(0);
		Teacher teacher = new TeacherDao().query(new Teacher(12), null).get(0);
		System.out.println(dao.deleteByTeacherCourse(course, teacher));
	}
	@Test
	public void deleteTeachersOfCourse() {
		Course course = dao.query(new Course(4), null).get(0);
		System.out.println(dao.deleteByCourse(course));
	}
	
}
