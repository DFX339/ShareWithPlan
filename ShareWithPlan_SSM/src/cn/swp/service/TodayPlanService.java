package cn.swp.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.swp.bean.TodayPlan;
import cn.swp.dao.ShareWithPlanMapper;

/**
 * ���ռƻ���ҵ��ʵ����
 * @author Administrator
 * @version1.0  2017/9/25
 */
public class TodayPlanService {

	//�����־ò����
	@Resource(name="Mapper")
	private ShareWithPlanMapper todayPlanDaoImpl;
	
	//��Ӽƻ�
	public int addTodayPlan(TodayPlan todayPlan){
		todayPlan.setTpCreateTime(new Date());
		return todayPlanDaoImpl.addTodayPlan(todayPlan);
	}
	//��Ӽƻ�
	public int addTodayPlanCompany(TodayPlan todayPlan){
		return todayPlanDaoImpl.addTodayPlanCompany(todayPlan);
	}
	
	//�޸ļƻ�
	public boolean updateTodayPlan(int tpid,TodayPlan todayPlan){
		todayPlan.setTpid(tpid);
		return todayPlanDaoImpl.updateTodayPlan(todayPlan);
	}
	
	//�鿴���ռƻ�
	public TodayPlan findTodayPlan(int tpid){
		return todayPlanDaoImpl.findTodayPlan(tpid);
	}
	
	//�鿴��ͨ�û����мƻ�
	public List<TodayPlan> findAllTodayPlan(int tpuserid){
		return todayPlanDaoImpl.findAllTodayPlan(tpuserid);
	}
	//�鿴��ҵ�û����мƻ�
	public List<TodayPlan> findAllTodayPlanCompany(int tpcuserid){
		return todayPlanDaoImpl.findAllTodayPlanCompany(tpcuserid);
	}
	
	//ɾ�����ռƻ�
	public boolean deleteTodayPlan(int tpid){
		return todayPlanDaoImpl.deleteTodayPlan(tpid);
	}
}
