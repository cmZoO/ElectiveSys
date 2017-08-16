/**   
* @Title: RecordTest.java 
* @Package com.test 
* @Description: TODO(描述RecordTest类) 
* @author zx583   
* @date 2017年4月19日 下午10:51:56 
* @version V1.0   
*/
package com.test;

import org.junit.Test;

import com.bean.Course;
import com.bean.ElectiveRecord;
import com.bean.Student;
import com.dao.CourseDao;
import com.dao.ElectiveRecordDao;
import com.dao.StudentDao;
import com.util.ShowUtil;

/** 
* @ClassName: RecordTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月19日 下午10:51:56 
*  
*/
public class RecordTest {
	ElectiveRecordDao dao = new ElectiveRecordDao();
	@Test
	public void query() {
		ShowUtil.showList(dao.query(new ElectiveRecord(), null));
	}
	@Test
	public void insert() {
		Student student = new StudentDao().query(new Student(11), null).get(0);
		Course course = new CourseDao().query(new Course(5), null).get(0);
		
		System.out.println(dao.insert(new ElectiveRecord(student, course)));
	}
	@Test
	public void update() {
		Student student = new StudentDao().query(new Student(11), null).get(0);
		Course course = new CourseDao().query(new Course(5), null).get(0);
		ElectiveRecord record = dao.query(new ElectiveRecord(student, course), null).get(0);
		
		record.setCourse(new Course(6));
		
		System.out.println(dao.update(record));
	}
	@Test
	public void delete() {
		Student student = new StudentDao().query(new Student(11), null).get(0);
		ElectiveRecord record = new ElectiveRecord(student, null);
		
		System.out.println(dao.delete(record));
	}
}
