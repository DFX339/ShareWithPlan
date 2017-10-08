package cn.swp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.swp.bean.CompanyUser;
import cn.swp.bean.Meeting;
import cn.swp.bean.MeetingComment;
import cn.swp.bean.Note;
import cn.swp.bean.NoteComment;
import cn.swp.bean.TodayPlan;
import cn.swp.bean.User;
import cn.swp.exception.LoginException;
import cn.swp.exception.MeetingException;
import cn.swp.exception.RegistException;
import cn.swp.exception.TodayPlanException;
import cn.swp.service.CompanyUserService;
import cn.swp.service.MeetingService;
import cn.swp.service.NoteService;
import cn.swp.service.TodayPlanService;
import cn.swp.service.UserService;

/**
 * ��˿�������
 * @author Administrator
 * @version 1.0 2017/09/25
 */

@Controller
public class ShareWithPlanController {
	
	//ע��ҵ������
	//��ͨ�û���ҵ������ע��
	@Resource(name="userService")
	private UserService userService;
	
	//��ҵ�û���ҵ�����ע��
	@Resource(name="companyUserService")
	private CompanyUserService companyUserService;
	
	//�����ҵ������ע��
	@Resource(name="meetingService")
	private MeetingService meetingService;
	
	//�ʼǵ�ҵ������ע��
	@Resource(name="noteService")
	private NoteService noteService;
	
	//���ռƻ���ҵ������ע��
	@Resource(name="todayPlanService")
	private TodayPlanService todayPlanService;
	

	
	/**
	 * ��ͨ�û���¼
	 * @param userNum
	 * @param password
	 * @return
	 */
	@RequestMapping("/userLogin.do")
	public String userLogin(String userNum,String password,HttpServletRequest request){
		/*
		 *������ͨ�û����˺ź�����ƥ���¼
		 *�������ֵΪnull����ʾ�˺ź��û�����ƥ�� ��¼ʧ�� �׳��쳣
		 *�������ֵ��Ϊnull����ʾ��¼�ɹ� ���ص� /WEB-INF/jsp/main.jspҳ�� 
		 */
		User user = userService.userLogin(userNum, password);
		request.getSession().setAttribute("user", user);
		if(user != null){
			return "main";
		}
		throw new LoginException("��¼�쳣");
	}
	
	/**
	 * ��ҵ�û���¼
	 * @param cuserNum
	 * @param cpassword
	 * @return
	 */
	@RequestMapping("/companyUserLogin.do")
	public String companyUserLogin(String cuserNum,String cpassword,HttpServletRequest request){

		/*
		 *������ҵ�û����˺ź�����ƥ���¼
		 *�������ֵΪnull����ʾ�˺ź��û�����ƥ�� ��¼ʧ�� �׳��쳣
		 *�������ֵ��Ϊnull����ʾ��¼�ɹ� ���ص� /WEB-INF/jsp/main.jspҳ�� 
		 */
		CompanyUser companyUser = companyUserService.login(cuserNum, cpassword);
		request.getSession().setAttribute("companyUser", companyUser);
		if(companyUser != null){
			return "main";
		}
		throw new LoginException("��¼�쳣");
	}
	
	/**
	 * ��ͨ�û�ע��
	 * @param user
	 * @return
	 */
	@RequestMapping("/userRegist.do")
	public String userRegist(User user,HttpServletRequest request){
		/*
		 * �ռ����е����ݽ���ע��
		 * springMVC���Զ��ռ����ݲ���������͵�ת��
		 * �����ݴ洢�����ݿ⣬ע��ɹ��󷵻ص���¼����
		 */
		boolean flag = userService.userRegist(user);
		if(flag){
			return "userLogin";
		}
		throw new RegistException("ע���쳣");
	}
	
