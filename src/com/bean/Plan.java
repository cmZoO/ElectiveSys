/**   
* @Title: Plan.java 
* @Package com.bean 
* @Description: TODO(描述Plan类) 
* @author zx583   
* @date 2017年4月15日 上午10:34:23 
* @version V1.0   
*/
package com.bean;

import java.sql.Date;
import java.util.List;

/** 
* @ClassName: Plan 
* @Description: TODO(描述一个选课计划) 
* @author zx583 
* @date 2017年4月15日 上午10:34:23 
*  
*/
public class Plan {
	/** 
	* @Fields plan_id : TODO(选课计划id) 
	*/ 
	private Integer plan_id;
	/** 
	* @Fields plan_name : TODO(选课计划名称) 
	*/ 
	private String plan_name;
	/** 
	* @Fields delay_drop_time : TODO(选课规则:延迟推选时间) 
	*/ 
	private Integer delay_drop_time;
	/** 
	* @Fields elective_Times : TODO(该选课计划的选课时间) 
	*/ 
	private List<Elective_time> elective_Times;
	/** 
	* Title: 
	* Description: TODO(无参构造函数)  
	*/
	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* Title: 
	* Description: TODO(有参构造方法) 
	* @param plan_id 
	*/
	public Plan(Integer plan_id) {
		super();
		this.plan_id = plan_id;
	}

	/** 
	* Title: 
	* Description: TODO(有参构造方法) 
	* @param plan_id
	* @param plan_name
	* @param start_date
	* @param end_date
	* @param delay_drop_time
	* @param frequency
	* @param elective_Times 
	*/
	public Plan(Integer plan_id, String plan_name, Integer delay_drop_time, List<Elective_time> elective_Times) {
		super();
		this.plan_id = plan_id;
		this.plan_name = plan_name;
		this.delay_drop_time = delay_drop_time;
		this.elective_Times = elective_Times;
	}

	/** 
	* @return paln_id 
	*/
	public Integer getPlan_id() {
		return plan_id;
	}
	/** 
	* @param paln_id 要设置的 paln_id 
	*/
	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}
	/** 
	* @return plan_name 
	*/
	public String getPlan_name() {
		return plan_name;
	}
	/** 
	* @param plan_name 要设置的 plan_name 
	*/
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	/** 
	* @return delay_drop_time 
	*/
	public Integer getDelay_drop_time() {
		return delay_drop_time;
	}
	/** 
	* @param delay_drop_time 要设置的 delay_drop_time 
	*/
	public void setDelay_drop_time(Integer delay_drop_time) {
		this.delay_drop_time = delay_drop_time;
	}
	
	/** 
	* @return elective_Times 
	*/
	public List<Elective_time> getElective_Times() {
		return elective_Times;
	}
	/** 
	* @param elective_Times 要设置的 elective_Times 
	*/
	public void setElective_Times(List<Elective_time> elective_Times) {
		this.elective_Times = elective_Times;
	}

	/* (非 Javadoc) 
	* Title: toString
	* Description:
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Plan [plan_id=" + plan_id + ", plan_name=" + plan_name + ", delay_drop_time=" + delay_drop_time
				+ ", elective_Times=" + (elective_Times==null?null:elective_Times.size()) + "]";
	}

}
