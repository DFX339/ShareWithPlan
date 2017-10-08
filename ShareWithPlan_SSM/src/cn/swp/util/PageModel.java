package cn.swp.util;

import java.util.List;

/**
 * ��ҳ����
 * @author Administrator
 * @version 1.0 2017/10/05
 */
public class PageModel {
	
	private int pageRecord;//ÿҳ�ļ�¼��
	private int totalRecords;//�ܼ�¼��
	private int pageNo;//��ǰҳ��
	private List list;//�����
	
	/**
	 * ������ҳ��
	 * @return
	 */
	public int getTotalPages(){
		return (totalRecords+pageRecord -1)/pageRecord;
	}
	
	/**
	 * ȡ����һҳ
	 * @return
	 */
	public int getPreviousPageNo(){
		if(pageNo <= 1){
			return 1;
		}
		return pageNo-1;
	}
	
	/**
	 * ȡ����һҳ
	 * @return
	 */
	public int getNextPageNo(){
		if(pageNo >= getTotalPages()){
			return getTotalPages()==0?1:getTotalPages();
		}
		return pageNo+1;
	}
	
	/**
	 * ȡ�����һҳ
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
