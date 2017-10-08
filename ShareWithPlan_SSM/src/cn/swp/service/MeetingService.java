package cn.swp.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.swp.bean.Meeting;
import cn.swp.bean.MeetingComment;
import cn.swp.dao.ShareWithPlanMapper;

/**
 * 会议内容的业务类
 * @author Administrator
 * @version1.0 2017/9/25
 */
public class MeetingService {
	
	//注入持久层对象
	@Resource(name="Mapper")
	private ShareWithPlanMapper meetingDaoImpl;
	
	//添加会议内容
	public int addMeeting(Meeting meeting){
		meeting.setMtime(new Date());
		return meetingDaoImpl.addMeeting(meeting);
	}
	
	//修改会议内容
	public boolean updateMeeting(Meeting meeting){
		return meetingDaoImpl.updateMeeting(meeting);
	}
	
	//根据会议id查看会议内容
	public Meeting findMeeting(int mid){
		return meetingDaoImpl.findMeeting(mid);
	}
	
	//查看所有的会议记录内容
	public List<Meeting> findAllMeetings(String mcompanyNum){
		return meetingDaoImpl.findAllMeetings(mcompanyNum);
	}
	
	//查看所有的会议记录评论内容
	public List<MeetingComment> findAllMeetingComment(int mcmid){
		return meetingDaoImpl.findAllMeetingComment(mcmid);
	}
	
	//根据会议id删除会议记录
	public boolean deleteMeeting(int mid){
		return meetingDaoImpl.deleteMeeting(mid);
	}
	
	//根据会议id添加会议评论
	public int addMeetComment(int mcmid,MeetingComment meetComments){
		meetComments.setMcmid(mcmid);
		meetComments.setMcTime(new Date());
		return meetingDaoImpl.addMeetComment(meetComments);
	}
	
	//根据会议评论id删除会议评论
	public boolean deleteMeetComment(int mcid){
		return meetingDaoImpl.deleteMeetComment(mcid);
	}
	
}
