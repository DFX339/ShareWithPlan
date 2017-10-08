package cn.swp.util;

import java.util.List;

/**
 * 分页技术
 * @author Administrator
 * @version 1.0 2017/10/05
 */
public class PageModel {
	
	private int pageRecord;//每页的记录数
	private int totalRecords;//总记录数
	private int pageNo;//当前页号
	private List list;//结果集
	
	/**
	 * 返回总页数
	 * @return
	 */
	public int getTotalPages(){
		return (totalRecords+pageRecord -1)/pageRecord;
	}
	
	/**
	 * 取得上一页
	 * @return
	 */
	public int getPreviousPageNo(){
		if(pageNo <= 1){
			return 1;
		}
		return pageNo-1;
	}
	
	/**
	 * 取得下一页
	 * @return
	 */
	public int getNextPageNo(){
		if(pageNo >= getTotalPages()){
			return getTotalPages()==0?1:getTotalPages();
		}
		return pageNo+1;
	}
	
	/**
	 * 取得最后一页
	 * @return
	 */
	public  int getLastPage(){
		return getTotalPages()==0?1:getTotalPages();
	}
	
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
}
