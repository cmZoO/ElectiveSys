/**   
* @Title: StudentServer.java 
* @Package com.server 
* @Description: TODO(描述StudentServer类) 
* @author zx583   
* @date 2017年4月21日 上午11:12:42 
* @version V1.0   
*/
package com.server;

import java.util.List;

import com.bean.Admin;
import com.bean.Student;
import com.dao.StudentDao;
import com.inter.UserServer;
import com.util.CheckUtil;
import com.util.Page;
import com.util.RegexUtil;

/** 
* @ClassName: StudentServer 
* @Description: TODO(学生账户服务) 
* @author zx583 
* @date 2017年4月21日 上午11:12:42 
*  
*/
public class StudentServer implements UserServer<Student> {
	/** 
	* @Fields dao : TODO(StudentDao对象) 
	*/ 
	private StudentDao dao = new StudentDao();
	/* (非 Javadoc) 
	* Title: login
	* Description:
	* @param username
	* @param password
	* @return 用户存在则返回该用户,否则返回null
	* @see com.inter.UserServer#login(java.lang.String, java.lang.String) 
	*/
	@Override
	public Student login(String username, String password) {
		//检查输入是否为空
		if (CheckUtil.nullCheck(username, password)) {
			return null;
		}
		
		//检查username格式是否正确
		if (!RegexUtil.isAllNum(username)) {
			return null;
		}
		
		//查询是否存在该用户
		List<Student> result = dao.query(new Student(Integer.parseInt(username), null, password, null), null);
		
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
	public List<Student> getAllUser() {
		return dao.query(new Student(), null);
	}

	/* (非 Javadoc) 
	* Title: getUserById
	* Description:
	* @param id
	* @return 
	* @see com.inter.UserServer#getUserById(java.lang.String) 
	*/
	@Override
	public Student getUserById(String id) {
		//检查输入是否为空
		if (CheckUtil.nullCheck(id)) {
			return null;
		}
		
		//检查id格式是否正确
		if (!RegexUtil.isAllNum(id)) {
			return null;
		}
		
		//查询是否存在该用户
		List<Student> result = dao.query(new Student(Integer.parseInt(id)), null);

		//存在该用户,返回该用户
		if (result.size() == 1) {
			return result.get(0);
		}
		
		return null;
	}

	/* (非 Javadoc) 
	* Title: register
	* Description:
	* @param student
	* @return 注册成功 true 注册失败 false
	* @see com.inter.UserServer#register(java.lang.Object) 
	*/
	@Override
	public boolean register(Student student) {
		if (CheckUtil.nullCheck(student)) {
			return false;
		}
		
		return dao.insert(student) == 1;
	}

	/* (非 Javadoc) 
	* Title: delete
	* Description:
	* @param id
	* @return 
	* @see com.inter.UserServer#delete(java.lang.String) 
	*/
	@Override
	public boolean delete(String id) {
		if (CheckUtil.nullCheck(id)) {
			return false;
		}
		
		if (!RegexUtil.isAllNum(id)) {
			return false;
		}
		
		return dao.delete(new Student(Integer.parseInt(id))) == 1;
	}

	/* (非 Javadoc) 
	* Title: update
	* Description:
	* @param student
	* @param id
	* @return 
	* @see com.inter.UserServer#update(java.lang.Object, java.lang.String) 
	*/
	@Override
	public boolean update(Student student, String id) {
		if (CheckUtil.nullCheck(student, id)) {
			return false;
		}
		
		if (!RegexUtil.isAllNum(id)) {
			return false;
		}
		
		student.setStu_id(Integer.parseInt(id));
		
		return dao.update(student) == 1;
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
		
		return update(new Student(null, null, password, null), username);
	}

	/* (非 Javadoc) 
	* Title: getPageUser
	* Description:
	* @param page
	* @return 
	* @see com.inter.UserServer#getPageUser(com.util.Page) 
	*/
	@Override
	public List<Student> getPageUser(Page page) {
		return dao.query(new Student(), page);
	}

	/* (非 Javadoc) 
	* Title: getTotal
	* Description:
	* @return 
	* @see com.inter.UserServer#getTotal() 
	*/
	@Override
	public Integer getTotal() {
		// TODO Auto-generated method stub
		return dao.getTotal();
	}

}
