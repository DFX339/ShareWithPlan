package cn.swp.bean;

import java.util.Date;
import java.util.Set;

/**
 * �ʼ�ʵ����
 * @author Administrator
 *
 */
public class Note {
	
	private int nid;//�ʼ�ID������ʶ
	private String ncontent;//�ʼ�����
	private Date nsendTime;//�ʼǷ���ʱ��
	private String nstate;//�ʼ�״̬
	private Set noteComments;//һ���ʼǿ����ж����ʼ�����
	private int nuserid;//��ͨ�û�id
	private int ncuserid;//��ҵ�û�id
	
	public Note(){}
	
	public int getNuserid() {
		return nuserid;
	}

	public void setNuserid(int nuserid) {
		this.nuserid = nuserid;
	}

	public int getNcuserid() {
		return ncuserid;
	}

	public void setNcuserid(int ncuserid) {
		this.ncuserid = ncuserid;
	}

	public Set getNoteComments() {
		return noteComments;
	}

	public void setNoteComments(Set noteComments) {
		this.noteComments = noteComments;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public Date getNsendTime() {
		return nsendTime;
	}

	public void setNsendTime(Date nsendTime) {
		this.nsendTime = nsendTime;
	}

	public String getNstate() {
		return nstate;
	}

	public void setNstate(String nstate) {
		this.nstate = nstate;
	}

}
