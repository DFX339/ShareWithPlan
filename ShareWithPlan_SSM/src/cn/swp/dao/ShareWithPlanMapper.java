package cn.swp.dao;

import java.util.List;

import cn.swp.bean.CompanyUser;
import cn.swp.bean.Meeting;
import cn.swp.bean.MeetingComment;
import cn.swp.bean.Note;
import cn.swp.bean.NoteComment;
import cn.swp.bean.TodayPlan;
import cn.swp.bean.User;
/**
 * 接口定义类
 * @author Administrator
 *
 */
public interface ShareWithPlanMapper {
	
	/**
	 * 企业用户
	 * @param userNum
	 * @param password
	 * @return
	 */
	public CompanyUser login(CompanyUser companyUser);//企业用户登录
	public boolean regist(CompanyUser companyUser);//企业用户注册
	
	/**
	 * 普通用户
	 * @param userNum
	 * @param password
	 * @return
	 */
	public User userLogin(User user);//普通用户登录
	public boolean userRegist(User user);//普通用户注册
	
	/**
	 * 会议
	 * @param meeting
	 * @return
	 */
	public int addMeeting(Meeting meeting);//添加会议内容
	public boolean updateMeeting(Meeting meeting);//修改会议内容
	public Meeting findMeeting(int mid);//根据会议id查看会议内容
	public List<Meeting> findAllMeetings(String mcompanyNum);//查看本公司的所有的会议记录内容（公司代码）
	public boolean deleteMeeting(int mid);//根据会议id删除会议记录
	public int addMeetComment(MeetingComment meetComment);//根据会议id添加会议评论
	public boolean deleteMeetComment(int mcid);//根据会议评论id删除会议评论
	public List<MeetingComment> findAllMeetingComment(int mcmid);//查看所有的会议记录评论
	
	
	/**
	 * 笔记
	 * @param note
	 * @return
	 */
	public int addNote(Note note);//添加笔记
	public int addNoteCompany(Note note);//添加笔记
	public boolean updateNote(Note note);//修改笔记
	public Note findNote(int nid);//根据id查询笔记
	public List<Note> findAllNote(int nuserid);//查询所有普通用户的公开的笔记以及当前用户的笔记
	public List<Note> findAllNoteCompany(int ncuserid);//查询所有企业用户的公开的笔记以及当前用户的笔记
	public boolean deleteNote(int nid);//根据笔记id删除笔记
	public List<NoteComment> findAllNoteComment(int nid);//查看所有笔记评论
	public int addNoteComment(NoteComment noteComment);//添加笔记评论
	public boolean deleteNoteComment(int ncid);//根据笔记评论id删除笔记评论
	
	/**
	 * 今日计划
	 * @param todayPlan
	 * @return
	 */
	public int addTodayPlan(TodayPlan todayPlan);//添加计划
	public int addTodayPlanCompany(TodayPlan todayPlan);//添加计划
	public boolean updateTodayPlan(TodayPlan todayPlan);//修改计划
	public TodayPlan findTodayPlan(int tpid);//查看今日计划
	public List<TodayPlan> findAllTodayPlan(int tpuserid);//查看当前登录的普通 用户的所有计划
	public List<TodayPlan> findAllTodayPlanCompany(int tpcuserid);//查看当前登录的企业 用户的所有计划
	public boolean deleteTodayPlan(int tpid);//删除今日计划
	
	
}
