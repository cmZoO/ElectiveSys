/**   
 * @Title: Teacher.java 
 * @Package com.bean 
 * @Description: TODO(描述Teacher类) 
 * @author zx583   
 * @date 2017年4月15日 上午10:25:17 
 * @version V1.0   
 */
package com.bean;

/** 
 * @ClassName: Teacher 
 * @Description: TODO(老师信息描述类) 
 * @author zx583 
 * @date 2017年4月15日 上午10:25:17 
 *  
 */
public class Teacher {
	/** 
	 * @Fields teac_id : TODO(老师id) 
	 */ 
	private Integer teac_id;
	/** 
	 * @Fields teac_name : TODO(老师姓名) 
	 */ 
	private String teac_name;
	/** 
	 * @Fields password : TODO(老师账户密码) 
	 */ 
	private String password;
	/** 
	 * Title: 
	 * Description: TODO(无参构造函数)  
	 */
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* Title: 
	* Description: TODO(只有id的有参构造函数) 
	* @param teac_id 
	*/
	public Teacher(Integer teac_id) {
		super();
		this.teac_id = teac_id;
	}

	/** 
	 * Title: 
	 * Description: TODO(有参构造函数) 
	 * @param teac_id
	 * @param teac_name
	 * @param password 
	 */
	public Teacher(Integer teac_id, String teac_name, String password) {
		super();
		this.teac_id = teac_id;
		this.teac_name = teac_name;
		this.password = password;
	}

	/** 
	 * @return teac_id 
	 */
	public Integer getTeac_id() {
		return teac_id;
	}
	/** 
	 * @param teac_id 要设置的 teac_id 
	 */
	public void setTeac_id(Integer teac_id) {
		this.teac_id = teac_id;
	}

	/** 
	 * @return teac_name 
	 */
	public String getTeac_name() {
		return teac_name;
	}

	/** 
	 * @param teac_name 要设置的 teac_name 
	 */
	public void setTeac_name(String teac_name) {
		this.teac_name = teac_name;
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
	 * Title: hashCode
	 * Description:
	 * @return 
	 * @see java.lang.Object#hashCode() 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((teac_id == null) ? 0 : teac_id.hashCode());
		result = prime * result + ((teac_name == null) ? 0 : teac_name.hashCode());
		return result;
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
		Teacher other = (Teacher) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (teac_id == null) {
			if (other.teac_id != null)
				return false;
		} else if (!teac_id.equals(other.teac_id))
			return false;
		if (teac_name == null) {
			if (other.teac_name != null)
				return false;
		} else if (!teac_name.equals(other.teac_name))
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
		return "Teacher [teac_id=" + teac_id + ", teac_name=" + teac_name + ", password=" + password + "]";
	}


}
