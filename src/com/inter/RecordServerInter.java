/**   
* @Title: RecordServerInter.java 
* @Package com.inter 
* @Description: TODO(描述RecordServerInter类) 
* @author zx583   
* @date 2017年4月21日 下午10:22:21 
* @version V1.0   
*/
package com.inter;

import java.util.List;

import com.bean.ElectiveRecord;

/** 
* @ClassName: RecordServerInter 
* @Description: TODO(选课记录服务功能) 
* @author zx583 
* @date 2017年4月21日 下午10:22:21 
*  
*/
public interface RecordServerInter {
	/** 
	* @Title: getCountOfCourse 
	* @Description: TODO(获取课程id为course_id的课程已选的总个数) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public abstract Integer getCountOfCourse(String course_id);
	/** 
	* @Title: getAllRecord 
	* @Description: TODO(获取所有选课记录) 
	* @param @return - 设定文件 
	* @return List<ElectiveRecord> - 返回类型 
	* @throws 
	*/
	public abstract List<ElectiveRecord> getAllRecord();
	
	/** 
	* @Title: getRecordByStu 
	* @Description: TODO(获取一位学生的所有选课记录) 
	* @param @param stu_id
	* @param @return - 设定文件 
	* @return List<ElectiveRecord> - 返回类型 
	* @throws 
	*/
	public abstract List<ElectiveRecord> getRecordByStu(String stu_id);
	
	/** 
	* @Title: getRecordByCourse 
	* @Description: TODO(获取一个课程的所有选课记录) 
	* @param @param course_id
	* @param @return - 设定文件 
	* @return List<ElectiveRecord> - 返回类型 
	* @throws 
	*/
	public abstract List<ElectiveRecord> getRecordByCourse(String course_id);
	
	/** 
	* @Title: insertRecord 
	* @Description: TODO(插入一条选课记录) 
	* @param @param stu_id
	* @param @param course_id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean insertRecord(String stu_id, String course_id);
	
	/** 
	* @Title: deleteRecord 
	* @Description: TODO(删除一条选课记录) 
	* @param @param stu_id
	* @param @param course_id
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteRecord(String stu_id, String course_id);
	
	/** 
	* @Title: deleteRecordOfCourse 
	* @Description: TODO(删除课程id为course_id的课程的所有选课记录) 
	* @param @param course_aid
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract boolean deleteRecordOfCourse(String course_id);
}
