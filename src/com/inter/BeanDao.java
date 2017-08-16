/**   
* @Title: BeanDao.java 
* @Package com.inter 
* @Description: TODO(描述BeanDao类) 
* @author zx583   
* @date 2017年4月16日 下午3:25:13 
* @version V1.0   
*/
package com.inter;

/** 
* @ClassName: BeanDao 
* @Description: TODO(对于Bean类特有的Dao) 
* @author zx583 
* @date 2017年4月16日 下午3:25:13 
*  
*/
public interface BeanDao<B> {
	/** 
	* @Title: getTotal 
	* @Description: TODO(查询数据库中共有多少个Bean数据) 
	* @param @return - 总数 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public abstract Integer getTotal();
}
