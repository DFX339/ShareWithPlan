package cn.swp.bean;

import java.util.Date;

/**
 * 会议记录的评论的实体类
 * @author Administrator
 *
 */
public class MeetingComment {
	
	private int mcid;//会议评论id
	private String mcContent;//评论内容
	private Date mcTime;//评论时间
	private int mcmid;//会议记录的id
	
	
	public int getMcmid() {
		return mcmid;
	}

	public void setMcmid(int mcmid) {
		this.mcmid = mcmid;
	}

	public MeetingComment(){}

	public int getMcid() {
		return mcid;
	}

	public void setMcid(int mcid) {
		this.mcid = mcid;
	}


	public String getMcContent() {
		return mcContent;
	}

	public void setMcContent(String mcContent) {
		this.mcContent = mcContent;
	}

	public Date getMcTime() {
		return mcTime;
	}

	public void setMcTime(Date mcTime) {
		this.mcTime = mcTime;
	}
	
	
	
	
}
