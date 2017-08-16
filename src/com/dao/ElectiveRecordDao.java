/**   
* @Title: ElectiveRecordDao.java 
* @Package com.dao 
* @Description: TODO(描述ElectiveRecordDao类) 
* @author zx583   
* @date 2017年4月19日 下午8:27:39 
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
import com.bean.ElectiveRecord;
import com.bean.Student;
import com.inter.BaseDao;
import com.inter.ElectiveRecordDaoInter;
import com.jdbc.JDBC;
import com.util.CheckUtil;
import com.util.DaoUtil;
import com.util.Page;

/** 
* @ClassName: ElectiveRecordDao 
* @Description: TODO(选课记录Dao类) 
* @author zx583 
* @date 2017年4月19日 下午8:27:39 
*  
*/
public class ElectiveRecordDao implements BaseDao<ElectiveRecord>, ElectiveRecordDaoInter {
	private static final String table_name = " elective_records ";
	
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
	* @param page
	* @return 
	* @see com.inter.BaseDao#query(java.lang.Object, com.util.Page) 
	*/
	@Override
	public List<ElectiveRecord> query(ElectiveRecord record, Page page) {
		List<ElectiveRecord> result = new ArrayList<ElectiveRecord>();
		
		if (record == null) {
			return result;
		}
		
		//获取连接
		conn = JDBC.getConnection();
		
		//去除干扰
		Student JamS = record.getStudent();
		record.setStudent(null);
		Course JamC = record.getCourse();
		record.setCourse(null);
		
		//构造sql
		String sql = "select * from " + table_name;
		if (JamS != null) {
			sql = DaoUtil.auto_where_sql(sql, new Student(JamS.getStu_id()), false);
		}
		if (JamC != null) {
			sql = DaoUtil.auto_where_sql(sql, new Course(JamC.getCourse_id()), false);
		}
		sql = DaoUtil.auto_where_sql(sql, record, false);
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			int index = 1;
			if (JamS != null) {
				index = DaoUtil.setString(ps, new Student(JamS.getStu_id()), false, index);
			}
			if (JamC != null) {
				index = DaoUtil.setString(ps, new Course(JamC.getCourse_id()), false, index);
			}
			index = DaoUtil.setString(ps, record, false, index);
			
			
			//执行语句
			rs = ps.executeQuery();
			
			List<String> cols = DaoUtil.getColumsName(conn, table_name);
			//处理结果
			while (rs.next()) {
				ElectiveRecord r = new ElectiveRecord();
				
				DaoUtil.initObject(rs, r, cols);
				
				List<Course> course = new CourseDao().query(new Course(rs.getInt("course_id")), null);
				if (course.size() == 1) {
					r.setCourse(course.get(0));
				}
				
				List<Student> student = new StudentDao().query(new Student(rs.getInt("stu_id")), null);
				if (student.size() == 1) {
					r.setStudent(student.get(0));
				}
				
				result.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//恢复干扰
			record.setCourse(JamC);
			record.setStudent(JamS);
		
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
	public int delete(ElectiveRecord record) {
		if (record == null) {
			return 0;
		}
		
		//获取连接
		conn = JDBC.getConnection();
		
		//去除干扰
		Student JamS = record.getStudent();
		record.setStudent(null);
		Course JamC = record.getCourse();
		record.setCourse(null);
		
		//构造sql
		String sql = "delete from " + table_name;
		if (JamS != null) {
			sql = DaoUtil.auto_where_sql(sql, new Student(JamS.getStu_id()), false);
		}
		if (JamC != null) {
			sql = DaoUtil.auto_where_sql(sql, new Course(JamC.getCourse_id()), false);
		}
		sql = DaoUtil.auto_where_sql(sql, record, false);
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			int index = 1;
			if (JamS != null) {
				index = DaoUtil.setString(ps, new Student(JamS.getStu_id()), false, index);
			}
			if (JamC != null) {
				index = DaoUtil.setString(ps, new Course(JamC.getCourse_id()), false, index);
			}
			DaoUtil.setString(ps, record, false, 1);
			
			//执行语句并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			//恢复干扰
			record.setCourse(JamC);
			record.setStudent(JamS);
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
	public int update(ElectiveRecord record) {
		//数据库设计出错
		
		//此dao update方法无效
		return 1;
	}
	/* (非 Javadoc) 
	* Title: insert
	* Description:
	* @param t
	* @return 
	* @see com.inter.BaseDao#insert(java.lang.Object) 
	*/
	@Override
	public int insert(ElectiveRecord record) {
		if (CheckUtil.allNullBeanCheck(record)) {
			return 0;
		}
		
		//获取连接
		conn = JDBC.getConnection();
		
		//去除干扰因子
		Student JamS = record.getStudent();
		record.setStudent(null);
		Course JamC = record.getCourse();
		record.setCourse(null);
		
		//构造sql
		String sql = "insert into" + table_name;
		sql = DaoUtil.auto_insert_sql(sql, record);
		if (JamS != null) {
			sql = DaoUtil.auto_insert_sql(sql, new Student(JamS.getStu_id()));
		}
		if (JamC != null) {
			sql = DaoUtil.auto_insert_sql(sql, new Course(JamC.getCourse_id()));
		}
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);
			
			//为ps赋值
			int index = DaoUtil.setString(ps, record, false, 1);
			if (JamS != null) {
				index = DaoUtil.setString(ps, new Student(JamS.getStu_id()), false, index);
			}
			if (JamC != null) {
				DaoUtil.setString(ps, new Course(JamC.getCourse_id()), false, index);
			}
			
			//执行语句并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//恢复干扰因子
			record.setCourse(JamC);
			record.setStudent(JamS);
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
	* Title: getCountOfCourse
	* Description:
	* @param course
	* @return 
	* @see com.inter.ElectiveRecordDaoInter#getCountOfCourse(com.bean.Course) 
	*/
	@Override
	public Integer queryCountOfCourse(Course course) {
		if (course == null || course.getCourse_id() == null) {
			return 0;
		}
		
		//获取数据库连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = "select count(*) as result from " + table_name;
		Course c = new Course(course.getCourse_id());
		sql = DaoUtil.auto_where_sql(sql, c, false);
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);
			
			//为ps赋值
			DaoUtil.setString(ps, c, false, 1);
			
			//执行ps并获取结果
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("result");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
		
		return 0;
	}
}
