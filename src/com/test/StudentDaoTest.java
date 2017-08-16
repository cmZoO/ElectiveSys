/**   
* @Title: StudentDaoTest.java 
* @Package com.test 
* @Description: TODO(描述StudentDaoTest类) 
* @author zx583   
* @date 2017年4月15日 下午5:43:14 
* @version V1.0   
*/
package com.test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.bean.Student;
import com.dao.StudentDao;

/** 
* @ClassName: StudentDaoTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月15日 下午5:43:14 
*  
*/
public class StudentDaoTest {
	StudentDao dao = new StudentDao();
	
//	public static void main(String[] args) {
////		dao.insert(new Student(11, "张三", "123456" ,new Date(new java.util.Date().getTime())));
////		dao.insert(new Student(12, "李四", "123456", new Date(new java.util.Date().getTime())));
////		dao.insert(new Student(13, "张四", "123456", new Date(new java.util.Date().getTime())));
////		dao.insert(new Student(14, "李三", "123456", new Date(new java.util.Date().getTime())));
//		
////		Student s = new Student();
////		s.setStu_id(11);
////		System.out.println(dao.delete(s));
//		
//		Student s = new Student();
//		s.setStu_id(15);
//		s.setStu_name("王五");
//		System.out.println(dao.update(s));
//		
//		Student student = new Student();
//		student.setStu_id(1);
////		student.setStu_name("四");
//		List<Student> result = dao.query(student);
//		for (Student stu : result) {
//			System.out.println(stu);
//		}
//		System.out.println(result.get(0).getRegister_date().toString());
//	}
	
	
	@Test
	public void insert() {
		Student s = new Student();
		s.setStu_id(20);
		dao.insert(s);
		
		query();
	}
	
	@Test
	public void query() {
		Student s = new Student();
		for (Student stu : dao.query(s,null)) {
			System.out.println(stu);
		}
		
	}
	
	@Test
	public void delete() {
		Student stu = new Student();
		stu.setStu_id(17);
		
		System.out.println(dao.delete(stu));
		
		query();
	}
	
	@Test
	public void update() {
		Student student = new Student();
		student.setStu_id(14);
		student.setStu_name("张五");
		System.out.println(dao.update(student));
		query();
	}
	
	@Test
	public void getTotal() {
		System.out.println(dao.getTotal());
	}
}
