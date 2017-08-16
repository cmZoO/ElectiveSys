/**   
* @Title: PlanServer.java 
* @Package com.server 
* @Description: TODO(描述PlanServer类) 
* @author zx583   
* @date 2017年4月21日 上午10:10:46 
* @version V1.0   
*/
package com.server;

import java.util.List;

import com.bean.Elective_time;
import com.bean.Plan;
import com.dao.Elective_timeDao;
import com.dao.PlanDao;
import com.inter.PlanServerInter;
import com.sun.org.apache.regexp.internal.recompile;
import com.util.CheckUtil;
import com.util.Page;
import com.util.RegexUtil;

/** 
* @ClassName: PlanServer 
* @Description: TODO(选课计划服务) 
* @author zx583 
* @date 2017年4月21日 上午10:10:46 
*  
*/
public class PlanServer implements PlanServerInter{
	/** 
	* @Fields dao : TODO(Plan的dao类) 
	*/ 
	PlanDao dao = new PlanDao();
	
	/* (非 Javadoc) 
	* Title: getAllPlan
	* Description:
	* @return 
	* @see com.inter.PlanServerInter#getAllPlan() 
	*/
	@Override
	public List<Plan> getAllPlan() {
		return dao.query(new Plan(), null);
	}

	/* (非 Javadoc) 
	* Title: getPlan
	* Description:
	* @param plan_id
	* @return 
	* @see com.inter.PlanServerInter#getPlan(java.lang.String) 
	*/
	@Override
	public Plan getPlan(String plan_id) {
		if (CheckUtil.nullCheck(plan_id)) {
			return null;
		}
		
		if (!RegexUtil.isAllNum(plan_id)) {
			return null;
		}
		
		List<Plan> result = dao.query(new Plan(Integer.parseInt(plan_id)), null);
		
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	/* (非 Javadoc) 
	* Title: deletePlanById
	* Description:
	* @param plan_id
	* @return 
	* @see com.inter.PlanServerInter#deletePlanById(java.lang.String) 
	*/
	@Override
	public boolean deletePlanById(String plan_id) {
		if (CheckUtil.nullCheck(plan_id)) {
			return false;
		}
		
		if (!RegexUtil.isAllNum(plan_id)) {
			return false;
		}
		
		return dao.delete(new Plan(Integer.parseInt(plan_id))) == 1;
	}

	/* (非 Javadoc) 
	* Title: deleteAllPlan
	* Description:
	* @return 
	* @see com.inter.PlanServerInter#deleteAllPlan() 
	*/
	@Override
	public boolean deleteAllPlan() {
		return dao.delete(new Plan()) > 0;
	}

	/* (非 Javadoc) 
	* Title: updatePlan
	* Description:
	* @param plan
	* @param plan_id
	* @return 
	* @see com.inter.PlanServerInter#updatePlan(com.bean.Plan, java.lang.Integer) 
	*/
	@Override
	public boolean updatePlan(Plan plan, String plan_id) {
		if (CheckUtil.nullCheck(plan, plan_id)) {
			return false;
		}
		
		if (!RegexUtil.isAllNum(plan_id)) {
			return false;
		}
		
		plan.setPlan_id(Integer.parseInt(plan_id));
		
		return dao.update(plan) == 1;
	}

	/* (非 Javadoc) 
	* Title: insertPlan
	* Description:
	* @param plan
	* @return 
	* @see com.inter.PlanServerInter#insertPlan(com.bean.Plan) 
	*/
	@Override
	public boolean insertPlan(Plan plan) {
		if (CheckUtil.nullCheck(plan)) {
			return false;
		}
		
		return dao.insert(plan) == 1 && insertElectiveTimesForPlan(plan.getElective_Times(), plan);
	}

	/* (非 Javadoc) 
	* Title: insertElectiveTimesForPlan
	* Description:
	* @param times
	* @param plan
	* @return 
	* @see com.inter.PlanServerInter#insertElectiveTimesForPlan(java.util.List, com.bean.Plan) 
	*/
	@Override
	public boolean insertElectiveTimesForPlan(List<Elective_time> times, Plan plan) {
		if (CheckUtil.nullCheck(times, plan)) {
			return false;
		}
		
		boolean result = true;
		Elective_timeDao timeDao = new Elective_timeDao();
		
		for (Elective_time t : times) {
			t.setPlan(plan);
			result = timeDao.insert(t) == 1;
		}
		
		return result;
	}

	/* (非 Javadoc) 
	* Title: deleteElectiveTimesForPlan
	* Description:
	* @param plan
	* @return 
	* @see com.inter.PlanServerInter#deleteElectiveTimesForPlan(com.bean.Plan) 
	*/
	@Override
	public boolean deleteElectiveTimesForPlan(Plan plan) {
		if (CheckUtil.nullCheck(plan)) {
			return false;
		}
		
		Elective_timeDao timeDao = new Elective_timeDao();
		Elective_time time = new Elective_time();
		time.setPlan(plan);
		
		return timeDao.delete(time) > 0;
	}

	/* (非 Javadoc) 
	* Title: getPagePlan
	* Description:
	* @param page
	* @return 
	* @see com.inter.PlanServerInter#getPagePlan(com.util.Page) 
	*/
	@Override
	public List<Plan> getPagePlan(Page page) {
		return dao.query(new Plan(), page);
	}

	/* (非 Javadoc) 
	* Title: getTotal
	* Description:
	* @return 
	* @see com.inter.PlanServerInter#getTotal() 
	*/
	@Override
	public Integer getTotal() {
		return dao.getTotal();
	}

}
