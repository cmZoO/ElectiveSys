/**   
* @Title: jdbcTest.java 
* @Package com.test 
* @Description: TODO(描述jdbcTest类) 
* @author zx583   
* @date 2017年4月15日 上午11:29:22 
* @version V1.0   
*/
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JDBC;

/** 
* @ClassName: jdbcTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月15日 上午11:29:22 
*  
*/
public class jdbcTest {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public static void main (String[] args) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		PreparedStatement ps = conn.prepareStatement("show tables");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1 ));
		}
	}

}
