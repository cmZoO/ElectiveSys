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
 * Servlet Filter implementation class TeacherFilter
 */
@WebFilter(filterName="teacher",urlPatterns={"/teacher/*"})
public class TeacherFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TeacherFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//只有老师与管理员能访问该控制器的方法
		if (((HttpServletRequest) request).getSession().getAttribute("teacher") == null) {
			//权限验证失败,跳转登出登出../login/login?method=logout
			if (((HttpServletRequest) request).getSession().getAttribute("admin") == null) {
				//权限验证失败,跳转登出登出../login/login?method=logout
				((HttpServletResponse) response).sendRedirect("../login/login?method=logout");
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
