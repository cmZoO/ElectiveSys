/**   
* @Title: JDBC.java 
* @Package com.jdbc 
* @Description: TODO(描述JDBC类) 
* @author zx583   
* @date 2017年4月15日 上午11:15:27 
* @version V1.0   
*/
package com.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

/** 
* @ClassName: JDBC 
* @Description: TODO(数据库连接工具类) 
* @author zx583 
* @date 2017年4月15日 上午11:15:27 
*  
*/
public class JDBC {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String port = "";
	private static String ipAdress = "";
	private static String url = "";
	private static String user  = "";
	private static String password = "";
	
	static {
		Properties pps = new Properties();
		try {
			pps.load(JDBC.class.getClassLoader().getResourceAsStream("db.properties"));
			if (pps.getProperty("port") != null) {
				port = pps.getProperty("port");
				System.out.println(port);
			}
			
			if (pps.getProperty("ipAdress") != null) {
				ipAdress = pps.getProperty("ipAdress");
				System.out.println(ipAdress);
			}
			
			if (pps.getProperty("user") != null) {
				user = pps.getProperty("user");
				System.out.println(user);
			}
			
			if (pps.getProperty("password") != null) {
				password = pps.getProperty("password");
				System.out.println(password);
			}
			
			url = "jdbc:mysql://" + ipAdress + ":" + port + "/elective?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
			System.out.println(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Object obj) {
		if (obj == null) {
			return;
		}
		
		if (obj instanceof Connection) {
			try {
				((Connection) obj).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		} 
		
		if (obj instanceof Statement) {
			try {
				((Statement) obj).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		if (obj instanceof ResultSet) {
			try {
				((ResultSet) obj).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
	}
}
