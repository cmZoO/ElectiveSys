/**   
* @Title: AdminFilter.java 
* @Package com.filter 
* @Description: TODO(描述AdminFilter类) 
* @author zx583   
* @date 2017年4月26日 上午11:21:14 
* @version V1.0   
*/
package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @ClassName: AdminFilter 
* @Description: TODO(管理员权限过滤器) 
* @author zx583 
* @date 2017年4月26日 上午11:21:14 
*  
*/
@WebFilter(filterName="admin",urlPatterns={"/admin/*"})
public class AdminFilter implements Filter {

	/* (非 Javadoc) 
	* Title: destroy
	* Description: 
	* @see javax.servlet.Filter#destroy() 
	*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (非 Javadoc) 
	* Title: doFilter
	* Description:
	* @param arg0
	* @param arg1
	* @param arg2
	* @throws IOException
	* @throws ServletException 
	* @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//只有管理员能访问该控制器的方法
		if (((HttpServletRequest) request).getSession().getAttribute("admin") == null) {
			//权限验证失败,跳转登出登出../login/login?method=logout
			((HttpServletResponse) response).sendRedirect("../login/login?method=logout");
			return;
		}
		
		chain.doFilter(request, response);
	}

	/* (非 Javadoc) 
	* Title: init
	* Description:
	* @param arg0
	* @throws ServletException 
	* @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
	*/
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
