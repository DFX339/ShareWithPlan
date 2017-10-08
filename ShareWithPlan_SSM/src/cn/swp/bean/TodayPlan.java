package cn.swp.bean;

import java.util.Date;

/**
 * 今日计划实体类
 * @author Administrator
 *
 */
public class TodayPlan {

	private int tpid;//计划的id主标识
	private String tpContent;//计划内容
	private Date tpCreateTime;//计划创建时间
	private long tpEndTime;//计划结束时间
	private int tpuserid;//普通用户id
	private int tpcuserid;//企业用户id
	
	
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
