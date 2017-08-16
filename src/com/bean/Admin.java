/**   
* @Title: Admin.java 
* @Package com.bean 
* @Description: TODO(描述Admin类) 
* @author zx583   
* @date 2017年4月15日 上午10:17:53 
* @version V1.0   
*/
package com.bean;

/** 
* @ClassName: Admin 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zx583 
* @date 2017年4月15日 上午10:17:53 
*  
*/
public class Admin {
	/** 
	* @Fields admin_id : TODO(管理员id) 
	*/ 
	private Integer admin_id;
	/** 
	* @Fields admin_name : TODO(管理员名称) 
	*/ 
	private String admin_name;
	/** 
	* @Fields password : TODO(管理员密码) 
	*/ 
	private String password;
	/** 
	* Title: 
	* Description: TODO(无参构造方法)  
	*/
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* Title: 
	* Description: TODO(有参构造方法) 
	* @param admin_id 
	*/
	public Admin(Integer admin_id) {
		super();
		this.admin_id = admin_id;
	}



	/** 
	 * Title: 
	 * Description: TODO(有参构造方法) 
	 * @param admin_id
	 * @param admin_name
	 * @param password 
	 */
	public Admin(Integer admin_id, String admin_name, String password) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.password = password;
	}
	/* (非 Javadoc) 
	* Title: hashCode
	* Description:
	* @return 
	* @see java.lang.Object#hashCode() 
	*/
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin_id == null) ? 0 : admin_id.hashCode());
		result = prime * result + ((admin_name == null) ? 0 : admin_name.hashCode());
		return result;
	}
	/** 
	* @return admin_id 
	*/
	public Integer getAdmin_id() {
		return admin_id;
	}
	/** 
	* @param admin_id 要设置的 admin_id 
	*/
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	/** 
	* @return admin_name 
	*/
	public String getAdmin_name() {
		return admin_name;
	}
	/** 
	* @param admin_name 要设置的 admin_name 
	*/
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	
	/** 
	* @return password 
	*/
	public String getPassword() {
		return password;
	}

	/** 
	* @param password 要设置的 password 
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/* (非 Javadoc) 
	* Title: equals
	* Description:
	* @param obj
	* @return 
	* @see java.lang.Object#equals(java.lang.Object) 
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (admin_id == null) {
			if (other.admin_id != null)
				return false;
		} else if (!admin_id.equals(other.admin_id))
			return false;
		if (admin_name == null) {
			if (other.admin_name != null)
				return false;
		} else if (!admin_name.equals(other.admin_name))
			return false;
		return true;
	}

	/* (非 Javadoc) 
	* Title: toString
	* Description:
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_name=" + admin_name + ", password=" + password + "]";
	}
	
}
