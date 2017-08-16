package com.inter;

import java.util.List;

import com.util.Page;

/** 
* @ClassName: BaseDao 
* @Description: TODO(基础Dao操作接口) 
* @author zx583 
* @date 2017年4月15日 上午10:28:52 
* 
* @param <T> 
*/
public interface BaseDao<T> {
	/** 
	* @Title: query 
	* @Description: TODO(在数据库中查询t) 
	* @param @param t
	* @param @return - 设定文件 
	* @return List<T> - 返回类型 
	* @throws 
	*/
	public abstract List<T> query(T t, Page page);
	
	/** 
	* @Title: delete 
	* @Description: TODO(在数据库中删除t) 
	* @param @param t
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract int delete(T t);
	
	/** 
	* @Title: update 
	* @Description: TODO(在数据库中更新t) 
	* @param @param t
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract int update(T t);
	
	/** 
	* @Title: insert 
	* @Description: TODO(在数据库中增加t) 
	* @param @param t
	* @param @return - 设定文件 
	* @return boolean - 返回类型 
	* @throws 
	*/
	public abstract int insert(T t);
	
	/** 
	* @Title: close 
	* @Description: TODO(关闭Dao方法,释放资源) 
	* @param  - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	public abstract void close();
}
