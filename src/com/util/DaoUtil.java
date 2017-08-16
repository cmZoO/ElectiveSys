/**   
* @Title: DaoUtil.java 
* @Package com.util 
* @Description: TODO(描述DaoUtil类) 
* @author zx583   
* @date 2017年4月15日 下午3:45:14 
* @version V1.0   
*/
package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/** 
* @ClassName: DaoUtil 
* @Description: TODO(Dao工具类) 
* @author zx583 
* @date 2017年4月15日 下午3:45:14 
*  
*/
public class DaoUtil {
	/** 
	* @Title: add_insert_sql 
	* @Description: TODO(为insert语句补充一段值) 
	* @param @param sql
	* @param @param c
	* @param @param obj
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public static String add_insert_sql(String sql, Object obj) {
		if (CheckUtil.nullCheck(sql, obj)) {
			return sql;
		}
		
		Class c = obj.getClass();
		
		String nameP = "";
		String valueP = "";
		
		for (Field f : c.getDeclaredFields()) {
			Method getMethod = null;
			try {
				getMethod = c.getDeclaredMethod(ReflectUtil.getMethodForFied(f));
			} catch (SecurityException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			try {
				if (getMethod.invoke(obj) != null) {
					nameP = nameP +  " , "  + f.getName();
					valueP = valueP + ", ? ";
				}
			} catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		if (nameP.length() == 0 || valueP.length() == 0) {
			return sql;
		}
		
		int i = sql.indexOf(')');
		int j = sql.lastIndexOf(')');
		
		return sql.substring(0, i) + nameP + sql.substring(i, j) + valueP + sql.substring(j);
	}
	/** 
	* @Title: auto_insert_sql 
	* @Description: TODO(构造insert语句工具类) 
	* @param @param sql
	* @param @param c
	* @param @param obj
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public static String auto_insert_sql(String sql, Object obj) {
		if (CheckUtil.nullCheck(sql, obj)) {
			return sql;
		}
		
		Class c = obj.getClass();
		
		if (sql.indexOf("values(") != -1) {
			return add_insert_sql(sql, obj);
		}
		
		boolean flag = true;
		
		String parameter = " (";
		String values = " values(";
		
		for (Field f : c.getDeclaredFields()) {
			Method getMethod = null;
			try {
				getMethod = c.getDeclaredMethod(ReflectUtil.getMethodForFied(f));
			} catch (SecurityException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			try {
				if (getMethod.invoke(obj) != null) {
					if (flag) {
						parameter = parameter + f.getName();
						values = values + "?";
						flag = false;
					} else {
						parameter = parameter + ", " + f.getName();
						values = values + ", " + "?";
					}
				}
			} catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		if (!flag) {
			parameter = parameter + ")";
			values = values + ")";
			sql = sql + parameter + values;
		}
		
		return sql;
	}
	/** 
	* @Title: auto_set_sql 
	* @Description: TODO(构造update语句工具类) 
	* @param @param sql
	* @param @param c
	* @param @param obj
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public static String auto_set_sql(String sql, Object obj) {
		if (CheckUtil.nullCheck(sql, obj)) {
			return null;
		}
		
		Class c = obj.getClass();
		
		boolean flag = sql.indexOf("set") == -1;
		
		for (Field f : c.getDeclaredFields()) {
			Method getMethod = null;
			try {
				getMethod = c.getDeclaredMethod(ReflectUtil.getMethodForFied(f));
			} catch (SecurityException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			try {
				if (getMethod.invoke(obj) != null) {
					if (flag) {
						sql = sql + " set " + f.getName() + " = ? ";
						flag = false;
					} else {
						sql = sql + ", " + f.getName() + " = ? ";
					}
				}
			} catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		return sql;
	}
	
	/** 
	* @Title: auto_where_sql 
	* @Description: TODO(构造查询语句工具类) 
	* @param @param sql
	* @param @param c
	* @param @param obj
	* @param @param isLimit 是否附加limit条件
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public static String auto_where_sql(String sql, Object obj, boolean isLimit) {
		if (sql == null || obj == null) {
			return null;
		}
		
		Class c = obj.getClass();
		
		boolean flag = sql.indexOf("where") == -1;
		
		for (Field f : c.getDeclaredFields()) {
			Method getMethod = null;
			try {
				getMethod = c.getDeclaredMethod(ReflectUtil.getMethodForFied(f));
			} catch (SecurityException | NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			try {
				if (getMethod.invoke(obj) != null) {
					if (flag) {
						sql = sql + " where " + f.getName() + " like ? ";
						flag = false;
					} else {
						sql = sql + "and " + f.getName() + " like ? ";
					}
				}
			} catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		return isLimit ? sql + " limit ?, ?" : sql;
	}
	
	/** 
	* @Title: setString 
	* @Description: TODO(将bean的属性设入ps的？中) 
	* @param @param ps
	* @param @param c
	* @param @param obj
	* @param @param fuzzy
	* @param @throws SQLException - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public static Integer setString(PreparedStatement ps, Object obj, boolean fuzzy, Integer index) throws SQLException {
		if (ps == null || obj == null || index == null || index < 1) {
			return null;
		}
		
		Class c = obj.getClass();
		
		for (Field f : c.getDeclaredFields()) {
			Method getMethod = null;
		
			try {
				getMethod = c.getDeclaredMethod(ReflectUtil.getMethodForFied(f));
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Object value = getMethod.invoke(obj);
				
				if (value != null) {
					if (fuzzy && value instanceof String) {
						ps.setString(index++, "%" + value + "%");
					} else {
						ps.setString(index++, value.toString());
					}
					
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return index;
	}
	
	/**
	 * @throws SQLException  
	* @Title: initObject 
	* @Description: TODO(根据查询结果初始化对应bean对象) 
	* @param @param rs
	* @param @param c
	* @param @param obj - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public static void initObject(ResultSet rs, Object obj, List<String> colums) throws SQLException {
		if (CheckUtil.nullCheck(rs, obj)) {
			return;
		}
		
		Class c = obj.getClass();
		
		for (Field f : c.getDeclaredFields()) {
			//获取属性名称
			String name = f.getName();
			
			//rs中不存在该列
			if (colums != null && !colums.contains(name)) {
//				System.out.println("不存在" + name);
				continue;
			}
			//在结果中获取该属性对应的值
			Object value = rs.getObject(name);
			
			//值不存在,寻找下一个
			if (value == null) {
				continue;
			}
			
			try {
				//值存在，获取对应的setter方法
				Method setMethod = c.getDeclaredMethod(ReflectUtil.setMethodForFied(f), f.getType());
				
				//执行方法
				setMethod.invoke(obj, value);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** 
	* @Title: getColumsName 
	* @Description: TODO(获取一张表的列名) 
	* @param @param conn
	* @param @param table
	* @param @return - 设定文件 
	* @return List<String> - 返回类型 
	* @throws 
	*/
	public static List<String> getColumsName(Connection conn, String table) {
		List<String> result = new ArrayList<String>();
		
		if (CheckUtil.nullCheck(conn, table)) {
			return result;
		}
		
		try {
			DatabaseMetaData md = conn.getMetaData();
			
			ResultSet rs = md.getColumns(null, null, table, "%");
			
			while (rs.next()) {
				result.add(rs.getString("COLUMN_NAME"));//列名 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/** 
	* @Title: plusValue_insert_sql 
	* @Description: TODO(根据单值insert语句构造多值insert语句) 
	* @param @param sql
	* @param @param size
	* @param @return - 设定文件 
	* @return String - 返回类型 
	* @throws 
	*/
	public static String plusValue_insert_sql(String sql, int size) {
		if (sql.indexOf("values") == -1) {
			return sql;
		}
		
		String subSql = sql.substring(sql.lastIndexOf('('));
		
		while (size-- > 1) {
			sql = sql + ", " + subSql;
		}
		
		return sql;
	}
}
