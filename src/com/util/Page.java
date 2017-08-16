/**   
* @Title: Page.java 
* @Package com.util 
* @Description: TODO(描述Page类) 
* @author zx583   
* @date 2017年4月16日 下午2:19:51 
* @version V1.0   
*/
package com.util;

/** 
* @ClassName: Page 
* @Description: TODO(表示分页) 
* @author zx583 
* @date 2017年4月16日 下午2:19:51 
*  
*/
public class Page {
	/** 
	* @Fields currentPage : TODO(当前页) 
	*/ 
	private Integer currentPage;
	/** 
	* @Fields pageColum : TODO(一页的行数) 
	*/ 
	private Integer pageRow;
	/** 
	* @Fields totalRows : TODO(总行数) 
	*/ 
	private Integer totalRows;
	/** 
	* Title: 
	* Description: TODO(有参构造方法) 
	* @param currentPage
	* @param pageRow
	* @param totalRows 
	*/
	public Page(Integer currentPage, Integer pageRow, Integer totalRows) {
		super();
		this.currentPage = currentPage;
		this.pageRow = pageRow;
		this.totalRows = totalRows;
	}
	/** 
	* @return currentPage 
	*/
	public Integer getCurrentPage() {
		return currentPage;
	}
	/** 
	* @param currentPage 要设置的 currentPage 
	*/
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/** 
	* @return pageRow 
	*/
	public Integer getPageRow() {
		return pageRow;
	}
	/** 
	* @param pageRow 要设置的 pageRow 
	*/
	public void setPageRow(Integer pageRow) {
		this.pageRow = pageRow;
	}
	
	/** 
	* @param totalRows 要设置的 totalRows 
	*/
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	
	/** 
	* @return totalRows 
	*/
	public Integer getTotalRows() {
		return totalRows;
	}
	/** 
	* @Title: getTotalPage 
	* @Description: TODO(获取总分页数) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public Integer getTotalPage() {
		//合法性判断
		if (totalRows == null || !(totalRows.compareTo(0) > 0) || pageRow == null || !(pageRow.compareTo(0) > 0)) {
			return null;
		}
		
		//如果总行数是页数的整数倍，该倍数
		if (totalRows % pageRow == 0) {
			return totalRows / pageRow;
		}
		
		//返回总行数对于页数的倍数+1
		return totalRows / pageRow + 1;
	}
	
	/** 
	* @Title: getFirstRowOfCurrentPage 
	* @Description: TODO(获取当前页的第一行) 
	* @param @return - 设定文件 
	* @return Integer - 返回类型 
	* @throws 
	*/
	public Integer getFirstRowOfCurrentPage() {
		//合法性判断
		if (currentPage == null || !(currentPage.compareTo(0) > 0) || pageRow == null || !(pageRow.compareTo(0) > 0)) {
			return null;
		}
		
		return (currentPage - 1) * pageRow + 1;
	}
}
