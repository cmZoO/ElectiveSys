/**   
* @Title: AdminDao.java 
* @Package com.dao 
* @Description: TODO(描述AdminDao类) 
* @author zx583   
* @date 2017年4月15日 下午9:51:28 
* @version V1.0   
*/
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Admin;
import com.inter.BaseDao;
import com.inter.BeanDao;
import com.jdbc.JDBC;
import com.util.CheckUtil;
import com.util.DaoUtil;
import com.util.Page;

/** 
* @ClassName: AdminDao 
* @Description: TODO(管理员信息Dao类) 
* @author zx583 
* @date 2017年4月15日 下午9:51:28 
*  
*/
public class AdminDao implements BaseDao<Admin>, BeanDao<Admin> {
	private static final String table_name = " administrators ";
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
	* Title: query
	* Description:
	* @param t
	* @return 
	* @see com.inter.BaseDao#query(java.lang.Object) 
	*/
	@Override
	public List<Admin> query(Admin admin, Page page) {
		List<Admin> result = new ArrayList<Admin>();
		
		if (admin == null) {
			return result;
		}
		
		//获取连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = DaoUtil.auto_where_sql("select * from" + table_name, admin, page != null);
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);
			
			//为ps设值
			int index = DaoUtil.setString(ps, admin, true, 1);
			
			//为limit赋值
			if (page != null) {
				ps.setObject(index++, page.getFirstRowOfCurrentPage() - 1);
				ps.setObject(index++, page.getPageRow());
			}
			
			//执行查询
			rs = ps.executeQuery();
			
			//处理结果
			while (rs.next()) {
				Admin tAdmin = new Admin();
				
				//为该变量赋值
				DaoUtil.initObject(rs, tAdmin, null);
				
				result.add(tAdmin);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
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
	public int delete(Admin admin) {
		if (admin == null) {
			return 0;
		}
		
		//获取连接
		conn = JDBC.getConnection();
				
		// TODO Auto-generated method stub
		try {
			//获取ps
			ps = conn.prepareStatement(DaoUtil.auto_where_sql("delete from" + table_name, admin, false));
			
			//为ps设值
			DaoUtil.setString(ps, admin, false, 1);
			
			//执行并处理结果
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	public int update(Admin admin) {
		// TODO Auto-generated method stub
		if (admin == null || admin.getAdmin_id() == null) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		Admin s = new Admin();
		s.setAdmin_id(admin.getAdmin_id());
		//构造sql语句set部分
		String sql = DaoUtil.auto_set_sql("update " + table_name, admin);
		//构造sql语句where部分
		sql = DaoUtil.auto_where_sql(sql, s, false);

		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			int index = DaoUtil.setString(ps, admin, false, 1);
			DaoUtil.setString(ps, s, false, index);

			//执行sql并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
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
	public int insert(Admin admin) {
		// TODO Auto-generated method stub
		if (CheckUtil.allNullBeanCheck(admin)) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		try {
			//获取ps
			ps = conn.prepareStatement(DaoUtil.auto_insert_sql("insert into " + table_name, admin));

			//为ps赋值
			DaoUtil.setString(ps, admin, false, 1);

			//执行插入并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
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
	
}
