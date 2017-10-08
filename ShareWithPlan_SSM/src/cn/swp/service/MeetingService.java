package cn.swp.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.swp.bean.Meeting;
import cn.swp.bean.MeetingComment;
import cn.swp.dao.ShareWithPlanMapper;

/**
 * �������ݵ�ҵ����
 * @author Administrator
 * @version1.0 2017/9/25
 */
public class MeetingService {
	
	//ע��־ò����
	@Resource(name="Mapper")
	private ShareWithPlanMapper meetingDaoImpl;
	
	//��ӻ�������
	public int addMeeting(Meeting meeting){
		meeting.setMtime(new Date());
		return meetingDaoImpl.addMeeting(meeting);
	}
	
	//�޸Ļ�������
	public boolean updateMeeting(Meeting meeting){
		return meetingDaoImpl.updateMeeting(meeting);
	}
	
	//���ݻ���id�鿴��������
	public Meeting findMeeting(int mid){
		return meetingDaoImpl.findMeeting(mid);
	}
	
	//�鿴���еĻ����¼����
	public List<Meeting> findAllMeetings(String mcompanyNum){
		return meetingDaoImpl.findAllMeetings(mcompanyNum);
	}
	
	//�鿴���еĻ����¼��������
	public List<MeetingComment> findAllMeetingComment(int mcmid){
		return meetingDaoImpl.findAllMeetingComment(mcmid);
	}
	
	//���ݻ���idɾ�������¼
	public boolean deleteMeeting(int mid){
		return meetingDaoImpl.deleteMeeting(mid);
	}
	
	//���ݻ���id��ӻ�������
	public int addMeetComment(int mcmid,MeetingComment meetComments){
		meetComments.setMcmid(mcmid);
		meetComments.setMcTime(new Date());
		return meetingDaoImpl.addMeetComment(meetComments);
	}
	
	//���ݻ�������idɾ����������
	public boolean deleteMeetComment(int mcid){
		return meetingDaoImpl.deleteMeetComment(mcid);
	}
	
}
