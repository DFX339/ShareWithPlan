package cn.swp.bean;

import java.util.Date;

/**
 * ���ռƻ�ʵ����
 * @author Administrator
 *
 */
public class TodayPlan {

	private int tpid;//�ƻ���id����ʶ
	private String tpContent;//�ƻ�����
	private Date tpCreateTime;//�ƻ�����ʱ��
	private long tpEndTime;//�ƻ�����ʱ��
	private int tpuserid;//��ͨ�û�id
	private int tpcuserid;//��ҵ�û�id
	
	
	public TodayPlan(){}
	
	

	public int getTpuserid() {
		return tpuserid;
	}



	public void setTpuserid(int tpuserid) {
		this.tpuserid = tpuserid;
	}



	public int getTpcuserid() {
		return tpcuserid;
	}



	public void setTpcuserid(int tpcuserid) {
		this.tpcuserid = tpcuserid;
	}



	public int getTpid() {
		return tpid;
	}

	public void setTpid(int tpid) {
		this.tpid = tpid;
	}


	public String getTpContent() {
		return tpContent;
	}

	public void setTpContent(String tpContent) {
		this.tpContent = tpContent;
	}

	public Date getTpCreateTime() {
		return tpCreateTime;
	}

	public void setTpCreateTime(Date tpCreateTime) {
		this.tpCreateTime = tpCreateTime;
	}

	public long getTpEndTime() {
		return tpEndTime;
	}

	public void setTpEndTime(long tpEndTime) {
		this.tpEndTime = tpEndTime;
	}
	
	
}
