/**   
* @Title: electiveTimeTest.java 
* @Package com.test 
* @Description: TODO(����electiveTimeTest��) 
* @author zx583   
* @date 2017��4��19�� ����10:20:03 
* @version V1.0   
*/
package com.test;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Test;

import com.bean.Elective_time;
import com.bean.Plan;
import com.dao.Elective_timeDao;
import com.dao.PlanDao;
import com.util.DateUtil;

/** 
* @ClassName: electiveTimeTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��19�� ����10:20:03 
*  
*/
public class electiveTimeTest {
//	Elective_timeDao dao = new Elective_timeDao();
//	
//	@Test
//	public void insert() {
//		Plan p = new PlanDao().query(new Plan(13, null, null, null), null).get(0);
//		
//		Elective_time time = new Elective_time(null, p, new Date(DateUtil.getLongTime()), new Timestamp(DateUtil.getLongTime()), new Timestamp(DateUtil.getLongTime()));
//		
//		dao.insert(time);
//	}
//	
//	public void upadte() {
//		Plan p = new PlanDao().query(new Plan(13, null, null, null), null).get(0);
//		Elective_time time = new Elective_time(1, p, new Date(DateUtil.getLongTime()), new Timestamp(DateUtil.getLongTime()), new Timestamp(DateUtil.getLongTime()));
//		dao.update(time);		
//	}
//	
//	@Test
//	public void query() {
//		Plan p = new PlanDao().query(new Plan(13, null, null, null), null).get(0);
//		Elective_time time = new Elective_time();
//		time.setPlan(p);
//		for (Elective_time t : dao.query(time, null)) {
//			System.out.println(t);
//		}
//	}
//	@Test
//	public void delete() {
//		Plan p = new PlanDao().query(new Plan(13, null, null, null), null).get(0);
//		Elective_time time = new Elective_time();
//		time.setPlan(p);
//		dao.delete(time);
//	}
}
