package cn.swp.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.swp.bean.TodayPlan;
import cn.swp.dao.ShareWithPlanMapper;

/**
 * 今日计划的业务实现类
 * @author Administrator
 * @version1.0  2017/9/25
 */
public class TodayPlanService {

	//创建持久层对象
	@Resource(name="Mapper")
	private ShareWithPlanMapper todayPlanDaoImpl;
	
	//添加计划
	public int addTodayPlan(TodayPlan todayPlan){
		todayPlan.setTpCreateTime(new Date());
		return todayPlanDaoImpl.addTodayPlan(todayPlan);
	}
	//添加计划
	public int addTodayPlanCompany(TodayPlan todayPlan){
		return todayPlanDaoImpl.addTodayPlanCompany(todayPlan);
	}
	
	//修改计划
	public boolean updateTodayPlan(int tpid,TodayPlan todayPlan){
		todayPlan.setTpid(tpid);
		return todayPlanDaoImpl.updateTodayPlan(todayPlan);
	}
	
	//查看今日计划
	public TodayPlan findTodayPlan(int tpid){
		return todayPlanDaoImpl.findTodayPlan(tpid);
	}
	
	//查看普通用户所有计划
	public List<TodayPlan> findAllTodayPlan(int tpuserid){
		return todayPlanDaoImpl.findAllTodayPlan(tpuserid);
	}
	//查看企业用户所有计划
	public List<TodayPlan> findAllTodayPlanCompany(int tpcuserid){
		return todayPlanDaoImpl.findAllTodayPlanCompany(tpcuserid);
	}
	
	//删除今日计划
	public boolean deleteTodayPlan(int tpid){
		return todayPlanDaoImpl.deleteTodayPlan(tpid);
	}
}