	/**
	 * ��ҵ�û�ע��
	 * @param companyUser
	 * @return
	 */
	@RequestMapping("/companyUserRegist.do")
	public String companyUserRegist(CompanyUser companyUser,HttpServletRequest request){
		/*
		 * �ռ����е����ݽ���ע��
		 * springMVC���Զ��ռ����ݲ���������͵�ת��
		 * �����ݴ洢�����ݿ⣬ע��ɹ��󷵻ص���¼����
		 */
		boolean flag = companyUserService.regist(companyUser);
		if(flag){
			return "companyUserLogin";
		}
		throw new RegistException("ע���쳣");
	}
	
	
//-------------------------------------------רְ���鴦��-----------------------------------------	
	/**
	 * ��ӻ�������
	 * @param meeting
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMeeting.do")
	public String addMeeting(Meeting meeting,HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ���������������ӻ�������
		 * �����Ϊnull��ȡ����ǰ��ҵ�û���cid�Լ���˾���룬���õ����������
		 * ���������洢�����ݿ���
		 */
		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		meeting.setMcuserid(companyUser.getCid());
		meeting.setMcompanyNum(companyUser.getCompanyNum());
		int mid = meetingService.addMeeting(meeting);
//		request.getSession().setAttribute("mid", mid);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * �޸Ļ�������
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateMeeting.do")
	public String updateMeeting(int mid,HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ��������������޸Ļ�������
		 * �����Ϊnull�ͻ�ȡ���е���Ҫ�޸ĵĲ�������Ӧ�Ĳ���ֵ �赽meeting������
		 * ���͸�������
		 */
		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		/*
		 * �õ������޸ĵĻ�������
		 * ���ݻ���id��ѯ������ǰ�Ļ����¼��������Meeting����
		 * ��meeting�����е�ֵ�޸�Ϊ�����ύ������
		 * ����ҵ�������޸ķ���
		 */
		String mcontent = request.getParameter("mContent");
		Meeting meeting = meetingService.findMeeting(mid);
		meeting.setmContent(mcontent);
		meeting.setMtime(new Date());
		meetingService.updateMeeting(meeting);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * ��ѯ�����еĻ����¼
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllMeetings.do")
	public String findAllMeetings(HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ�������������鿴��������
		 * �����Ϊnull��ȡ����ǰ��ҵ�û��Ĺ�˾����
		 * ���ݹ�˾���뵽���ݿ��в�ѯ������˾�����л����¼�浽list������
		 * ��list���ϴ浽request���ö�����
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		String companyNum = companyUser.getCompanyNum();
		List<Meeting> meetings = meetingService.findAllMeetings(companyNum);
		request.setAttribute("meetings", meetings);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * ��ѯ�����еĻ����¼����
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllMeetingComment.do")
	public String findAllMeetingComment(HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ�������������鿴������������
		 * �����Ϊnull��ȡ����ǰ��ҵ�û��Ĺ�˾����
		 * ���ݹ�˾�����ѯ������˾�����л����¼ �浽list���� meetings��
		 * ������ÿ�������¼��id�����ݻ���id��ѯ�����������¼������ 
		 * �����۴浽�µ�list����meetingComments��
		 * ���������ݴ浽request���ö�����
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		int cid = companyUser.getCid();
		List<Meeting> meetings = meetingService.findAllMeetings(companyUser.getCompanyNum());
		List list = new ArrayList();
		for(int i=0;i<meetings.size();i++){
			List<MeetingComment> meetingComments = meetingService.findAllMeetingComment(meetings.get(i).getMid());
			list.add(meetingComments);
		}
		request.setAttribute("meetingComments", list);
		return "redirect:/toMeeting.do";
	}
	
	
	
	/**
	 * ����mid��ѯ�����¼
	 * @param request
	 * @return
	 */
	@RequestMapping("/findMeeting.do")
	public String findMeeting(HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ����������������id��ѯ��������
		 * �����Ϊnull�ʹӱ��л�ȡ����id
		 * ����ҵ����Ӧ��������id���в�ѯ
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){  
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		int mid = Integer.parseInt(request.getParameter("mid"));
		Meeting meeting = meetingService.findMeeting(mid);
		request.setAttribute("meeting", meeting);
		return "redirect:/toMeeting.do";
	}
	
	
	/**
	 * ����midɾ�������¼
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteMeeting.do")
	public String deleteMeeting(HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ�������������ɾ����������
		 * �����Ϊnull�ͻ�ȡ����ǰѡ�еĻ���id
		 * ���ݻ���id����ҵ�����Ӧ����ɾ����������
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		int mid = Integer.parseInt(request.getParameter("mid"));
		meetingService.deleteMeeting(mid);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * ��ӻ����¼����������
	 * @param meetingComment
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMeetingComment.do")
	public String addMeetingComment(int mid,MeetingComment meetingComment,HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ���������������ӻ�����������
		 * �����Ϊnull�ͻ�ȡ���е����ݵ���ҵ���ķ���������
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		int mcid = meetingService.addMeetComment(mid,meetingComment);
//		request.getSession().setAttribute("mcid", mcid);
		return "redirect:/toMeeting.do";
	}
	
	
	/**
	 * ɾ�������¼��ĳ������
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteMeetingComment.do")
	public String deleteMeetingComment(HttpServletRequest request){
		
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ҵ�û���
		 * ���Ϊnull����ʾ���û�Ϊ�Ƿ��û� ���Ͳ�������������ɾ��������������
		 * �����Ϊnull�ʹ������л�ȡ��������id
		 * ���ݻ�������id ɾ��������������
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("�Ƿ��û��ķǷ�����");
		}
		int mcid = Integer.parseInt(request.getParameter("mcid"));
		meetingService.deleteMeetComment(mcid);
		return "redirect:/toMeeting.do";
	}
	
	
//-------------------------------------------רְ�ʼǴ���------------------------------------------	
	
	
	/**
	 * ��ӱʼ�����
	 * @param note
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNote.do")
	public String addMeeting(Note note,HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Wie��ͨ�û���¼��
		 * �����Ϊnull���ͻ�ȡ��ǰ��ͨ�û���id������ͨ�û���id�赽�ʼ���
		 * springmvc�Զ���ȡ���еĲ�������װ��note������
		 * ����ҵ������ķ�����note����浽���ݿ���
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull���ͻ�ȡ��ǰ��ҵ�û���cid������ҵ�û���id�赽�ʼ���
		 * springmvc�Զ���ȡ���еĲ�������װ��note������
		 * ����ҵ������ķ�����note����浽���ݿ���
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */

		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//�ж�Ϊ��ҵ�û���Ĳ���
			note.setNcuserid(companyUser.getCid());
			System.out.println(note.getNcuserid()+"��ҵ�û�id");
			note.setNsendTime(new Date());
			int nid = noteService.addNoteCompany(note);
			request.getSession().setAttribute("nid", nid);
			return "redirect:/toNote.do";
		}
		//�ж�Ϊ��ͨ�û��Ĳ���
		note.setNuserid(user.getId());
		note.setNsendTime(new Date());
		System.out.println(note.getNuserid()+"��ͨ�û�id");
		int nid = noteService.addNote(note);
		request.getSession().setAttribute("nid", nid);
		return "redirect:/toNote.do";

	}
	
