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
 * �ӿڶ�����
 * @author Administrator
 *
 */
public interface ShareWithPlanMapper {
	
	/**
	 * ��ҵ�û�
	 * @param userNum
	 * @param password
	 * @return
	 */
	public CompanyUser login(CompanyUser companyUser);//��ҵ�û���¼
	public boolean regist(CompanyUser companyUser);//��ҵ�û�ע��
	
	/**
	 * ��ͨ�û�
	 * @param userNum
	 * @param password
	 * @return
	 */
	public User userLogin(User user);//��ͨ�û���¼
	public boolean userRegist(User user);//��ͨ�û�ע��
	
	/**
	 * ����
	 * @param meeting
	 * @return
	 */
	public int addMeeting(Meeting meeting);//��ӻ�������
	public boolean updateMeeting(Meeting meeting);//�޸Ļ�������
	public Meeting findMeeting(int mid);//���ݻ���id�鿴��������
	public List<Meeting> findAllMeetings(String mcompanyNum);//�鿴����˾�����еĻ����¼���ݣ���˾���룩
	public boolean deleteMeeting(int mid);//���ݻ���idɾ�������¼
	public int addMeetComment(MeetingComment meetComment);//���ݻ���id��ӻ�������
	public boolean deleteMeetComment(int mcid);//���ݻ�������idɾ����������
	public List<MeetingComment> findAllMeetingComment(int mcmid);//�鿴���еĻ����¼����
	
	
	/**
	 * �ʼ�
	 * @param note
	 * @return
	 */
	public int addNote(Note note);//��ӱʼ�
	public int addNoteCompany(Note note);//��ӱʼ�
	public boolean updateNote(Note note);//�޸ıʼ�
	public Note findNote(int nid);//����id��ѯ�ʼ�
	public List<Note> findAllNote(int nuserid);//��ѯ������ͨ�û��Ĺ����ıʼ��Լ���ǰ�û��ıʼ�
	public List<Note> findAllNoteCompany(int ncuserid);//��ѯ������ҵ�û��Ĺ����ıʼ��Լ���ǰ�û��ıʼ�
	public boolean deleteNote(int nid);//���ݱʼ�idɾ���ʼ�
	public List<NoteComment> findAllNoteComment(int nid);//�鿴���бʼ�����
	public int addNoteComment(NoteComment noteComment);//��ӱʼ�����
	public boolean deleteNoteComment(int ncid);//���ݱʼ�����idɾ���ʼ�����
	
	/**
	 * ���ռƻ�
	 * @param todayPlan
	 * @return
	 */
	public int addTodayPlan(TodayPlan todayPlan);//��Ӽƻ�
	public int addTodayPlanCompany(TodayPlan todayPlan);//��Ӽƻ�
	public boolean updateTodayPlan(TodayPlan todayPlan);//�޸ļƻ�
	public TodayPlan findTodayPlan(int tpid);//�鿴���ռƻ�
	public List<TodayPlan> findAllTodayPlan(int tpuserid);//�鿴��ǰ��¼����ͨ �û������мƻ�
	public List<TodayPlan> findAllTodayPlanCompany(int tpcuserid);//�鿴��ǰ��¼����ҵ �û������мƻ�
	public boolean deleteTodayPlan(int tpid);//ɾ�����ռƻ�
	
	
}
