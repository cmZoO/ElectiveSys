/**   
* @Title: Elective_timeDao.java 
* @Package com.dao 
* @Description: TODO(描述Elective_timeDao类) 
* @author zx583   
* @date 2017年4月16日 下午4:49:49 
* @version V1.0   
*/
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Elective_time;
import com.bean.Plan;
import com.inter.BaseDao;
import com.inter.BeanDao;
import com.jdbc.JDBC;
import com.util.CheckUtil;
import com.util.DaoUtil;
import com.util.Page;

/** 
* @ClassName: Elective_timeDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月16日 下午4:49:49 
*  
*/
public class Elective_timeDao implements BaseDao<Elective_time>, BeanDao<Elective_time> {
	private static final String table_name = " elective_times ";
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
	* Description:查询elective_time
	* @param t
	* @param page
	* @return elective_time查询结果的集合
	* @see com.inter.BaseDao#query(java.lang.Object, com.util.Page) 
	*/
	@Override
	public List<Elective_time> query(Elective_time time, Page page) {
		List<Elective_time> result = new ArrayList<Elective_time>();
		
		if (time == null) {
			return result;
		}
		
		//去除干扰因子
		Plan jam = time.getPlan();
		time.setPlan(null);
		
		//获取连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = "select * from " + table_name;
		//添加类属性对应的条件
		if (jam != null) {
			Plan p = new Plan(jam.getPlan_id(), null, null, null);
			sql = DaoUtil.auto_where_sql(sql, p, false);
		}
		sql = DaoUtil.auto_where_sql(sql, time, page != null);
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps设值
			if (jam != null) {
				ps.setInt(1, jam.getPlan_id());
			}
			int index = DaoUtil.setString(ps, time, true, jam == null ? 1 : 2);

			//为limit赋值
			if (page != null) {
				ps.setObject(index++, page.getFirstRowOfCurrentPage() - 1);
				ps.setObject(index++, page.getPageRow());
			}

			//执行查询
			rs = ps.executeQuery();

			//处理结果
			while (rs.next()) {
				Elective_time tTime = new Elective_time();

				//为该变量赋值
				DaoUtil.initObject(rs, tTime, DaoUtil.getColumsName(conn, table_name));
				if (jam != null) {
					tTime.setPlan(jam);
				} else {
					Plan p = new Plan(rs.getInt("plan_id"), null, null, null);
					List<Plan> l = new PlanDao().query(p, null);
					if (l.size() == 1) {
						tTime.setPlan(l.get(0));
					}
				}
				
				result.add(tTime);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			//恢复干扰因子
			time.setPlan(jam);
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
	public int delete(Elective_time time) {
		if (time == null) {
			return 0;
		}
		
		//获取连接
		conn = JDBC.getConnection();
		
		//去除干扰因子
		Plan jam = time.getPlan();
		time.setPlan(null);
		
		try {
			//构造sql
			String sql = "delete from " + table_name;
			//添加类属性对应的条件
			if (jam != null) {
				Plan p = new Plan(jam.getPlan_id(), null, null, null);
				sql = DaoUtil.auto_where_sql(sql, p, false);
			}
			sql = DaoUtil.auto_where_sql(sql, time, false);
			
			//获取ps
			ps = conn.prepareStatement(sql);
			
			//为ps设值
			if (jam != null) {
				ps.setInt(1, jam.getPlan_id());
			}
			DaoUtil.setString(ps, time, false, jam == null ? 1 : 2);
			
			//执行并处理结果
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			time.setPlan(jam);
			close();
		}
		
		return 0;
	}
	/* (非 Javadoc) 
	* Title: update
	* Description:
	* @param t
	* @return 
	* @see com.inter.BaseDao#update(java.lang.Object) 
	*/
	@Override
	public int update(Elective_time time) {
		if (time == null || time.getTime_id() == null) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		//去除干扰因子
		Plan jam = time.getPlan();
		time.setPlan(null);

		Elective_time t = new Elective_time();
		t.setTime_id(time.getTime_id());
		//构造sql语句set部分
		String sql = "update " + table_name;
		if (jam != null) {
			Plan p = new Plan(jam.getPlan_id(), null, null, null);
			sql = DaoUtil.auto_set_sql(sql, p);
		}
		sql = DaoUtil.auto_set_sql(sql, time);
		//构造sql语句where部分
		sql = DaoUtil.auto_where_sql(sql, t, false);
		
//		System.out.println(sql);

		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			if (jam != null) {
				ps.setInt(1, jam.getPlan_id());
			}
			int index = DaoUtil.setString(ps, time, false, jam == null ? 1 : 2);
			DaoUtil.setString(ps, t, false, index);

			//执行sql并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			time.setPlan(jam);
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
	public int insert(Elective_time time) {
		if (CheckUtil.allNullBeanCheck(time)) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		//去除干扰因子
		Plan jam = time.getPlan();
		time.setPlan(null);

		try {
			//构造sql
			String sql = "insert into " + table_name;
			if (jam != null) {
				Plan p = new Plan();
				p.setPlan_id(jam.getPlan_id());
				sql = DaoUtil.auto_insert_sql(sql, p);
			}
			sql = DaoUtil.auto_insert_sql(sql, time);
			
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			if (jam != null) {
				ps.setInt(1, jam.getPlan_id());
			}
			DaoUtil.setString(ps, time, false, jam == null ? 1 : 2);

			//执行插入并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			//恢复干扰因子
			time.setPlan(jam);
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
}
