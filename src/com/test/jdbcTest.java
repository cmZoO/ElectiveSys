/**   
* @Title: jdbcTest.java 
* @Package com.test 
* @Description: TODO(����jdbcTest��) 
* @author zx583   
* @date 2017��4��15�� ����11:29:22 
* @version V1.0   
*/
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JDBC;

/** 
* @ClassName: jdbcTest 
* @Description: TODO(������һ�仰��������������) 
* @author zx583 
* @date 2017��4��15�� ����11:29:22 
*  
*/
public class jdbcTest {

	/** 
	* @Title: main 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param args - �趨�ļ� 
	* @return void - �������� 
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
