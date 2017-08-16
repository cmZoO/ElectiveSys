/**   
* @Title: CharacterEncodingFilter.java 
* @Package com.filter 
* @Description: TODO(描述CharacterEncodingFilter类) 
* @author zx583   
* @date 2017年4月26日 上午11:09:57 
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

/** 
* @ClassName: CharacterEncodingFilter 
* @Description: TODO(字符编码过滤器) 
* @author zx583 
* @date 2017年4月26日 上午11:09:57 
*  
*/
@WebFilter(filterName="character",urlPatterns={"/*"})
public class CharacterEncodingFilter implements Filter{

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
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
		
	}

}
