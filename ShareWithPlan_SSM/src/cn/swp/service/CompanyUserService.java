package cn.swp.service;

import javax.annotation.Resource;

import cn.swp.bean.CompanyUser;
import cn.swp.dao.ShareWithPlanMapper;
/**
 * 企业用户业务类
 * @author Administrator
 *
 */
public class CompanyUserService {
	
	//注入持久层对象
	@Resource(name="Mapper")
	private ShareWithPlanMapper companyUserDaoImpl;
	
	//登录
	public CompanyUser login(String cuserNum,String cpassword){
		CompanyUser companyUser = new CompanyUser();
		companyUser.setCuserNum(cuserNum);
		companyUser.setCpassword(cpassword);
		return companyUserDaoImpl.login(companyUser);
	}
	
	//注册
	public boolean regist(CompanyUser companyUser){
		
		return companyUserDaoImpl.regist(companyUser);
	}
}
