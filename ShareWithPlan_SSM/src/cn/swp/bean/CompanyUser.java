package cn.swp.bean;

import java.util.Set;

/**
 * ��ҵ�û�
 * @author Administrator
 *
 */
public class CompanyUser {
	
	private int cid;//��������ʶ��û��ʵ������
	private String cuserNum;//�û��˺ţ����ڵ�¼
	private String cusername;//�û�����������ʾ
	private String cpassword;//�û����룺���ڵ�¼
	private String cprofessional;//ְҵ������ƥ�乫���ʼ�
	private String companyNum;//��˾��ţ����������¼�����
	private Set cnotes;//�������ԣ�һ���û������ж����ʼ�
	private Set cplans;//�������ԣ�һ���û������ж����ƻ�
	private Set cmeetings;//�������ԣ�һ���û������ж��������¼
	
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