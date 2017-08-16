/**   
* @Title: AdminDaoTest.java 
* @Package com.test 
* @Description: TODO(����AdminDaoTest��) 
* @author zx583   
* @date 2017��4��15�� ����9:59:58 
* @version V1.0   
*/
package com.test;

import java.util.List;

import org.junit.Test;

import com.bean.Admin;
import com.dao.AdminDao;
import com.util.Page;
import com.util.ShowUtil;

/** 
* @ClassName: AdminDaoTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��15�� ����9:59:58 
*  
*/
public class AdminDaoTest {

	/** 
	* @Title: main 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param args - �趨�ļ� 
	* @return void - �������� 
	* @throws 
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdminDao dao = new AdminDao();
		
//		dao.insert(new Admin(11, "����", "abc"));
//		dao.insert(new Admin(12, "����", "abc"));
//		dao.insert(new Admin(13, "����", "abc"));
//		dao.insert(new Admin(14, "����", "abc"));
//		System.out.println(dao.insert(new Admin()));
//		
////		System.out.println(dao.delete(new Admin()));
//
//		Admin a = new Admin();
//		a.setAdmin_id(11);
//		a.setAdmin_name("����");
//		System.out.println(dao.update(a));
//		
//		Admin admin = new Admin();
////		student.setStu_name("��");
//		List<Admin> result = dao.query(admin, new Page(3, 4));
//		for (Admin stu : result) {
//			System.out.println(stu);
//		}
//		System.out.println();
//		
//		for (Admin stu : dao.query(admin, new Page(2, 3))) {
//			System.out.println(stu);
//		}
//		System.out.println();
//		for (Admin stu : dao.query(admin, new Page(2, 2))) {
//			System.out.println(stu);
//		}
//		System.out.println();
//		for (Admin stu : dao.query(admin, new Page(2, 1))) {
//			System.out.println(stu);
//		}
	}
	
	@Test
	public void test() {
		Integer a = new Integer(1);
		Object b = a;
		System.out.println(a.getClass());
		System.out.println(b.getClass());
	}
	
	AdminDao dao = new AdminDao();
	
	@Test
	public void query() {
		Admin admin = new Admin();
		ShowUtil.showList(dao.query(admin, null));
	}
	
	@Test
	public void delete() {
		Admin admin = new Admin(12);
		
		System.out.println(dao.delete(admin));
		
		query();
	}
	
	
	@Test
	public void update() {
		Admin admin = new Admin(14);
		admin.setAdmin_name("����");
		System.out.println(dao.update(admin));
		
		query();
	}
	
	@Test
	public void insert() {
		Admin admin = new Admin();
		admin.setPassword("cdeg");
		admin.setAdmin_name("����");
		
		System.out.println(dao.insert(admin));
		
		query();
	}
}
