package cn.swp.bean;

import java.util.Date;

/**
 * �����¼�����۵�ʵ����
 * @author Administrator
 *
 */
public class MeetingComment {
	
	private int mcid;//��������id
	private String mcContent;//��������
	private Date mcTime;//����ʱ��
	private int mcmid;//�����¼��id
	
	
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
