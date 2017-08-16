/**   
* @Title: RecordServer.java 
* @Package com.server 
* @Description: TODO(描述RecordServer类) 
* @author zx583   
* @date 2017年4月21日 下午10:23:16 
* @version V1.0   
*/
package com.server;

import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.ElectiveRecord;
import com.bean.Student;
import com.dao.ElectiveRecordDao;
import com.inter.RecordServerInter;
import com.util.CheckUtil;
import com.util.RegexUtil;

/** 
* @ClassName: RecordServer 
* @Description: TODO(选课记录服务) 
* @author zx583 
* @date 2017年4月21日 下午10:23:16 
*  
*/
public class RecordServer implements RecordServerInter {
	/** 
	* @Fields dao : TODO(选课记录dao) 
	*/ 
	private ElectiveRecordDao dao= new ElectiveRecordDao();
	/* (非 Javadoc) 
	* Title: getAllRecord
	* Description:
	* @return 
	* @see com.inter.RecordServerInter#getAllRecord() 
	*/
	@Override
	public List<ElectiveRecord> getAllRecord() {
		return dao.query(new ElectiveRecord(), null);
	}

	/* (非 Javadoc) 
	* Title: getRecordByStu
	* Description:
	* @param stu_id
	* @return 
	* @see com.inter.RecordServerInter#getRecordByStu(java.lang.String) 
	*/
	@Override
	public List<ElectiveRecord> getRecordByStu(String stu_id) {
		if (CheckUtil.nullCheck(stu_id) || !RegexUtil.isAllNum(stu_id)) {
			return new ArrayList<ElectiveRecord>();
		}
		
		return dao.query(new ElectiveRecord(new Student(Integer.parseInt(stu_id)), null), null);
	}

	/* (非 Javadoc) 
	* Title: getRecordByCourse
	* Description:
	* @param course_id
	* @return 
	* @see com.inter.RecordServerInter#getRecordByCourse(java.lang.String) 
	*/
	@Override
	public List<ElectiveRecord> getRecordByCourse(String course_id) {
		if (CheckUtil.nullCheck(course_id) || !RegexUtil.isAllNum(course_id)) {
			return new ArrayList<ElectiveRecord>();
		}
		
		return dao.query(new ElectiveRecord(null, new Course(Integer.parseInt(course_id))), null);
	}

	/* (非 Javadoc) 
	* Title: insertRecord
	* Description:
	* @param stu_id
	* @param course_id
	* @return 
	* @see com.inter.RecordServerInter#insertRecord(java.lang.String, java.lang.String) 
	*/
	@Override
	public boolean insertRecord(String stu_id, String course_id) {
		if (CheckUtil.nullCheck(stu_id, course_id) || !RegexUtil.isAllNum(stu_id) || !RegexUtil.isAllNum(course_id)) {
			return false;
		}
		return dao.insert(new ElectiveRecord(new Student(Integer.parseInt(stu_id)), new Course(Integer.parseInt(course_id)))) == 1;
	}

	/* (非 Javadoc) 
	* Title: deleteRecord
	* Description:
	* @param stu_id
	* @param course_id
	* @return 
	* @see com.inter.RecordServerInter#deleteRecord(java.lang.String, java.lang.String) 
	*/
	@Override
	public boolean deleteRecord(String stu_id, String course_id) {
		if (CheckUtil.nullCheck(stu_id, course_id) || !RegexUtil.isAllNum(stu_id) || !RegexUtil.isAllNum(course_id)) {
			return false;
		}
		
		return dao.delete(new ElectiveRecord(new Student(Integer.parseInt(stu_id)), new Course(Integer.parseInt(course_id)))) == 1;
	}

	/* (非 Javadoc) 
	* Title: getCountOfCourse
	* Description:
	* @param course_id
	* @return 
	* @see com.inter.RecordServerInter#getCountOfCourse(java.lang.String) 
	*/
	@Override
	public Integer getCountOfCourse(String course_id) {
		if (CheckUtil.nullCheck(course_id) || !RegexUtil.isAllNum(course_id)) {
			return 0;
		}
		
		return dao.queryCountOfCourse(new Course(Integer.parseInt(course_id)));
	}

	/* (非 Javadoc) 
	* Title: deleteRecordOfCourse
	* Description:
	* @param course_id
	* @return 
	* @see com.inter.RecordServerInter#deleteRecordOfCourse(java.lang.String) 
	*/
	@Override
	public boolean deleteRecordOfCourse(String course_id) {
		if (course_id == null || !RegexUtil.isAllNum(course_id)) {
			return false;
		}
		
		return dao.delete(new ElectiveRecord(null, new Course(Integer.parseInt(course_id)))) >= 0;
	}

}
