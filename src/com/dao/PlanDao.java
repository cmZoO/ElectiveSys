/**   
 * @Title: PlanDao.java 
 * @Package com.dao 
 * @Description: TODO(描述PlanDao类) 
 * @author zx583   
 * @date 2017年4月16日 下午3:36:47 
 * @version V1.0   
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.Elective_time;
import com.bean.Plan;
import com.inter.BaseDao;
import com.inter.BeanDao;
import com.inter.PlanDaoInter;
import com.jdbc.JDBC;
import com.util.CheckUtil;
import com.util.DaoUtil;
import com.util.Page;

/** 
 * @ClassName: PlanDao 
 * @Description: TODO(选课计划Dao类) 
 * @author zx583 
 * @date 2017年4月16日 下午3:36:47 
 *  
 */
public class PlanDao implements BaseDao<Plan>, BeanDao<Plan>, PlanDaoInter {
	private static final String table_name = " elective_plans ";
	/** 
	 * @Fields conn : TODO(数据库连接) 
	 */ 
	private Connection conn = null;
	/** 
	 * @Fields ps : TODO(执行sql语句的类) 
	 */ 
	private PreparedStatement ps = null;
	/** 
	 * @Fields rs : TODO(sql执行完毕得到的结果集) 
	 */ 
	private ResultSet rs = null;
	/* (非 Javadoc) 
	 * Title: getTotal
	 * Description:
	 * @return 
	 * @see com.inter.BeanDao#getTotal() 
	 */
	@Override
	public Integer getTotal() {
		//获取连接
		conn = JDBC.getConnection();

		//构造sql
		String sql = "select count(*) as result from " + table_name;

		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//执行查询
			rs = ps.executeQuery();

			//处理结果
			if (rs.next()) {
				return rs.getInt("result");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return 0;
	}
	/* (非 Javadoc) 
	 * Title: query
	 * Description:	查询Plan
	 * @param t
	 * @param page
	 * @return Plan查询结果的集合
	 * @see com.inter.BaseDao#query(java.lang.Object, com.util.Page) 
	 */
	@Override
	public List<Plan> query(Plan plan, Page page) {
		List<Plan> result = new ArrayList<Plan>();

		if (plan == null) {
			return result;
		}

		//去除干扰因子
		List<Elective_time> jam = plan.getElective_Times();
		plan.setElective_Times(null);

		//获取连接
		conn = JDBC.getConnection();

		//构造sql
		String sql = DaoUtil.auto_where_sql("select * from " + table_name, plan, page != null);

		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps设值
			int index = DaoUtil.setString(ps, plan, true, 1);

			//为limit赋值
			if (page != null) {
				ps.setObject(index++, page.getFirstRowOfCurrentPage() - 1);
				ps.setObject(index++, page.getPageRow());
			}

			//执行查询
			rs = ps.executeQuery();

			//处理结果
			while (rs.next()) {
				Plan tPlan = new Plan();

				//为该变量赋值
				DaoUtil.initObject(rs, tPlan, DaoUtil.getColumsName(conn, table_name));
				
				tPlan.setElective_Times(queryElective_timesOfPlan(tPlan));

				result.add(tPlan);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			//恢复干扰因子
			plan.setElective_Times(jam);
		}

		return result;
	}
	/* (非 Javadoc) 
	 * Title: delete
	 * Description:
	 * @param t
	 * @return 
	 * @see com.inter.BaseDao#delete(java.lang.Object) 
	 */
	@Override
	public int delete(Plan plan) {
		if (plan == null) {
			return 0;
		}
		
		//去除干扰因子
		List<Elective_time> jam = plan.getElective_Times();
		plan.setElective_Times(null);
		
		//获取连接
		conn = JDBC.getConnection();
		
		try {
			//获取ps
			ps = conn.prepareStatement(DaoUtil.auto_where_sql("delete from " + table_name, plan, false));
			
			//为ps设值
			DaoUtil.setString(ps, plan, false, 1);
			
			//执行并处理结果
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			//恢复干扰因子
			plan.setElective_Times(jam);
		}
		
		return 0;		
	}
	/* (非 Javadoc) 
	 * Title: update
	 * Description:使用Plan的id更新表中对应的数据
	 * @param t
	 * @return 
	 * @see com.inter.BaseDao#update(java.lang.Object) 
	 */
	@Override
	public int update(Plan plan) {
		if (plan == null || plan.getPlan_id() == null) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		//去除干扰因子
		List<Elective_time> jam = plan.getElective_Times();
		plan.setElective_Times(null);

		Plan p = new Plan();
		p.setPlan_id(plan.getPlan_id());
		//构造sql语句set部分
		String sql = DaoUtil.auto_set_sql("update " + table_name, plan);
		//构造sql语句where部分
		sql = DaoUtil.auto_where_sql(sql, p, false);

		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			int index = DaoUtil.setString(ps, plan, false, 1);
			DaoUtil.setString(ps, p, false, index);

			//执行sql并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			//恢复干扰因子
			plan.setElective_Times(jam);
		}

		return 0;
	}
	/* (非 Javadoc) 
	 * Title: insert
	 * Description:
	 * @param t
	 * @return 
	 * @see com.inter.BaseDao#insert(java.lang.Object) 
	 */
	@Override
	public int insert(Plan plan) {
		if (CheckUtil.allNullBeanCheck(plan)) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		//去除干扰因子
		List<Elective_time> jam = plan.getElective_Times();
		plan.setElective_Times(null);

		try {
			//获取ps
			ps = conn.prepareStatement(DaoUtil.auto_insert_sql("insert into " + table_name, plan));

			//为ps赋值
			DaoUtil.setString(ps, plan, false, 1);

			//执行插入并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		} finally {
			//恢复干扰因子
			plan.setElective_Times(jam);
		}

		return 0;
	}

	/* (非 Javadoc) 
	 * Title: close
	 * Description: 
	 * @see com.inter.BaseDao#close() 
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (conn != null) {
			JDBC.close(conn);
		}

		if (ps != null) {
			JDBC.close(ps);
		}

		if (rs != null) {
			JDBC.close(rs);
		}
	}
	/* (非 Javadoc) 
	* Title: queryElective_timesOfPlan
	* Description:
	* @param plan
	* @return 
	* @see com.inter.PlanDaoInter#queryElective_timesOfPlan(com.bean.Plan) 
	*/
	@Override
	public List<Elective_time> queryElective_timesOfPlan(Plan plan) {
		Elective_time time = new Elective_time();
		time.setPlan(plan);
		return new Elective_timeDao().query(time, null);
	}
	/* (非 Javadoc) 
	* Title: queryCoursesOfPlan
	* Description:
	* @param plan
	* @return 
	* @see com.inter.PlanDaoInter#queryCoursesOfPlan(com.bean.Plan) 
	*/
	@Override
	public List<Course> queryCoursesOfPlan(Plan plan) {
		Course course = new Course();
		course.setPlan(plan);
		
		return new CourseDao().query(course, null); 
	}

}
