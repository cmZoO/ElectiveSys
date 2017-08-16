/**   
* @Title: ElectiveObject.java 
* @Package com.server 
* @Description: TODO(描述ElectiveObject类) 
* @author zx583   
* @date 2017年4月21日 下午10:18:30 
* @version V1.0   
*/
package com.server;

import com.bean.Course;
import com.inter.ElectiveServer;
import com.util.CheckUtil;
import com.util.DateUtil;
import com.util.RegexUtil;

/** 
* @ClassName: ElectiveObject 
* @Description: TODO(选课对象) 
* @author zx583 
* @date 2017年4月21日 下午10:18:30 
*  
*/
public class ElectiveObject implements ElectiveServer {
	/** 
	* @Fields course_id : TODO(该选课对象操作的课程id) 
	*/ 
	private String course_id = null;
	/** 
	* @Fields total0 : TODO(该选课对象可选总数0) 
	*/ 
	private Integer total0 = 0;
	/** 
	* @Fields total1 : TODO(该选课对象可选总数1) 
	*/ 
	private Integer total1 = 0;
	/** 
	* @Fields recordServer : TODO(记录操作服务) 
	*/ 
	private RecordServer recordServer = null;
	/** 
	* @Fields totalTime : TODO(当前选课时间段) 
	*/ 
	private Long TimePark;
	
	
	/* (非 Javadoc) 
	* Title: choice
	* Description:
	* @param stu_id
	* @return 
	* @see com.inter.ElectiveServer#choice(java.lang.String) 
	*/
	@Override
	public String choice(String stu_id) {
		//非法判断
		if (CheckUtil.nullCheck(stu_id) || !RegexUtil.isAllNum(stu_id) || !new SystemServer().isLoaded()) {
			return illegal;
		}
		
		//加锁以修改total
		synchronized (total0) {
			//判断是否发生了时间段的改变
			Long newTimePark = DateUtil.getTimePark(new SystemServer().getDelay_drop_time());
			if (TimePark == null || newTimePark.compareTo(TimePark) != 0) {
				//改变了时间段，修改总数以及时间段
				TimePark = newTimePark;
				total0 = total1;
//				total0 = Integer.max(total0, total1);
//				total1 = total0;
			}
			
			//				System.out.println("我访问了");
			//判断是否有选课名额,有则选课
			if (total0 > 0) {
				total0 -= 1;
				total1 -= 1;
			} else {
				return fail;
			}
		}
				
		if (recordServer.insertRecord(stu_id, course_id)) {
			return success;
		}
		
		//选课失败，恢复可选个数
		synchronized (total0) {
			total1 += 1;
			total0 += 1;
		}
		
		return fail;
	}
	
	/* (非 Javadoc) 
	* Title: dechoice
	* Description:
	* @param stu_id
	* @return 
	* @see com.inter.ElectiveServer#dechoice(java.lang.String) 
	*/
	@Override
	public String dechoice(String stu_id) {
		//非法判断
		if (CheckUtil.nullCheck(stu_id) || !RegexUtil.isAllNum(stu_id) || !new SystemServer().isLoaded()) {
			return illegal;
		}
		
		if (!recordServer.deleteRecord(stu_id, course_id)) {
			return fail;
		}
		
		//加锁以修改total
		synchronized (total0) {
			//判断是否发生了时间段的改变
//			Long newTimePark = DateUtil.getTimePark(new SystemServer().getDelay_drop_time());
//			if (TimePark == null || newTimePark.compareTo(TimePark) != 0) {
//				//改变了时间段，修改总数以及时间段
//				TimePark = newTimePark;
//				total0 = Integer.max(total0, total1);
//				total1 = total0;
//			}
			
			total1 += 1;
		}
		
		return success;
	}

	/* (非 Javadoc) 
	* Title: initServer
	* Description:
	* @param course 
	* @see com.inter.ElectiveServer#initServer(com.bean.Course) 
	*/
	@Override
	public synchronized void initServer(Course course) {
		// TODO Auto-generated method stub
		if (CheckUtil.nullCheck(course, course.getCourse_id(), course.getTotal())) {
			return;
		}
		
		//变量的初始化
		recordServer = new RecordServer();
		course_id = course.getCourse_id().toString();
		synchronized (total0) {
			TimePark = DateUtil.getTimePark(new SystemServer().getDelay_drop_time());
			total0 = total1 = course.getTotal() - recordServer.getCountOfCourse(course_id);
		}
	}

	/* (非 Javadoc) 
	* Title: getTotal
	* Description:
	* @return 
	* @see com.inter.TotalInter#getTotal() 
	*/
	@Override
	public Integer getTotal() {
		SystemServer syServer = new SystemServer();
		if (!syServer.isLoaded()) {
			//系统无选课计划,可选个数为0
			return 0;
		}
		
		synchronized (total0) {
			//判断是否发生了时间段的改变
			Long newTimePark = DateUtil.getTimePark(syServer.getDelay_drop_time());
			if (TimePark == null || newTimePark.compareTo(TimePark) != 0) {
				//改变了时间段，修改总数以及时间段
				TimePark = newTimePark;
//				total0 = Integer.max(total0, total1);
//				total1 = total0;
				total0 = total1;
			}
			
		}

		return total0;
	}

	/* (非 Javadoc) 
	* Title: getNextToal
	* Description:
	* @return 
	* @see com.inter.TotalInter#getNextToal() 
	*/
	@Override
	public Integer getNextToal() {
		SystemServer syServer = new SystemServer();
		if (!syServer.isLoaded()) {
			//系统无选课计划,可选个数为0
			return 0;
		}
		
		synchronized (total0) {
			//判断是否发生了时间段的改变
			Long newTimePark = DateUtil.getTimePark(syServer.getDelay_drop_time());
			if (TimePark == null || newTimePark.compareTo(TimePark) != 0) {
				//改变了时间段，修改总数以及时间段
				TimePark = newTimePark;
//				total0 = Integer.max(total0, total1);
//				total1 = total0;
				total0 = total1;
			}
			
		}

		return total1;
	}

	/* (非 Javadoc) 
	* Title: getCourse_id
	* Description:
	* @return 
	* @see com.inter.ElectiveServer#getCourse_id() 
	*/
	@Override
	public String getCourse_id() {
		return this.course_id;
	}

}
