/**   
* @Title: SystemServer.java 
* @Package com.server 
* @Description: TODO(描述SystemServer类) 
* @author zx583   
* @date 2017年4月21日 下午10:17:37 
* @version V1.0   
*/
package com.server;

import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.ElectiveSys;
import com.bean.Elective_time;
import com.inter.ElectiveServer;
import com.inter.SystemServerInter;
import com.util.CheckUtil;
import com.util.RegexUtil;

/** 
* @ClassName: SystemServer 
* @Description: TODO(系统服务) 
* @author zx583 
* @date 2017年4月21日 下午10:17:37 
*  
*/
public class SystemServer implements SystemServerInter {

	/* (非 Javadoc) 
	* Title: onLoadPlan
	* Description:
	* @param plan_id 
	* @see com.inter.SystemServerInter#onLoadPlan(java.lang.String) 
	*/
	@Override
	public void onLoadPlan(String plan_id) {
		if (CheckUtil.nullCheck(plan_id) || !RegexUtil.isAllNum(plan_id)) {
			return;
		}
		//互斥初始化
		synchronized (ElectiveSys.class) {
			//初始化选课计划
			ElectiveSys.plan = new PlanServer().getPlan(plan_id);
			//初始化选课计划的课程和选课服务
			ElectiveServer electiveServer = null;
			for (Course c : new CourseServer().getCourseByPlan(plan_id)) {
				c.setPlan(ElectiveSys.plan);
				ElectiveSys.courses.put(c.getCourse_id().toString(), c);
				electiveServer = new ElectiveObject();
				electiveServer.initServer(c);
				ElectiveSys.electives.put(c.getCourse_id().toString(), electiveServer);
			}
		}
	}

	/* (非 Javadoc) 
	* Title: deLoadPlan
	* Description: 
	* @see com.inter.SystemServerInter#deLoadPlan() 
	*/
	@Override
	public void deLoadPlan() {
		//互斥初始化
		synchronized (ElectiveSys.class) {
			//下线选课计划
			ElectiveSys.plan = null;
			//下线选课计划课程
			ElectiveSys.courses.clear();
			//下线选课服务
			ElectiveSys.electives.clear();
			//关闭选课状态
			ElectiveSys.isOpen = false;
		}
	}

	/* (非 Javadoc) 
	* Title: isLoaded
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#isLoaded() 
	*/
	@Override
	public boolean isLoaded() {
		return ElectiveSys.plan != null;
	}

	/* (非 Javadoc) 
	* Title: getDelay_drop_time
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#getDelay_drop_time() 
	*/
	@Override
	public Integer getDelay_drop_time() {
		if (!isLoaded()) {
			return null;
		}
		
		return ElectiveSys.plan.getDelay_drop_time();
	}

	/* (非 Javadoc) 
	* Title: getElective_Times
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#getElective_Times() 
	*/
	@Override
	public List<Elective_time> getElective_Times() {
		if (!isLoaded()) {
			return null;
		}
		
		return ElectiveSys.plan.getElective_Times();
	}

	/* (非 Javadoc) 
	* Title: getPlan_name
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#getPlan_name() 
	*/
	@Override
	public String getPlan_name() {
		if (!isLoaded()) {
			return null;
		}
		
		return ElectiveSys.plan.getPlan_name();
	}

	/* (非 Javadoc) 
	* Title: getElectiveServer
	* Description:
	* @param course_id
	* @return 
	* @see com.inter.SystemServerInter#getElectiveServer(java.lang.String) 
	*/
	@Override
	public ElectiveServer getElectiveServer(String course_id) {
		return ElectiveSys.electives.get(course_id);
	}

	/* (非 Javadoc) 
	* Title: getCourse
	* Description:
	* @param course_id
	* @return 
	* @see com.inter.SystemServerInter#getCourse(java.lang.String) 
	*/
	@Override
	public Course getCourse(String course_id) {
		return ElectiveSys.courses.get(course_id);
	}

	/* (非 Javadoc) 
	* Title: isOpen
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#isOpen() 
	*/
	@Override
	public boolean isOpen() {
		return ElectiveSys.isOpen;
	}

	/* (非 Javadoc) 
	* Title: changOpen
	* Description:
	* @param open
	* @return 
	* @see com.inter.SystemServerInter#changOpen(boolean) 
	*/
	@Override
	public void changOpen(boolean open) {
		//互斥初始化
		synchronized (ElectiveSys.class) {
			ElectiveSys.isOpen = open;
		}
	}

	/* (非 Javadoc) 
	* Title: getPlan_id
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#getPlan_id() 
	*/
	@Override
	public Integer getPlan_id() {
		if (isLoaded()) {
			return ElectiveSys.plan.getPlan_id();
		}
		return null;
	}

	/* (非 Javadoc) 
	* Title: getCourses
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#getCourses() 
	*/
	@Override
	public List<Course> getCourses() {
		if (isLoaded()) {
			List<Course> result = new ArrayList<Course>();
			for (Course c : ElectiveSys.courses.values()) {
				result.add(c);
			}
			return result;
		}
		return new ArrayList<Course>();
	}

	/* (非 Javadoc) 
	* Title: reload
	* Description: 
	* @see com.inter.SystemServerInter#reload() 
	*/
	@Override
	public void reload() {
		String plan_id = ElectiveSys.plan.getPlan_id().toString();
		deLoadPlan();
		onLoadPlan(plan_id);
	}

	/* (非 Javadoc) 
	* Title: getElective_servers
	* Description:
	* @return 
	* @see com.inter.SystemServerInter#getElective_servers() 
	*/
	@Override
	public List<ElectiveServer> getElective_servers() {
		if (isLoaded()) {
			List<ElectiveServer> result = new ArrayList<ElectiveServer>();
			for (ElectiveServer e : ElectiveSys.electives.values()) {
				result.add(e);
			}
			return result;
		}
		return new ArrayList<ElectiveServer>();
	}

}
