package cn.swp.bean;

import java.util.Date;

/**
 * �ʼ�������
 * @author Administrator
 *
 */
public class NoteComment {
	
	private int ncid;//�ʼ�����ID
	private String ncContent;//�ʼ���������
	private int ncCommentID;//�ʼ�������ID
	private String ncCommentName;//�ʼ�����������
	private Date ncTime;//����ʱ��
	private int ncnid;//����ʼ��ߵ�id
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
