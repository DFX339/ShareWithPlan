package cn.swp.bean;

import java.util.Date;
import java.util.Set;

/**
 * 笔记实体类
 * @author Administrator
 *
 */
public class Note {
	
	private int nid;//笔记ID，主标识
	private String ncontent;//笔记内容
	private Date nsendTime;//笔记发送时间
	private String nstate;//笔记状态
	private Set noteComments;//一条笔记可以有多条笔记评论
	private int nuserid;//普通用户id
	private int ncuserid;//企业用户id
	
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
