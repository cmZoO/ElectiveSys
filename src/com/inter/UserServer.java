/**   
* @Title: UserServerInter.java 
* @Package com.inter 
* @Description: TODO(描述UserServerInter类) 
* @author zx583   
* @date 2017年4月21日 上午10:33:15 
* @version V1.0   
*/
package com.inter;

import java.util.List;

import com.util.Page;

/** 
* @ClassName: UserServerInter 
* @Description: TODO(用户服务所具有的功能) 
* @author zx583 
* @date 2017年4月21日 上午10:33:15 
*  
*/
public interface UserServer<U> {
	/** 
	* @Title: getTotal 
	* @Description: TODO(获取所有用户的个数) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public abstract Integer getTotal();
	/** 
	* @Title: login 
	* @Description: TODO(登陆功能) 
	* @param @param username
	* @param @param password
	* @param @return - 设定文件 
	* @return U - 返回类型 
	* @throws 
	*/
	public abstract U login(String username, String password);
	
	/** 
	* @Title: getAllUser 
	* @Description: TODO(获取所有用户) 
	* @param @return - 设定文件 
	* @return List<U> - 返回类型 
	* @throws 
	*/
	public abstract List<U> getAllUser();
	
	
	/** 
	* @Title: getPageUser 
	* @Description: TODO(获取一个分页内的用户) 
	* @param @param page
	* @param @return - 设定文件 
	* @return List<U> - 返回类型 
	* @throws 
	*/
	public abstract List<U> getPageUser(Page page);
	
	/** 
	* @Title: getUserById 
	* @Description: TODO(通过id获取用户) 
	* @param @param id
	* @param @return - 设定文件 
	* @return U - 返回类型 
	* @throws 
	*/
	public abstract U getUserById(String id);
	
	/** 
	* @Title: register 
	* @Description: TODO(注册一位用户) 
	* @param @param u
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean register(U u);
	
	/** 
	* @Title: delete 
	* @Description: TODO(删除ID为id的用户) 
	* @param @param id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean delete(String id);
	
	/** 
	* @Title: update 
	* @Description: TODO(修改ID为id的信息为u) 
	* @param @param u
	* @param @param id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean update(U u, String id);
	
	/** 
	* @Title: changePassword 
	* @Description: TODO(修改用户名为username的用户的密码为password) 
	* @param @param username
	* @param @param password
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean changePassword(String username, String password);
}
