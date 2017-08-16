/**   
 * @Title: Student.java 
 * @Package com.bean 
 * @Description: TODO(描述Student类) 
 * @author zx583   
 * @date 2017年4月15日 上午10:21:56 
 * @version V1.0   
 */
package com.bean;

import java.sql.Date;

/** 
 * @ClassName: Student 
 * @Description: TODO(学生信息描述类) 
 * @author zx583 
 * @date 2017年4月15日 上午10:21:56 
 *  
 */
public class Student {
	/** 
	 * @Fields stu_id : TODO(学生id) 
	 */ 
	private Integer stu_id;
	/** 
	 * @Fields stu_name : TODO(学生姓名) 
	 */ 
	private String stu_name;
	/** 
	 * @Fields password : TODO(学生密码) 
	 */ 
	private String password;
	/** 
	 * @Fields register_date : TODO(学生入学时间) 
	 */ 
	private Date register_date;
	/** 
	 * Title: 
	 * Description: TODO(无参构造方法)  
	 */
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* Title: 
	* Description: TODO(只有id的有参构造函数) 
	* @param stu_id 
	*/
	public Student(Integer stu_id) {
		super();
		this.stu_id = stu_id;
	}

	/** 
	 * Title: 
	 * Description: TODO(有参构造方法) 
	 * @param stu_id
	 * @param stu_name
	 * @param register_date
	 * @param password 
	 */
	public Student(Integer stu_id, String stu_name,String password, Date register_date) {
		super();
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.register_date = register_date;
		this.password = password;
	}

	/** 
	 * @return stu_id 
	 */
	public Integer getStu_id() {
		return stu_id;
	}
	/** 
	 * @param stu_id 要设置的 stu_id 
	 */
	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}
	/** 
	 * @return stu_name 
	 */
	public String getStu_name() {
		return stu_name;
	}
	/** 
	 * @param stu_name 要设置的 stu_name 
	 */
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
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
	/** 
	 * @return register_date 
	 */
	public Date getRegister_date() {
		return register_date;
	}
	/** 
	 * @param register_date 要设置的 register_date 
	 */
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
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
		result = prime * result + ((register_date == null) ? 0 : register_date.hashCode());
		result = prime * result + ((stu_id == null) ? 0 : stu_id.hashCode());
		result = prime * result + ((stu_name == null) ? 0 : stu_name.hashCode());
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
		Student other = (Student) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (register_date == null) {
			if (other.register_date != null)
				return false;
		} else if (!register_date.equals(other.register_date))
			return false;
		if (stu_id == null) {
			if (other.stu_id != null)
				return false;
		} else if (!stu_id.equals(other.stu_id))
			return false;
		if (stu_name == null) {
			if (other.stu_name != null)
				return false;
		} else if (!stu_name.equals(other.stu_name))
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
		return "Student [stu_id=" + stu_id + ", stu_name=" + stu_name + ", register_date=" + register_date
				+ ", password=" + password + "]";
	}


}
