package cn.swp.bean;

import java.util.Set;

/**
 * ��ͨ�û�ʵ����
 * 
 * @author Administrator
 *	@Version 1.0 2017/9/24
 */
public class User {
	
	private int id;//��������ʶ��û��ʵ������
	private String userNum;//�û��˺ţ����ڵ�¼
	private String username;//�û�����������ʾ
	private String password;//�û����룺���ڵ�¼
	private String professional;//ְҵ������ƥ�乫���ʼ�
	private Set notes;//һ���˿����ж����ʼ�
	private Set plans;//һ���˿����ж���ƻ�
	
	
	public User(){}
	
	public Set getNotes() {
		return notes;
	}



	public void setNotes(Set notes) {
		this.notes = notes;
	}



	public Set getPlans() {
		return plans;
	}



	public void setPlans(Set plans) {
		this.plans = plans;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
	
}
