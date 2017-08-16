/**   
 * @Title: CourseDao.java 
 * @Package com.dao 
 * @Description: TODO(描述CourseDao类) 
 * @author zx583   
 * @date 2017年4月16日 下午3:11:18 
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
import com.bean.Student;
import com.bean.Teacher;
import com.inter.BaseDao;
import com.inter.BeanDao;
import com.inter.CourseDaoInter;
import com.jdbc.JDBC;
import com.util.CheckUtil;
import com.util.DaoUtil;
import com.util.Page;

/** 
 * @ClassName: CourseDao 
 * @Description: TODO(课程信息Dao类) 
 * @author zx583 
 * @date 2017年4月16日 下午3:11:18 
 *  
 */
public class CourseDao implements BaseDao<Course>, BeanDao<Course>, CourseDaoInter {
	private static final String table_name = " courses ";
	private static final String table_c_t = " course_teacher ";
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
	public List<Course> query(Course course, Page page) {
		// TODO Auto-generated method stub
		List<Course> result = new ArrayList<Course>();
		
		if (course == null) {
			return result;
		}
		
		//去除干扰因子
		Plan jamP = course.getPlan();
		course.setPlan(null);
		List<Teacher> jamT = course.getCourse_teac();
		course.setCourse_teac(null);
		
		//获取连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = "select * from " + table_name;
		//添加类属性对应的条件
		if (jamP != null) {
			Plan p = new Plan(jamP.getPlan_id(), null, null, null);
			sql = DaoUtil.auto_where_sql(sql, p, false);
		}
		sql = DaoUtil.auto_where_sql(sql, course, page != null);
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps设值
			if (jamP != null) {
				ps.setInt(1, jamP.getPlan_id());
			}
			int index = DaoUtil.setString(ps, course, true, jamP == null ? 1 : 2);

			//为limit赋值
			if (page != null) {
				ps.setObject(index++, page.getFirstRowOfCurrentPage() - 1);
				ps.setObject(index++, page.getPageRow());
			}

			//执行查询
			rs = ps.executeQuery();

			//处理结果
			while (rs.next()) {
				Course tCour = new Course();

				//为该变量赋值
				DaoUtil.initObject(rs, tCour, DaoUtil.getColumsName(conn, table_name));
				if (jamP != null) {
					tCour.setPlan(jamP);
				} else {
					Plan p = new Plan(rs.getInt("plan_id"), null, null, null);
					List<Plan> l = new PlanDao().query(p, null);
					if (l.size() == 1) {
						tCour.setPlan(l.get(0));
					}
				}
				
				tCour.setCourse_teac(new CourseDao().queryTeacherOfCourse(tCour));
				
				result.add(tCour);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			//恢复干扰因子
			course.setPlan(jamP);
			course.setCourse_teac(jamT);
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
	public int delete(Course course) {
		if (course == null) {
			return 0;
		}
		
		//获取连接
		conn = JDBC.getConnection();
		
		//去除干扰因子
		Plan jamP = course.getPlan();
		course.setPlan(null);
		List<Teacher> jamT = course.getCourse_teac();
		course.setCourse_teac(null);
				
		try {
			//构造sql
			String sql = "delete from " + table_name;
			//添加类属性对应的条件
			if (jamP != null) {
				Plan p = new Plan(jamP.getPlan_id(), null, null, null);
				sql = DaoUtil.auto_where_sql(sql, p, false);
			}
			sql = DaoUtil.auto_where_sql(sql, course, false);
			
			//获取ps
			ps = conn.prepareStatement(sql);
			
			//为ps设值
			if (jamP != null) {
				ps.setInt(1, jamP.getPlan_id());
			}
			DaoUtil.setString(ps, course, false, jamP == null ? 1 : 2);
			
			//执行并处理结果
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//恢复干扰因子
			course.setPlan(jamP);
			course.setCourse_teac(jamT);
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
	public int update(Course course) {
		if (course == null || course.getCourse_id() == null) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		//去除干扰因子
		Plan jamP = course.getPlan();
		course.setPlan(null);
		List<Teacher> jamT = course.getCourse_teac();
		course.setCourse_teac(null);

		Course c = new Course();
		c.setCourse_id(course.getCourse_id());
		//构造sql语句set部分
		String sql = "update " + table_name;
		if (jamP != null) {
			Plan p = new Plan(jamP.getPlan_id(), null, null, null);
			sql = DaoUtil.auto_set_sql(sql, p);
		}
		sql = DaoUtil.auto_set_sql(sql, course);
		//构造sql语句where部分
		sql = DaoUtil.auto_where_sql(sql, c, false);
		
//		System.out.println(sql);

		try {
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			if (jamP != null) {
				ps.setInt(1, jamP.getPlan_id());
			}
			int index = DaoUtil.setString(ps, course, false, jamP == null ? 1 : 2);
			DaoUtil.setString(ps, c, false, index);

			//执行sql并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			//恢复干扰因子
			course.setPlan(jamP);
			course.setCourse_teac(jamT);
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
	public int insert(Course course) {
		if (CheckUtil.allNullBeanCheck(course)) {
			return 0;
		}

		//获取连接
		conn = JDBC.getConnection();

		//去除干扰因子
		Plan jamP = course.getPlan();
		course.setPlan(null);
		List<Teacher> jamT = course.getCourse_teac();
		course.setCourse_teac(null);

		try {
			//构造sql
			String sql = "insert into " + table_name;
			if (jamP != null) {
				Plan p = new Plan();
				p.setPlan_id(jamP.getPlan_id());
				sql = DaoUtil.auto_insert_sql(sql, p);
			}
			sql = DaoUtil.auto_insert_sql(sql, course);
//			System.out.println(sql);
			
//			System.out.println(sql);
			
			//获取ps
			ps = conn.prepareStatement(sql);

			//为ps赋值
			if (jamP != null) {
				ps.setInt(1, jamP.getPlan_id());
			}
			DaoUtil.setString(ps, course, false, jamP == null ? 1 : 2);

			//执行插入并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			//恢复干扰因子
			course.setPlan(jamP);
			course.setCourse_teac(jamT);
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
	* Title: queryTeacherOfCourse
	* Description:
	* @param course
	* @return 
	* @see com.inter.CourseDaoInter#queryTeacherOfCourse(com.bean.Course) 
	*/
	@Override
	public List<Teacher> queryTeacherOfCourse(Course course) {
		List<Teacher> result = new ArrayList<Teacher>();
		
		if (course == null || course.getCourse_id() == null) {
			return result;
		}
		
		//获取数据库连接
		conn = JDBC.getConnection();
		
		//构造查询条件
		Course c = new Course();
		c.setCourse_id(course.getCourse_id());
		
		//构造sql
		String sql = "select teac_id from " + table_c_t;
		sql = DaoUtil.auto_where_sql(sql, c, false);
				
		try {
			//获取ps
			ps = conn.prepareStatement(sql);
			//为ps赋值
			DaoUtil.setString(ps, c, false, 1);
			
			//执行查询并处理结果 
			rs = ps.executeQuery();
			List<String> cols = DaoUtil.getColumsName(conn, table_c_t);
			TeacherDao teacherDao = new TeacherDao();
			while (rs.next()) {
				Teacher tTeac = new Teacher();
				
				//初始化结果
				DaoUtil.initObject(rs, tTeac, cols);
				
				List<Teacher> list = teacherDao.query(tTeac, null);
				if (list.size() == 1) {
					result.add(list.get(0));
				}
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
	* Title: queryCourseOfTeacher
	* Description:
	* @param teacher
	* @return 
	* @see com.inter.CourseDaoInter#queryCourseOfTeacher(com.bean.Teacher) 
	*/
	@Override
	public List<Course> queryCourseOfTeacher(Teacher teacher) {
		List<Course> result = new ArrayList<Course>();
		
		if (teacher == null || teacher.getTeac_id() == null) {
			return result;
		}
		
		//获取数据库连接
		conn = JDBC.getConnection();
		
		//构造查询条件
		Teacher t = new Teacher();
		t.setTeac_id(teacher.getTeac_id());
		
		//构造sql
		String sql = "select course_id from " + table_c_t;
		sql = DaoUtil.auto_where_sql(sql, t, false);
				
		try {
			//获取ps
			ps = conn.prepareStatement(sql);
			//为ps赋值
			DaoUtil.setString(ps, t, false, 1);
			
			//执行查询并处理结果 
			rs = ps.executeQuery();
			List<String> cols = DaoUtil.getColumsName(conn, table_c_t);
			while (rs.next()) {
				Course tCour = new Course();
				
				//初始化结果
				DaoUtil.initObject(rs, tCour, cols);
				
				List<Course> list = new CourseDao().query(tCour, null);
				if (list.size() == 1) {
					result.add(list.get(0));
				}
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
	* Title: insertTeacherForCourse
	* Description:
	* @param teacher
	* @return 
	* @see com.inter.CourseDaoInter#insertTeacherForCourse(com.bean.Teacher) 
	*/
	@Override
	public int insertTeacherForCourse(Teacher teacher, Course course) {
		if (CheckUtil.nullCheck(teacher, course)) {
			return 0;
		}
		
		//获取数据库连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = "insert into " + table_c_t;
		Teacher t = new Teacher(teacher.getTeac_id());
		Course c = new Course(course.getCourse_id());
		sql = DaoUtil.auto_insert_sql(sql, t);
		sql = DaoUtil.auto_insert_sql(sql, c);
//		System.out.println(sql);
		try {
			//获取ps;
			ps = conn.prepareStatement(sql);
			
			//为ps赋值
			int index = DaoUtil.setString(ps, t, false, 1);
			DaoUtil.setString(ps, c, false, index);
			
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
	* Title: insertTeachersForCourse
	* Description:
	* @param teachers
	* @return 
	* @see com.inter.CourseDaoInter#insertTeachersForCourse(java.util.List) 
	*/
	@Override
	public int insertTeachersForCourse(List<Teacher> teachers, Course course) {
		if (CheckUtil.nullCheck(teachers, course) || teachers.size() == 0) {
			return 0;
		}
		
		//获取数据库连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = "insert into " + table_c_t;
		Course c = new Course(course.getCourse_id());
		sql = DaoUtil.auto_insert_sql(sql, new Teacher(1));
		sql = DaoUtil.auto_insert_sql(sql, c);
		sql = DaoUtil.plusValue_insert_sql(sql, teachers.size());
		
		try {
			//获取ps;
			ps = conn.prepareStatement(sql);
			
			//为ps赋值
			//循环插入每个老师
			int index = 1;
			for (int i = 0, len = teachers.size(); i < len; i++) {
				Teacher t = new Teacher(teachers.get(i).getTeac_id());
				
				index = DaoUtil.setString(ps, t, false, index);
				index = DaoUtil.setString(ps, c, false, index);
			}
			
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
	* Title: deleteByTeacherCourse
	* Description:
	* @param course
	* @return 
	* @see com.inter.CourseDaoInter#deleteTeacherOfCourse(com.bean.Course) 
	*/
	@Override
	public int deleteByTeacherCourse(Course course, Teacher teacher) {
		if (CheckUtil.nullCheck(course, teacher)) {
			return 0;
		}
		
		//获取连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = "delete from " + table_c_t;
		Teacher t = new Teacher(teacher.getTeac_id());
		sql = DaoUtil.auto_where_sql(sql, t, false);
		Course c = new Course(course.getCourse_id());
		sql = DaoUtil.auto_where_sql(sql, c, false);
		
		try {
			//获取ps
			ps = conn.prepareStatement(sql);
			
			//为ps赋值
			int index = DaoUtil.setString(ps, t, false, 1);
			DaoUtil.setString(ps, c, false, index);
			
			//执行并处理结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
		return 0;
	}
	/* (非 Javadoc) 
	* Title: getTotal
	* Description:
	* @param b
	* @return 
	* @see com.inter.BeanDao#getTotal(java.lang.Object) 
	*/
	@Override
	public Integer getTotal() {
		// TODO Auto-generated method stub
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
	* Title: deleteByCourse
	* Description:
	* @param course
	* @param teachers
	* @return 
	* @see com.inter.CourseDaoInter#deleteTeachersOfCourse(com.bean.Course, java.util.List) 
	*/
	@Override
	public int deleteByCourse(Course course) {
		if (CheckUtil.nullCheck(course)) {
			return 0;
		}
		
		//获取数据库连接
		conn = JDBC.getConnection();
		
		//构造sql
		String sql = "delete from " + table_c_t;
		Course c = new Course(course.getCourse_id());
		sql = DaoUtil.auto_where_sql(sql, c, false);
		
		try {
			//获取ps;
			ps = conn.prepareStatement(sql);
			
			DaoUtil.setString(ps, c, false, 1);
				
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
		
		return 0;
	}

}
