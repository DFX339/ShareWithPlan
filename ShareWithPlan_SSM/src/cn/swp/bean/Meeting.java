package cn.swp.bean;

import java.util.Date;
import java.util.Set;

/**
 * �����¼ʵ����
 * @author Administrator
 *
 */
public class Meeting {
	
	private int mid;//�����¼id
	private String mContent;//�����¼����
	private Date mtime;//�����¼��ʱ��
	private int mcuserid;//��ҵ�û�id 
	private String mcompanyNum;//��˾����
	
	public int getMcuserid() {
		return mcuserid;
	}

	
	
	public String getMcompanyNum() {
		return mcompanyNum;
	}



	public void setMcompanyNum(String mcompanyNum) {
		this.mcompanyNum = mcompanyNum;
	}



	public void setMcuserid(int mcuserid) {
		this.mcuserid = mcuserid;
	}

	private Set meetComments;//һ�������¼�����ж��������¼������
	private CompanyUser companyUser;//�������ԣ���������¼����Ա
	
	public Meeting(){}

	
	public CompanyUser getCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}


	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}
	

	public Set getMeetComments() {
		return meetComments;
	}

	public void setMeetComments(Set meetComments) {
		this.meetComments = meetComments;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	
	
}
