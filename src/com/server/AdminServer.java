/**   
* @Title: AdminServer.java 
* @Package com.server 
* @Description: TODO(描述AdminServer类) 
* @author zx583   
* @date 2017年4月21日 上午10:44:36 
* @version V1.0   
*/
package com.server;

import java.util.List;

import com.bean.Admin;
import com.dao.AdminDao;
import com.inter.UserServer;
import com.util.CheckUtil;
import com.util.Page;
import com.util.RegexUtil;

/** 
* @ClassName: AdminServer 
* @Description: TODO(管理员账户服务) 
* @author zx583 
* @date 2017年4月21日 上午10:44:36 
*  
*/
public class AdminServer implements UserServer<Admin> {
	/** 
	* @Fields dao : TODO(AdminDao对象) 
	*/ 
	private AdminDao dao = new AdminDao();
	/* (非 Javadoc) 
	* Title: login
	* Description:
	* @param userName
	* @param password
	* @return 用户存在则返回该用户,否则返回null
	* @see com.inter.UserServer#login(java.lang.String, java.lang.String) 
	*/
	@Override
	public Admin login(String username, String password) {
		//检查输入是否为空
		if (CheckUtil.nullCheck(username, password)) {
			return null;
		}
		
		//检查username格式是否正确
		if (!RegexUtil.isAllNum(username)) {
			return null;
		}
		
		//查询是否存在该用户
		List<Admin> result = dao.query(new Admin(Integer.parseInt(username), null, password), null);
		
		//存在该用户,返回该用户
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	/* (非 Javadoc) 
	* Title: getAllUser
	* Description:
	* @return 所有用户的List集合
	* @see com.inter.UserServer#getAllUser() 
	*/
	@Override
	public List<Admin> getAllUser() {
		return dao.query(new Admin(), null);
	}

	/* (非 Javadoc) 
	* Title: getUserById
	* Description:
	* @param id
	* @return 存在则返回该用户,否则返回null
	* @see com.inter.UserServer#getUserById(java.lang.String) 
	*/
	@Override
	public Admin getUserById(String id) {
		//检查输入是否为空
		if (CheckUtil.nullCheck(id)) {
			return null;
		}
		
		//检查id格式是否正确
		if (!RegexUtil.isAllNum(id)) {
			return null;
		}
		
		//查询是否存在该用户
		List<Admin> result = dao.query(new Admin(Integer.parseInt(id)), null);

		//存在该用户,返回该用户
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	/* (非 Javadoc) 
	* Title: register
	* Description:
	* @param admin
	* @return 注册成功 true 注册失败 false
	* @see com.inter.UserServer#register(java.lang.Object) 
	*/
	@Override
	public boolean register(Admin admin) {
		if (CheckUtil.nullCheck(admin)) {
			return false;
		}
		
		admin.setAdmin_id(null);
		
		return dao.insert(admin) == 1;
	}

	/* (非 Javadoc) 
	* Title: delete
	* Description:
	* @param id
	* @return 删除成功true 删除失败false
	* @see com.inter.UserServer#delete(java.lang.Object) 
	*/
	@Override
	public boolean delete(String id) {
		if (CheckUtil.nullCheck(id)) {
			return false;
		}
		
		if (!RegexUtil.isAllNum(id)) {
			return false;
		}
		
		return dao.delete(new Admin(Integer.parseInt(id))) == 1;
	}

	/* (非 Javadoc) 
	* Title: update
	* Description:
	* @param admin
	* @param id
	* @return 
	* @see com.inter.UserServer#update(java.lang.Object, java.lang.String) 
	*/
	@Override
	public boolean update(Admin admin, String id) {
		if (CheckUtil.nullCheck(admin, id)) {
			return false;
		}
		
		if (!RegexUtil.isAllNum(id)) {
			return false;
		}
		
		admin.setAdmin_id(Integer.parseInt(id));
		
		return dao.update(admin) == 1;
	}

	/* (非 Javadoc) 
	* Title: changePassword
	* Description:
	* @param username
	* @param password
	* @return 
	* @see com.inter.UserServer#changePassword(java.lang.String, java.lang.String) 
	*/
	@Override
	public boolean changePassword(String username, String password) {
		if (CheckUtil.nullCheck(password)) {
			return false;
		}
		
		return update(new Admin(null, null, password), username);
	}

	/* (非 Javadoc) 
	* Title: getPageUser
	* Description:
	* @param page
	* @return 
	* @see com.inter.UserServer#getPageUser(com.util.Page) 
	*/
	@Override
	public List<Admin> getPageUser(Page page) {
		return dao.query(new Admin(), page);
	}

	/* (非 Javadoc) 
	* Title: getTotal
	* Description:
	* @return 
	* @see com.inter.UserServer#getTotal() 
	*/
	@Override
	public Integer getTotal() {
		return dao.getTotal();
	}


}
