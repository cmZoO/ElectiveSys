/**   
* @Title: PlanServerInter.java 
* @Package com.inter 
* @Description: TODO(描述PlanServerInter类) 
* @author zx583   
* @date 2017年4月21日 上午10:10:59 
* @version V1.0   
*/
package com.inter;

import java.util.List;

import com.bean.Elective_time;
import com.bean.Plan;
import com.util.Page;

/** 
* @ClassName: PlanServerInter 
* @Description: TODO(选课计划服务所具有的功能) 
* @author zx583 
* @date 2017年4月21日 上午10:10:59 
*  
*/
public interface PlanServerInter {
	/** 
	* @Title: getAllPlan 
	* @Description: TODO(获取所有的选课计划) 
	* @param  - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public abstract List<Plan> getAllPlan();
	
	/** 
	* @Title: getPagePlan 
	* @Description: TODO(获取分页内的计划) 
	* @param @return - 设定文件 
	* @return List<Plan> - 返回类型 
	* @throws 
	*/
	public abstract List<Plan> getPagePlan(Page page);
	
	/** 
	* @Title: getTotal 
	* @Description: TODO(获取所有计划个数) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public abstract Integer getTotal();
	
	/** 
	* @Title: getPlan 
	* @Description: TODO(根据计划id获取选课计划) 
	* @param @param plan_id - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public abstract Plan getPlan(String plan_id);
	
	/** 
	* @Title: deletePlanById 
	* @Description: TODO(删除id为plan_id的计划) 
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deletePlanById(String plan_id);
	
	/** 
	* @Title: deleteAllPlan 
	* @Description: TODO(删除所有选课计划) 
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteAllPlan();
	
	/** 
	* @Title: updatePlan 
	* @Description: TODO(用plan更新id为plan_id的计划) 
	* @param @param plan
	* @param @param plan_id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean updatePlan(Plan plan, String plan_id);
	
	/** 
	* @Title: insert 
	* @Description: TODO(插入一个新的计划) 
	* @param @param plan
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean insertPlan(Plan plan);
	
	/** 
	* @Title: insertElectiveTimesForPlan 
	* @Description: TODO(为一个计划添加选课时间) 
	* @param @param times
	* @param @param plan
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean insertElectiveTimesForPlan(List<Elective_time> times, Plan plan);
	
	/** 
	* @Title: deleteElectiveTimesForPlan 
	* @Description: TODO(删除一个计划的选课时间) 
	* @param @param plan
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteElectiveTimesForPlan(Plan plan);
}
