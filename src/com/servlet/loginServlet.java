/**   
* @Title: loginServlet.java 
* @Package com.login 
* @Description: TODO(描述loginServlet类) 
* @author zx583   
* @date 2017年4月25日 上午8:43:27 
* @version V1.0   
*/
package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/** 
* @ClassName: loginServlet 
* @Description: TODO(登陆控制逻辑) 
* @author zx583 
* @date 2017年4月25日 上午8:43:27 
*  
*/
@WebServlet("/login/login")
public class loginServlet extends HttpServlet {
	/* (非 Javadoc) 
	* Title: service
	* Description:
	* @param arg0
	* @param arg1
	* @throws ServletException
	* @throws IOException 
	* @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
	*/
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if (method == null) {
			return;
		}
		
		switch (method) {
		case "logout":
			logout(request,response);
			break;

		default:
			break;
		}
	}

	/**
	 * @throws IOException  
	* @Title: logout 
	* @Description: TODO(登出方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		
		response.sendRedirect("../login.html");
	}
}
