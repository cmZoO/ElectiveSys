/**   
* @Title: TeacherDaoTest.java 
* @Package com.test 
* @Description: TODO(����TeacherDaoTest��) 
* @author zx583   
* @date 2017��4��15�� ����9:37:27 
* @version V1.0   
*/
package com.test;

import org.junit.Test;

import com.bean.Teacher;
import com.dao.TeacherDao;
import com.util.ShowUtil;

/** 
* @ClassName: TeacherDaoTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��15�� ����9:37:27 
*  
*/
public class TeacherDaoTest {

	/** 
	* @Title: main 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param args - �趨�ļ� 
	* @return void - �������� 
	* @throws 
	*/
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		TeacherDao dao = new TeacherDao();
//		
////		dao.insert(new Teacher(11, "����", "abc"));
////		dao.insert(new Teacher(12, "����", "abc"));
////		dao.insert(new Teacher(13, "����", "abc"));
////		dao.insert(new Teacher(14, "����", "abc"));
//		
////		Teacher t = new Teacher();
////		t.setTeac_id(11);
////		System.out.println(dao.delete(t));
//		
//		Teacher t = new Teacher();
//		t.setTeac_id(12);
//		t.setTeac_name("����");
//		System.out.println(dao.update(t));
//		
//		Teacher teacher = new Teacher();
//		teacher.setTeac_id(1);
////		student.setStu_name("��");
//		List<Teacher> result = dao.query(teacher);
//		for (Teacher teac : result) {
//			System.out.println(teac);
//		}
//	}
	
	TeacherDao dao = new TeacherDao();
	
	@Test
	public void insert() {
		Teacher t = new Teacher();
		t.setTeac_id(15);
		t.setTeac_name("sss");
		t.setPassword("aaaaa");
		System.out.println(dao.insert(t));
		
		query();
	}
	@Test
	public void query() {
		Teacher t = new Teacher();
		
		ShowUtil.showList(dao.query(t, null));
	}
	@Test
	public void delete() {
		Teacher t = new Teacher(14);
		
		System.out.println(dao.delete(t));
		
		query();
	}
	@Test
	public void update() {
		Teacher t = new Teacher(13);
		t.setPassword("def");
		
		System.out.println(dao.update(t));
		
		query();
	}
	@Test
	public void getTotal() {
		System.out.println(dao.getTotal());
	}

}
