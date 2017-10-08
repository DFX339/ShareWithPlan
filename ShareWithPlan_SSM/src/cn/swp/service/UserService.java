package cn.swp.service;

import javax.annotation.Resource;

import cn.swp.bean.User;
import cn.swp.dao.ShareWithPlanMapper;

public class UserService {
	
	//ע��־ò����
	@Resource(name="Mapper")
	private ShareWithPlanMapper userDaoImpl;
	
	//��¼
	public User userLogin(String userNum,String password){
		User user = new User();
		user.setUserNum(userNum);
		user.setPassword(password);
		return userDaoImpl.userLogin(user);
	}
	
	//ע��
	public boolean userRegist(User user){
		
		return userDaoImpl.userRegist(user);
	}
	
}
