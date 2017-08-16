/**   
* @Title: ElectiveRecordDaoInter.java 
* @Package com.inter 
* @Description: TODO(描述ElectiveRecordDaoInter类) 
* @author zx583   
* @date 2017年4月23日 上午9:25:16 
* @version V1.0   
*/
package com.inter;

import com.bean.Course;

/** 
* @ClassName: ElectiveRecordDaoInter 
* @Description: TODO(选课记录Dao特有的功能) 
* @author zx583 
* @date 2017年4月23日 上午9:25:16 
*  
*/
public interface ElectiveRecordDaoInter {
	/** 
	* @Title: getCountOfCourse 
	* @Description: TODO(获取一门课程的选课记录数) 
	* @param @param course
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public abstract Integer queryCountOfCourse(Course course);
}