	/**
	 * �޸ıʼ�����
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateNote.do")
	public String updateNote(int nid,Note note,HttpServletRequest request){
		
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Wie��ͨ�û���¼��
		 * �����Ϊnull������ҵ��������Ӧ������ɸ��±ʼ�
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull������ҵ��������Ӧ������ɸ��±ʼ�
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			noteService.updateNote(nid, note);
			return "redirect:/toNote.do";
		}
		noteService.updateNote(nid, note);
		return "redirect:/toNote.do";
	}
	
	/**
	 * ��ѯ�����еıʼ�
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllNotes.do")
	public String findAllNotes(HttpServletRequest request){
		
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Wie��ͨ�û���¼��
		 * �����Ϊnull����ȡ����ͨ�û���id
		 * ����id��ѯ����ǰ�û������бʼǣ��Լ����ݿ������й������ݵıʼ�
		 * ���ʼǴ浽list�����У�list���ϴ浽request���ö�����
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull��ȡ����ҵ�û���id
		 * ����id��ѯ����ǰ�û������бʼǣ��Լ����ݿ������й������ݵıʼ�
		 * ���ʼǴ浽list�����У�list���ϴ浽request���ö�����
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ҵ�û���¼
			int cid = companyUser.getCid();
			List<Note> notes = noteService.findAllNote(cid);
			request.setAttribute("notes", notes);
			return "redirect:/toNote.do";
		}
		//��ǰ��¼�û�Ϊ��ͨ�û�
		int id = user.getId();
		List<Note> notes = noteService.findAllNote(id);
		request.setAttribute("notes", notes);
		return "redirect:/toNote.do";
	}
	
	/**
	 * ��ѯ�����еıʼ�����
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllNoteComment.do")
	public String findAllNoteComment(int nid,HttpServletRequest request){

		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Wie��ͨ�û���¼��
		 * �����Ϊnull����ȡ�����д����ıʼ�id
		 * ���ݱʼ�id����ҵ��㷽����ѯ�����еıʼ���������
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull��ȡ�����д����ıʼ�id
		 * ���ݱʼ�id����ҵ��㷽����ѯ�����еıʼ���������
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ҵ�û���¼
			List<NoteComment> noteComments = noteService.findAllNoteComment(nid);
			request.setAttribute("noteComments", noteComments);
				return "redirect:/toNote.do";
		}
			//��ǰ��¼�û�Ϊ��ͨ�û�
			List<NoteComment> noteComments = noteService.findAllNoteComment(nid);
			request.setAttribute("noteComments", noteComments);
			return "redirect:/toNote.do";
	}
	
	
	/**
	 * ����nid��ѯ�ʼ�
	 * @param request
	 * @return
	 */
	@RequestMapping("/findNote.do")
	public String findNote(HttpServletRequest request){

		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Wie��ͨ�û���¼��
		 * �����Ϊnull����ȡ�����д����ıʼ�id
		 * ���ݱʼ�id����ҵ��㷽����ѯ���ʼ�����
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull��ȡ�����д����ıʼ�id
		 * ���ݱʼ�id����ҵ��㷽����ѯ���ʼ�����
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ҵ�û���¼
			int nid = Integer.parseInt(request.getParameter("nid"));
			Note note = noteService.findNote(nid);
			request.setAttribute("note", note);
			return "redirect:/toNote.do";
		}
		//��ǰ��¼�û�Ϊ��ͨ�û�
		int nid = Integer.parseInt(request.getParameter("nid"));
		Note note = noteService.findNote(nid);
		request.setAttribute("note", note);
		return "redirect:/toNote.do";
	}
	
	
	/**
	 * ����nidɾ���ʼ�
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteNote.do")
	public String deleteNote(HttpServletRequest request){

		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Wie��ͨ�û���¼��
		 * �����Ϊnull����ȡ�����д����ıʼ�id
		 * ���ݱʼ�id����ҵ��㷽��ɾ���ʼ�
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull��ȡ�����д����ıʼ�id
		 * ���ݱʼ�id����ҵ��㷽��ɾ���ʼ�
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ҵ�û���¼
			int nid = Integer.parseInt(request.getParameter("nid"));
			noteService.deleteNote(nid);
			return "redirect:/toNote.do";
		}
		//��ǰ��¼�û�Ϊ��ͨ�û�
		int nid = Integer.parseInt(request.getParameter("nid"));
		noteService.deleteNote(nid);
		return "redirect:/toNote.do";
	}
	
	/**
	 * ��ӱʼǵ���������
	 * @param noteComment
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNoteComment.do")
	public String addNoteComment(int nid,NoteComment noteComment,HttpServletRequest request){

		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Wie��ͨ�û���¼��
		 * �����Ϊnull���õ���ǰ��¼��ͨ�û���id�Ѿ��û����������赽NoteComment������
		 * ����ҵ��㷽����ɶ�NoteComment����Ĵ洢
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull�õ���ǰ��¼��ҵ�û���id�Ѿ��û����������赽NoteComment������
		 * ����ҵ��㷽����ɶ�NoteComment����Ĵ洢
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ҵ�û���¼
			noteComment.setNcCommentID(companyUser.getCid());
			noteComment.setNcCommentName(companyUser.getCusername());
			int ncid = noteService.addNoteComment(nid,noteComment);
			request.getSession().setAttribute("ncid", ncid);
			return "redirect:/toNote.do";
		}
		//��ǰ��¼�û�Ϊ��ͨ�û�
		noteComment.setNcCommentID(user.getId());
		noteComment.setNcCommentName(user.getUsername());
		int ncid = noteService.addNoteComment(nid,noteComment);
		request.getSession().setAttribute("ncid", ncid);
		return "redirect:/toNote.do";
	}
	
	
	/**
	 * ɾ���ʼǵ�ĳ������
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteNoteComment.do")
	public String deleteNoteComment(HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Ϊ��ͨ�û���¼��
		 * �����Ϊnull����ȡ�����еĲ��� �ʼ�����id
		 * ��ҵ��㷽����ɶԸ���ncidɾ����Ӧ��NOTEComment����
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull����ȡ�����еĲ��� �ʼ�����id
		 * ��ҵ��㷽����ɶԸ���ncidɾ����Ӧ��NOTEComment����
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ҵ�û���¼
			int ncid = Integer.parseInt(request.getParameter("ncid"));
			noteService.deleteNoteComment(ncid);
			return "redirect:/toNote.do";
		}
		//��ǰ��¼�û�Ϊ��ͨ�û�
		int ncid = Integer.parseInt(request.getParameter("ncid"));
		noteService.deleteNoteComment(ncid);
		return "redirect:/toNote.do";
	}
	
	
//-------------------------------------------���ռƻ�����------------------------------------------	
	
	/**
	 * ��ӽ��ռƻ�
	 * @param todayPlan
	 * @param request
	 * @return
	 */
	@RequestMapping("/addTodayPlan.do")
	public String addTodayPlan(TodayPlan todayPlan,HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Ϊ��ͨ�û���¼��
		 * �����Ϊnull����ȡ��ͨ�û���id����id���õ��ƻ�������
		 * ��ҵ��㷽����ɶ���Ӽƻ�����
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull����ȡ��ҵ�û���id����id���õ��ƻ�������
		 * ��ҵ��㷽����ɶ���Ӽƻ�����
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ǰ��¼����Ϊ��ҵ�û�
			todayPlan.setTpcuserid(companyUser.getCid());
			todayPlan.setTpCreateTime(new Date());
			String lastTime = request.getParameter("tpEndTime");
			long last = Long.parseLong(lastTime);
			todayPlan.setTpEndTime(last*1000);
			int tpid = todayPlanService.addTodayPlanCompany(todayPlan);
			request.getSession().setAttribute("tpid", tpid);
			return "redirect:/toTodayPlan.do";
		}
		//��ǰ��¼����Ϊ��ͨ�û�
		todayPlan.setTpuserid(user.getId());
		todayPlan.setTpCreateTime(new Date());
		String lastTime = request.getParameter("tpEndTime");
		long last = Long.parseLong(lastTime);
		todayPlan.setTpEndTime(last*1000);
		int tpid = todayPlanService.addTodayPlan(todayPlan);
		request.getSession().setAttribute("tpid", tpid);
		return "redirect:/toTodayPlan.do";
	}
	
	/**
	 * �޸Ľ��ռƻ�
	 * @param todayPlan
	 * @param tpid
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateTodayPlan.do")
	public String updateTodayPlan(int tpid,TodayPlan todayPlan,HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Ϊ��ͨ�û���¼��
		 * �����Ϊnull��
		 * ��ҵ��㷽������޸ļƻ�����
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull����ȡ��ҵ�û���id����id���õ��ƻ�������
		 * ��ҵ��㷽������޸ļƻ�����
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ǰ��¼����Ϊ��ҵ�û�
			todayPlan.setTpCreateTime(new Date());
			todayPlanService.updateTodayPlan(tpid, todayPlan);
			return "redirect:/toTodayPlan.do";
		}
		//��ǰ��¼�û�Ϊ��ͨ�û�
		todayPlan.setTpCreateTime(new Date());
		todayPlanService.updateTodayPlan(tpid, todayPlan);
		return "redirect:/toTodayPlan.do";
	}
	
	/**
	 * �鿴���ռƻ�
	 * @param tpid
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTodayPlan.do")
	public String findTodayPlan(int tpid,HttpServletRequest request){
		/*
		 * �ȴ�session��ȡ�õ�ǰ��¼����ͨ�û������ж��Ƿ�Ϊ��ͨ�û���¼��
		 * �����Ϊnull���������л�ȡ�ƻ�id
		 * ��ҵ��㷽�����ݼƻ�id��ѯ�ƻ�
		 * 
		 * ���Ϊnull���ͼ����ж��Ƿ�Ϊ��ҵ�û���¼
		 * �����Ϊnull���������л�ȡ�ƻ�id
		 * ��ҵ��㷽�����ݼƻ�id��ѯ�ƻ�
		 * 
		 * �����ͨ�û�����ҵ�û���Ϊnull�����׳��쳣 ˵���Ƿ��û� ��ֹ�ò���
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan�Ƿ��û��ķǷ�����");
			}
			//��ǰ��¼����Ϊ��ҵ�û�
			TodayPlan todayPlan = todayPlanService.findTodayPlan(tpid);
			request.setAttribute("todayPlan", todayPlan);
			return "redirect:/toTodayPlan.do";
		}
		//��ǰ��¼�û�Ϊ��ͨ�û�
		TodayPlan todayPlan = todayPlanService.findTodayPlan(tpid);
		request.setAttribute("todayPlan", todayPlan);
		return "redirect:/toTodayPlan.do";
	}
	
	/**
	 * �鿴���мƻ�
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllTodayPlan.do")
	public String findAllTodayPlan(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//�ж��û��Ƿ��Ѿ���¼
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//�û�δ��¼�����ص�¼ҳ��
				return "userLogin";
			}else{
				int cid = companyUser.getCid();
				List<TodayPlan> todayPlans = todayPlanService.findAllTodayPlanCompany(cid);
				request.setAttribute("todayPlans", todayPlans);
				return "redirect:/toTodayPlan.do";
			}
		}else{
			int id = user.getId();
			List<TodayPlan> todayPlans = todayPlanService.findAllTodayPlan(id);
			request.setAttribute("todayPlans", todayPlans);
			return "redirect:/toTodayPlan.do";
		}
		
	}
	
	/**
	 * ɾ�����ռƻ�
	 * @param tpid
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteTodayPlan.do")
	public String deleteTodayPlan(int tpid,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//�ж��û��Ƿ��Ѿ���¼
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//�û�δ��¼�����ص�¼ҳ��
				return "userLogin";
			}else{
				todayPlanService.deleteTodayPlan(tpid);
				return "redirect:/toTodayPlan.do";
			}
		}
		todayPlanService.deleteTodayPlan(tpid);
		return "redirect:/toTodayPlan.do";
	}
	
	
//---------------------------------------רְ----ҳ����ת------------------------------------------
	
	/**
	 * ��index.jspҳ����ת����ͨ�û���¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toLogin(HttpServletRequest request){
			return "userLogin";
	}
	
	/**
	 * ��userLogin.jspҳ����ת����¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUserRegist.do")
	public String toUserRegist(HttpServletRequest request){
			return "userRegist";
	}
	
	/**
	 * ��index.jspҳ����ת����ҵ�û���¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toCompanyUserLogin.do")
	public String toCompanyUserLogin(HttpServletRequest request){
			return "companyUserLogin";
	}
	
	/**
	 * ��companyUserLogin.jspҳ����ת����ҵ�û���¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toCompanyUserRegist.do")
	public String toCompanyUserRegist(HttpServletRequest request){
			return "companyUserRegist";
	}
	
	/**
	 * ��ת����Ŀ��ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toMain.do")
	public String toMain(HttpServletRequest request){
			return "main";
	}
	
	
	/**
	 * ��ת���ʼǷ���ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toNote.do")
	public String toNote(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//�ж��û��Ƿ��Ѿ���¼
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//�û�δ��¼�����ص�¼ҳ��
				return "userLogin";
			}else{
				int cid = companyUser.getCid();
				List<Note> notes = noteService.findAllNoteCompany(cid);
				request.setAttribute("notes", notes);
				List list = new ArrayList();
				for(int i=0;i<notes.size();i++){
					List<NoteComment> noteComments = noteService.findAllNoteComment(notes.get(i).getNid());
					for(int j=0;j<noteComments.size();j++)
					list.add(noteComments);
				}
				request.setAttribute("noteComments", list);
				
				return "note";
			}
		}else{
			int id = user.getId();
			List<Note> notes = noteService.findAllNote(id);
			request.setAttribute("notes", notes);
			List list = new ArrayList();
			for(int i=0;i<notes.size();i++){
				List<NoteComment> noteComments = noteService.findAllNoteComment(notes.get(i).getNid());
				list.add(noteComments);
			}
			request.setAttribute("noteComments", list);
			
			return "note";
		}
	}
	
	/**
	 * ��ת�����ռƻ�ҳ��
	 * �ڽ��ռƻ�ҳ������ʾ���мƻ�
	 * @param request
	 * @return
	 */
	@RequestMapping("/toTodayPlan.do")
	public String toTodayPlan(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//�ж��û��Ƿ��Ѿ���¼
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//�û�δ��¼�����ص�¼ҳ��
				return "userLogin";
			}else{
				int cid = companyUser.getCid();
				List<TodayPlan> todayPlans = todayPlanService.findAllTodayPlanCompany(cid);
				request.setAttribute("todayPlans", todayPlans);
				return "todayPlan";
			}
		}else{
			int id = user.getId();
			List<TodayPlan> todayPlans = todayPlanService.findAllTodayPlan(id);
			request.setAttribute("todayPlans", todayPlans);
			return "todayPlan";
		}
	}
	
	
	/**
	 * ��ת�������¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toMeeting.do")
	public String toMeeting(HttpServletRequest request,HttpServletResponse response){
		//�ж��Ƿ�Ϊ�Ѿ���¼����ҵ��Ա
		CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
		if(companyUser != null){
			
			int cid = companyUser.getCid();
			String companyNum = companyUser.getCompanyNum(); 
			List<Meeting> meetings = meetingService.findAllMeetings(companyNum);
			request.setAttribute("meetings", meetings);
			List list = new ArrayList();
			for(int i=0;i<meetings.size();i++){
			List<MeetingComment> meetingComments = meetingService.findAllMeetingComment(meetings.get(i).getMid());
			list.add(meetingComments);
			}
			request.setAttribute("meetingComments", list);
			return "meeting";
		}else{
			//�����ǰ�û������Ѿ���¼����ҵ�û�������ת����Ŀ��ҳ��
			return "main";
		}
	}
	
	/**
	 * ��ת���޸Ļ����¼ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdateMeeting.do")
	public String toUpdateMeeting(int mid,String mContent,HttpServletRequest request){
		request.setAttribute("mid",mid);
		Meeting meeting = meetingService.findMeeting(mid);
		request.setAttribute("meeting",meeting);
		return "updateMeeting";
	}
	/**
	 * ��ת���ޱʼ�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdateNote.do")
	public String toUpdatenNote(int nid,HttpServletRequest request){
		request.setAttribute("nid", nid);
		Note note = noteService.findNote(nid);
		request.setAttribute("note",note);
		return "updateNote";
	}
	/**
	 * ��ת���޸Ľ��ռƻ�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdateTodayPlan.do")
	public String toUpdateTodayPlan(int tpid,String tpContent,HttpServletRequest request){
		request.setAttribute("tpid", tpid);
		TodayPlan todayPlan = todayPlanService.findTodayPlan(tpid);
		request.setAttribute("todayPlan", todayPlan);
		return "updateTodayPlan";
	}
}
