/**   
* @Title: ElectiveRecord.java 
* @Package com.bean 
* @Description: TODO(描述ElectiveRecord类) 
* @author zx583   
* @date 2017年4月15日 上午10:33:26 
* @version V1.0   
*/
package com.bean;

/** 
* @ClassName: ElectiveRecord 
* @Description: TODO(描述一个选课记录) 
* @author zx583 
* @date 2017年4月15日 上午10:33:26 
*  
*/
public class ElectiveRecord {
	/** 
	* @Fields stu : TODO(表示该记录的拥有学生) 
	*/ 
	private Student student;
	/** 
	* @Fields course : TODO(表示所选的课程) 
	*/ 
	private Course course;
	/** 
	* Title: 
	* Description: TODO(无参构造方法)  
	*/
	public ElectiveRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** 
	* Title: 
	* Description: TODO(有参构造方法) 
	* @param record_id
	* @param plan
	* @param student
	* @param course 
	*/
	public ElectiveRecord(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
	/** 
	* @return student 
	*/
	public Student getStudent() {
		return student;
	}
	/** 
	* @param student 要设置的 student 
	*/
	public void setStudent(Student student) {
		this.student = student;
	}
	/** 
	* @return course 
	*/
	public Course getCourse() {
		return course;
	}
	/** 
	* @param course 要设置的 course 
	*/
	public void setCourse(Course course) {
		this.course = course;
	}
	/* (非 Javadoc) 
	* Title: toString
	* Description:
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "ElectiveRecord [student=" + student.getStu_name() + ", course=" + course.getCourse_name() + "]";
	}
	
	
}
