/**   
* @Title: Course.java 
* @Package com.bean 
* @Description: TODO(描述Course类) 
* @author zx583   
* @date 2017年4月15日 上午10:46:13 
* @version V1.0   
*/
package com.bean;

import java.util.List;

/** 
* @ClassName: Course 
* @Description: TODO(选修课程描述类) 
* @author zx583 
* @date 2017年4月15日 上午10:46:13 
*  
*/
public class Course {
	/** 
	* @Fields course_id : TODO(选修课程id) 
	*/ 
	private Integer course_id;
	/** 
	* @Fields plan : TODO(该选修课程所在的选修计划) 
	*/ 
	private Plan plan;
	/** 
	* @Fields course_name : TODO(选修课程名称) 
	*/ 
	private String course_name;
	/** 
	* @Fields class_time : TODO(选修上课时间) 
	*/ 
	private Integer class_time;
	/** 
	* @Fields class_place : TODO(选修上课地点) 
	*/ 
	private String class_place;
	/** 
	* @Fields total : TODO(选修总人数) 
	*/ 
	private Integer total;
	/** 
	* @Fields course_teac : TODO(教导该课程的老师) 
	*/ 
	private List<Teacher> course_teac;
	/** 
	* Title: 
	* Description: TODO(无参构造方法)  
	*/
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* Title: 
	* Description: TODO(有参构造方法) 
	* @param course_id 
	*/
	public Course(Integer course_id) {
		super();
		this.course_id = course_id;
	}
	
	/** 
	* Title: 
	* Description: TODO(有参构造方法) 
	* @param course_id
	* @param plan
	* @param course_name
	* @param class_time
	* @param class_place
	* @param course_teac 
	*/
	public Course(Integer course_id, Plan plan, String course_name, Integer class_time, String class_place, Integer total,
			List<Teacher> course_teac) {
		super();
		this.course_id = course_id;
		this.plan = plan;
		this.course_name = course_name;
		this.class_time = class_time;
		this.class_place = class_place;
		this.total = total;
		this.course_teac = course_teac;
	}

	/** 
	* @return course_id 
	*/
	public Integer getCourse_id() {
		return course_id;
	}
	/** 
	* @param course_id 要设置的 course_id 
	*/
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	/** 
	* @return plan 
	*/
	public Plan getPlan() {
		return plan;
	}
	/** 
	* @param plan 要设置的 plan 
	*/
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	/** 
	* @return course_name 
	*/
	public String getCourse_name() {
		return course_name;
	}
	/** 
	* @param course_name 要设置的 course_name 
	*/
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	/** 
	* @return class_time 
	*/
	public Integer getClass_time() {
		return class_time;
	}
	/** 
	* @param class_time 要设置的 class_time 
	*/
	public void setClass_time(Integer class_time) {
		this.class_time = class_time;
	}
	/** 
	* @return class_place 
	*/
	public String getClass_place() {
		return class_place;
	}
	/** 
	* @param class_place 要设置的 class_place 
	*/
	public void setClass_place(String class_place) {
		this.class_place = class_place;
	}
	

	/** 
	* @return total 
	*/
	public Integer getTotal() {
		return total;
	}

	/** 
	* @param total 要设置的 total 
	*/
	public void setTotal(Integer total) {
		this.total = total;
	}

	/** 
	* @return course_teac 
	*/
	public List<Teacher> getCourse_teac() {
		return course_teac;
	}

	/** 
	* @param course_teac 要设置的 course_teac 
	*/
	public void setCourse_teac(List<Teacher> course_teac) {
		this.course_teac = course_teac;
	}

	/* (非 Javadoc) 
	* Title: toString
	* Description:
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", plan=" + (plan == null ? null : plan.getPlan_name()) + ", course_name=" + course_name + ", class_time="
				+ class_time + ", class_place=" + class_place + ", total=" + total + ", course_teac=" + course_teac
				+ "]";
	}

	
	
}
