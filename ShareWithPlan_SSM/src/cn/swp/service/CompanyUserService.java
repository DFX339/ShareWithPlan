package cn.swp.service;

import javax.annotation.Resource;

import cn.swp.bean.CompanyUser;
import cn.swp.dao.ShareWithPlanMapper;
/**
 * ��ҵ�û�ҵ����
 * @author Administrator
 *
 */
public class CompanyUserService {
	
	//ע��־ò����
	@Resource(name="Mapper")
	private ShareWithPlanMapper companyUserDaoImpl;
	
	//��¼
	public CompanyUser login(String cuserNum,String cpassword){
		CompanyUser companyUser = new CompanyUser();
		companyUser.setCuserNum(cuserNum);
		companyUser.setCpassword(cpassword);
		return companyUserDaoImpl.login(companyUser);
	}
	
	//ע��
	public boolean regist(CompanyUser companyUser){
		
		return companyUserDaoImpl.regist(companyUser);
	}
}
