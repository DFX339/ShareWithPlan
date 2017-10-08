package cn.swp.bean;

import java.util.Date;

/**
 * 笔记评论类
 * @author Administrator
 *
 */
public class NoteComment {
	
	private int ncid;//笔记评论ID
	private String ncContent;//笔记评论内容
	private int ncCommentID;//笔记评论者ID
	private String ncCommentName;//笔记评论者姓名
	private Date ncTime;//评论时间
	private int ncnid;//发表笔记者的id
	public NoteComment(){}

	
	public int getNcnid() {
		return ncnid;
	}


	public void setNcnid(int ncnid) {
		this.ncnid = ncnid;
	}


	public int getNcid() {
		return ncid;
	}

	public void setNcid(int ncid) {
		this.ncid = ncid;
	}


	public String getNcContent() {
		return ncContent;
	}

	public void setNcContent(String ncContent) {
		this.ncContent = ncContent;
	}

	public int getNcCommentID() {
		return ncCommentID;
	}

	public void setNcCommentID(int ncCommentID) {
		this.ncCommentID = ncCommentID;
	}

	public String getNcCommentName() {
		return ncCommentName;
	}

	public void setNcCommentName(String ncCommentName) {
		this.ncCommentName = ncCommentName;
	}

	public Date getNcTime() {
		return ncTime;
	}

	public void setNcTime(Date ncTime) {
		this.ncTime = ncTime;
	}
	
	
}
