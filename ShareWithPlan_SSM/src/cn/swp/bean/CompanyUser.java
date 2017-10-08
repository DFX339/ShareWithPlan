package cn.swp.bean;

import java.util.Set;

/**
 * 企业用户
 * @author Administrator
 *
 */
public class CompanyUser {
	
	private int cid;//物理主标识：没有实际意义
	private String cuserNum;//用户账号：用于登录
	private String cusername;//用户名：用于显示
	private String cpassword;//用户密码：用于登录
	private String cprofessional;//职业：用于匹配公开笔记
	private String companyNum;//公司编号：用来会议记录共享的
	private Set cnotes;//关联属性，一个用户可以有多条笔记
	private Set cplans;//关联属性，一个用户可以有多条计划
	private Set cmeetings;//关联属性，一个用户可以有多条会议记录
	
	public CompanyUser(){}

	public Set getCnotes() {
		return cnotes;
	}



	public void setCnotes(Set cnotes) {
		this.cnotes = cnotes;
	}



	public Set getCplans() {
		return cplans;
	}



	public void setCplans(Set cplans) {
		this.cplans = cplans;
	}



	public Set getCmeetings() {
		return cmeetings;
	}



	public void setCmeetings(Set cmeetings) {
		this.cmeetings = cmeetings;
	}



	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCuserNum() {
		return cuserNum;
	}

	public void setCuserNum(String cuserNum) {
		this.cuserNum = cuserNum;
	}

	public String getCusername() {
		return cusername;
	}

	public void setCusername(String cusername) {
		this.cusername = cusername;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getCprofessional() {
		return cprofessional;
	}

	public void setCprofessional(String cprofessional) {
		this.cprofessional = cprofessional;
	}

	public String getCompanyNum() {
		return companyNum;
	}

	public void setCompanyNum(String companyNum) {
		this.companyNum = companyNum;
	}
	
	
	
	
}