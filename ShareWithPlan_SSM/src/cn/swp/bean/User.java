package cn.swp.bean;

import java.util.Set;

/**
 * 普通用户实体类
 * 
 * @author Administrator
 *	@Version 1.0 2017/9/24
 */
public class User {
	
	private int id;//物理主标识：没有实际意义
	private String userNum;//用户账号：用于登录
	private String username;//用户名：用于显示
	private String password;//用户密码：用于登录
	private String professional;//职业：用于匹配公开笔记
	private Set notes;//一个人可以有多条笔记
	private Set plans;//一个人可以有多个计划
	
	
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
