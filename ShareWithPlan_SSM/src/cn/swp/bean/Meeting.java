package cn.swp.bean;

import java.util.Date;
import java.util.Set;

/**
 * 会议记录实体类
 * @author Administrator
 *
 */
public class Meeting {
	
	private int mid;//会议记录id
	private String mContent;//会议记录内容
	private Date mtime;//会议记录的时间
	private int mcuserid;//企业用户id 
	private String mcompanyNum;//公司代码
	
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

	private Set meetComments;//一条会议记录可以有多条会议记录的评论
	private CompanyUser companyUser;//关联属性，发表会议记录的人员
	
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
