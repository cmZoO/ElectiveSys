/**   
* @Title: AdminServer.java 
* @Package com.server 
* @Description: TODO(����AdminServer��) 
* @author zx583   
* @date 2017��4��21�� ����10:44:36 
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
* @Description: TODO(����Ա�˻�����) 
* @author zx583 
* @date 2017��4��21�� ����10:44:36 
*  
*/
public class AdminServer implements UserServer<Admin> {
	/** 
	* @Fields dao : TODO(AdminDao����) 
	*/ 
	private AdminDao dao = new AdminDao();
	/* (�� Javadoc) 
	* Title: login
	* Description:
	* @param userName
	* @param password
	* @return �û������򷵻ظ��û�,���򷵻�null
	* @see com.inter.UserServer#login(java.lang.String, java.lang.String) 
	*/
	@Override
	public Admin login(String username, String password) {
		//��������Ƿ�Ϊ��
		if (CheckUtil.nullCheck(username, password)) {
			return null;
		}
		
		//���username��ʽ�Ƿ���ȷ
		if (!RegexUtil.isAllNum(username)) {
			return null;
		}
		
		//��ѯ�Ƿ���ڸ��û�
		List<Admin> result = dao.query(new Admin(Integer.parseInt(username), null, password), null);
		
		//���ڸ��û�,���ظ��û�
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	/* (�� Javadoc) 
	* Title: getAllUser
	* Description:
	* @return �����û���List����
	* @see com.inter.UserServer#getAllUser() 
	*/
	@Override
	public List<Admin> getAllUser() {
		return dao.query(new Admin(), null);
	}

	/* (�� Javadoc) 
	* Title: getUserById
	* Description:
	* @param id
	* @return �����򷵻ظ��û�,���򷵻�null
	* @see com.inter.UserServer#getUserById(java.lang.String) 
	*/
	@Override
	public Admin getUserById(String id) {
		//��������Ƿ�Ϊ��
		if (CheckUtil.nullCheck(id)) {
			return null;
		}
		
		//���id��ʽ�Ƿ���ȷ
		if (!RegexUtil.isAllNum(id)) {
			return null;
		}
		
		//��ѯ�Ƿ���ڸ��û�
		List<Admin> result = dao.query(new Admin(Integer.parseInt(id)), null);

		//���ڸ��û�,���ظ��û�
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	/* (�� Javadoc) 
	* Title: register
	* Description:
	* @param admin
	* @return ע��ɹ� true ע��ʧ�� false
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

	/* (�� Javadoc) 
	* Title: delete
	* Description:
	* @param id
	* @return ɾ���ɹ�true ɾ��ʧ��false
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

	/* (�� Javadoc) 
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

	/* (�� Javadoc) 
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

	/* (�� Javadoc) 
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

	/* (�� Javadoc) 
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
