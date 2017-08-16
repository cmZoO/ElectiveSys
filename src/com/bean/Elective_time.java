/**   
* @Title: Elective_time.java 
* @Package com.bean 
* @Description: TODO(描述Elective_time类) 
* @author zx583   
* @date 2017年4月15日 上午10:39:38 
* @version V1.0   
*/
package com.bean;

import java.sql.Date;
import java.sql.Timestamp;

/** 
* @ClassName: Elective_time 
* @Description: TODO(选课时间描述表) 
* @author zx583 
* @date 2017年4月15日 上午10:39:38 
*  
*/
public class Elective_time {
	/** 
	* @Fields time_id : TODO(选课时间id) 
	*/ 
	private Integer time_id;
	/** 
	* @Fields plan : TODO(本选课时间所在的选课计划) 
	*/ 
	private Plan plan;
	/** 
	* @Fields register_date : TODO(选课规格:限制可选课的年纪) 
	*/ 
	private Date register_date;
	/** 
	* @Fields start_end : TODO(选课时间开始时间) 
	*/ 
	private Timestamp start_time;
	/** 
	* @Fields end_time : TODO(选课时间结束时间) 
	*/ 
	private Timestamp end_time;
	/** 
	 * @Fields max : TODO(最大选课个数) 
	 */ 
	private Integer max;
	/** 
	* Title: 
	* Description: TODO(无参构造函数)  
	*/
	public Elective_time() {
		super();
		// TODO Auto-generated constructor stub
	}
	/** 
	* Title: 
	* Description: TODO(有参构造函数) 
	* @param time_id
	* @param plan
	* @param register_date
	* @param start_time
	* @param end_time 
	*/
	public Elective_time(Integer time_id, Plan plan, Date register_date, Timestamp start_time, Timestamp end_time, Integer max) {
		super();
		this.time_id = time_id;
		this.plan = plan;
		this.register_date = register_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.max = max;
	}
	/** 
	* @return time_id 
	*/
	public Integer getTime_id() {
		return time_id;
	}
	/** 
	* @param time_id 要设置的 time_id 
	*/
	public void setTime_id(Integer time_id) {
		this.time_id = time_id;
	}
	
	/** 
	* @return max 
	*/
	public Integer getMax() {
		return max;
	}
	/** 
	* @param max 要设置的 max 
	*/
	public void setMax(Integer max) {
		this.max = max;
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
	* @return register_date 
	*/
	public Date getRegister_date() {
		return register_date;
	}
	/** 
	* @param register_date 要设置的 register_date 
	*/
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	/** 
	* @return start_time 
	*/
	public Timestamp getStart_time() {
		return start_time;
	}
	/** 
	* @param start_time 要设置的 start_time 
	*/
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	/** 
	* @return end_time 
	*/
	public Timestamp getEnd_time() {
		return end_time;
	}
	/** 
	* @param end_time 要设置的 end_time 
	*/
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	/* (非 Javadoc) 
	* Title: toString
	* Description:
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Elective_time [time_id=" + time_id + ", plan=" + (plan==null?null:plan.getPlan_name()) + ", register_date=" + register_date
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", max=" + max + "]";
	}
	
	
}
