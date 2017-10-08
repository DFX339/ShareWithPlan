package cn.swp.service;

import javax.annotation.Resource;

import cn.swp.bean.User;
import cn.swp.dao.ShareWithPlanMapper;

public class UserService {
	
	//×¢Èë³Ö¾Ã²ã¶ÔÏó
	@Resource(name="Mapper")
	private ShareWithPlanMapper userDaoImpl;
	
	//µÇÂ¼
	public User userLogin(String userNum,String password){
		User user = new User();
		user.setUserNum(userNum);
		user.setPassword(password);
		return userDaoImpl.userLogin(user);
	}
	
	//×¢²á
	public boolean userRegist(User user){
		
		return userDaoImpl.userRegist(user);
	}
	
}
