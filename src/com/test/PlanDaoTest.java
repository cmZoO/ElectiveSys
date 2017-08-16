/**   
* @Title: PlanDaoTest.java 
* @Package com.test 
* @Description: TODO(描述PlanDaoTest类) 
* @author zx583   
* @date 2017年4月16日 下午4:07:38 
* @version V1.0   
*/
package com.test;

import org.junit.Test;

import com.bean.Elective_time;
import com.bean.Plan;
import com.dao.PlanDao;

/** 
* @ClassName: PlanDaoTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月16日 下午4:07:38 
*  
*/
public class PlanDaoTest {
	PlanDao dao = new PlanDao();
	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		PlanDao dao = new PlanDao();
		Long time = new java.util.Date().getTime();
//		dao.insert(new Plan(11, "abc", new Date(time), new Date(time), 0, 0, null));
//		dao.insert(new Plan(12, "abc", new Date(time), new Date(time), 0, 0, null));
//		dao.insert(new Plan(13, "abc", new Date(time), new Date(time), 0, 0, null));
//		dao.insert(new Plan(15, "abc", new Date(time), new Date(time), 0, 0, null));
//		dao.insert(new Plan(16, "arc", new Date(time), new Date(time), 0, 0, null));
		
		Plan p = new Plan();
//		p.setPlan_id(12);
//		System.out.println(dao.delete(p));
		
		p.setPlan_id(11);
		p.setPlan_name("a");
		System.out.println(dao.update(p));
		
		Plan plan = new Plan();
		for (Plan stu : dao.query(plan, null)) {
			System.out.println(stu);
		}
	}
	@Test
	public void queryMyTime() {
		Plan p = new Plan();
		p.setPlan_id(13);
		p = dao.query(p, null).get(0);
		for (Elective_time t : p.getElective_Times()) {
			if (t.getPlan() == p) {
				System.out.print(true + " ");
			}
			System.out.println(t);
		}
	}

}
