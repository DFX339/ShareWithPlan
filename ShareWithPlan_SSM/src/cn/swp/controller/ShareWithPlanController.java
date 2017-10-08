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
 * 后端控制器类
 * @author Administrator
 * @version 1.0 2017/09/25
 */

@Controller
public class ShareWithPlanController {
	
	//注入业务层对象
	//普通用户的业务对象的注入
	@Resource(name="userService")
	private UserService userService;
	
	//企业用户的业务对象注入
	@Resource(name="companyUserService")
	private CompanyUserService companyUserService;
	
	//会议的业务对象的注入
	@Resource(name="meetingService")
	private MeetingService meetingService;
	
	//笔记的业务对象的注入
	@Resource(name="noteService")
	private NoteService noteService;
	
	//今日计划的业务对象的注入
	@Resource(name="todayPlanService")
	private TodayPlanService todayPlanService;
	

	
	/**
	 * 普通用户登录
	 * @param userNum
	 * @param password
	 * @return
	 */
	@RequestMapping("/userLogin.do")
	public String userLogin(String userNum,String password,HttpServletRequest request){
		/*
		 *根据普通用户的账号和密码匹配登录
		 *如果返回值为null，表示账号和用户名不匹配 登录失败 抛出异常
		 *如果返回值不为null，表示登录成功 返回到 /WEB-INF/jsp/main.jsp页面 
		 */
		User user = userService.userLogin(userNum, password);
		request.getSession().setAttribute("user", user);
		if(user != null){
			return "main";
		}
		throw new LoginException("登录异常");
	}
	
	/**
	 * 企业用户登录
	 * @param cuserNum
	 * @param cpassword
	 * @return
	 */
	@RequestMapping("/companyUserLogin.do")
	public String companyUserLogin(String cuserNum,String cpassword,HttpServletRequest request){

		/*
		 *根据企业用户的账号和密码匹配登录
		 *如果返回值为null，表示账号和用户名不匹配 登录失败 抛出异常
		 *如果返回值不为null，表示登录成功 返回到 /WEB-INF/jsp/main.jsp页面 
		 */
		CompanyUser companyUser = companyUserService.login(cuserNum, cpassword);
		request.getSession().setAttribute("companyUser", companyUser);
		if(companyUser != null){
			return "main";
		}
		throw new LoginException("登录异常");
	}
	
	/**
	 * 普通用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/userRegist.do")
	public String userRegist(User user,HttpServletRequest request){
		/*
		 * 收集表单中的数据进行注册
		 * springMVC会自动收集数据并且完成类型的转换
		 * 将数据存储到数据库，注册成功后返回到登录界面
		 */
		boolean flag = userService.userRegist(user);
		if(flag){
			return "userLogin";
		}
		throw new RegistException("注册异常");
	}
	
	/**
	 * 企业用户注册
	 * @param companyUser
	 * @return
	 */
	@RequestMapping("/companyUserRegist.do")
	public String companyUserRegist(CompanyUser companyUser,HttpServletRequest request){
		/*
		 * 收集表单中的数据进行注册
		 * springMVC会自动收集数据并且完成类型的转换
		 * 将数据存储到数据库，注册成功后返回到登录界面
		 */
		boolean flag = companyUserService.regist(companyUser);
		if(flag){
			return "companyUserLogin";
		}
		throw new RegistException("注册异常");
	}
	
	
//-------------------------------------------专职会议处理-----------------------------------------	
	/**
	 * 添加会议内容
	 * @param meeting
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMeeting.do")
	public String addMeeting(Meeting meeting,HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求添加会议内容
		 * 如果不为null就取出当前企业用户的cid以及公司编码，设置到会议对象中
		 * 将会议对象存储到数据库中
		 */
		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("非法用户的非法请求");
		}
		meeting.setMcuserid(companyUser.getCid());
		meeting.setMcompanyNum(companyUser.getCompanyNum());
		int mid = meetingService.addMeeting(meeting);
//		request.getSession().setAttribute("mid", mid);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * 修改会议内容
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateMeeting.do")
	public String updateMeeting(int mid,HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求修改会议内容
		 * 如果不为null就获取表单中的需要修改的参数名对应的参数值 设到meeting对象中
		 * 发送更新请求
		 */
		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("非法用户的非法请求");
		}
		/*
		 * 得到表单中修改的会议内容
		 * 根据会议id查询出来当前的会议记录并且生成Meeting对象
		 * 将meeting对象中的值修改为表单中提交的数据
		 * 调用业务对象的修改方法
		 */
		String mcontent = request.getParameter("mContent");
		Meeting meeting = meetingService.findMeeting(mid);
		meeting.setmContent(mcontent);
		meeting.setMtime(new Date());
		meetingService.updateMeeting(meeting);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * 查询出所有的会议记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllMeetings.do")
	public String findAllMeetings(HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求查看会议内容
		 * 如果不为null就取出当前企业用户的公司编码
		 * 根据公司编码到数据库中查询出本公司的所有会议记录存到list集合中
		 * 将list集合存到request内置对象中
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("非法用户的非法请求");
		}
		String companyNum = companyUser.getCompanyNum();
		List<Meeting> meetings = meetingService.findAllMeetings(companyNum);
		request.setAttribute("meetings", meetings);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * 查询出所有的会议记录评论
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllMeetingComment.do")
	public String findAllMeetingComment(HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求查看会议内容评论
		 * 如果不为null就取出当前企业用户的公司编码
		 * 根据公司编码查询出本公司的所有会议记录 存到list集合 meetings中
		 * 迭代出每条会议记录的id，根据会议id查询出本条会议记录的评论 
		 * 将评论存到新的list集合meetingComments中
		 * 将评论内容存到request内置对象中
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("非法用户的非法请求");
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
	 * 根据mid查询会议记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/findMeeting.do")
	public String findMeeting(HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求根据id查询会议内容
		 * 如果不为null就从表单中获取会议id
		 * 调用业务层对应方法根据id进行查询
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){  
			throw new MeetingException("非法用户的非法请求");
		}
		int mid = Integer.parseInt(request.getParameter("mid"));
		Meeting meeting = meetingService.findMeeting(mid);
		request.setAttribute("meeting", meeting);
		return "redirect:/toMeeting.do";
	}
	
	
	/**
	 * 根据mid删除会议记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteMeeting.do")
	public String deleteMeeting(HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求删除会议内容
		 * 如果不为null就获取表单当前选中的会议id
		 * 根据会议id调用业务层相应方法删除会议内容
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("非法用户的非法请求");
		}
		int mid = Integer.parseInt(request.getParameter("mid"));
		meetingService.deleteMeeting(mid);
		return "redirect:/toMeeting.do";
	}
	
	/**
	 * 添加会议记录的评论内容
	 * @param meetingComment
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMeetingComment.do")
	public String addMeetingComment(int mid,MeetingComment meetingComment,HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求添加会议评论内容
		 * 如果不为null就获取表单中的数据调用业务层的方法完成添加
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("非法用户的非法请求");
		}
		int mcid = meetingService.addMeetComment(mid,meetingComment);
//		request.getSession().setAttribute("mcid", mcid);
		return "redirect:/toMeeting.do";
	}
	
	
	/**
	 * 删除会议记录的某条评论
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteMeetingComment.do")
	public String deleteMeetingComment(HttpServletRequest request){
		
		/*
		 * 先从session中取得当前登录的企业用户，
		 * 如果为null，表示该用户为非法用户 ，就不继续发送请求删除会议评论内容
		 * 如果不为null就从请求中获取会议评论id
		 * 根据会议评论id 删除会议评论内容
		 */

		CompanyUser companyUser = (CompanyUser) request.getSession().getAttribute("companyUser");
		if(companyUser == null){
			throw new MeetingException("非法用户的非法请求");
		}
		int mcid = Integer.parseInt(request.getParameter("mcid"));
		meetingService.deleteMeetComment(mcid);
		return "redirect:/toMeeting.do";
	}
	
	
//-------------------------------------------专职笔记处理------------------------------------------	
	
	
	/**
	 * 添加笔记内容
	 * @param note
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNote.do")
	public String addMeeting(Note note,HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的普通用户，（判断是否Wie普通用户登录）
		 * 如果不为null，就获取当前普通用户的id，将普通用户的id设到笔记中
		 * springmvc自动获取表单中的参数，封装到note对象中
		 * 调用业务层对象的方法将note对象存到数据库中
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null，就获取当前企业用户的cid，将企业用户的id设到笔记中
		 * springmvc自动获取表单中的参数，封装到note对象中
		 * 调用业务层对象的方法将note对象存到数据库中
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */

		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//判断为企业用户后的操作
			note.setNcuserid(companyUser.getCid());
			System.out.println(note.getNcuserid()+"企业用户id");
			note.setNsendTime(new Date());
			int nid = noteService.addNoteCompany(note);
			request.getSession().setAttribute("nid", nid);
			return "redirect:/toNote.do";
		}
		//判断为普通用户的操作
		note.setNuserid(user.getId());
		note.setNsendTime(new Date());
		System.out.println(note.getNuserid()+"普通用户id");
		int nid = noteService.addNote(note);
		request.getSession().setAttribute("nid", nid);
		return "redirect:/toNote.do";

	}
	
	/**
	 * 修改笔记内容
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateNote.do")
	public String updateNote(int nid,Note note,HttpServletRequest request){
		
		/*
		 * 先从session中取得当前登录的普通用户，（判断是否Wie普通用户登录）
		 * 如果不为null，调用业务对象的相应方法完成更新笔记
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null，调用业务对象的相应方法完成更新笔记
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			noteService.updateNote(nid, note);
			return "redirect:/toNote.do";
		}
		noteService.updateNote(nid, note);
		return "redirect:/toNote.do";
	}
	
	/**
	 * 查询出所有的笔记
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllNotes.do")
	public String findAllNotes(HttpServletRequest request){
		
		/*
		 * 先从session中取得当前登录的普通用户，（判断是否Wie普通用户登录）
		 * 如果不为null，就取出普通用户的id
		 * 根据id查询出当前用户的所有笔记，以及数据库中所有公开内容的笔记
		 * 将笔记存到list集合中，list集合存到request内置对象中
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null就取出企业用户的id
		 * 根据id查询出当前用户的所有笔记，以及数据库中所有公开内容的笔记
		 * 将笔记存到list集合中，list集合存到request内置对象中
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//企业用户登录
			int cid = companyUser.getCid();
			List<Note> notes = noteService.findAllNote(cid);
			request.setAttribute("notes", notes);
			return "redirect:/toNote.do";
		}
		//当前登录用户为普通用户
		int id = user.getId();
		List<Note> notes = noteService.findAllNote(id);
		request.setAttribute("notes", notes);
		return "redirect:/toNote.do";
	}
	
	/**
	 * 查询出所有的笔记评论
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllNoteComment.do")
	public String findAllNoteComment(int nid,HttpServletRequest request){

		/*
		 * 先从session中取得当前登录的普通用户，（判断是否Wie普通用户登录）
		 * 如果不为null，获取请求中传来的笔记id
		 * 根据笔记id调用业务层方法查询出所有的笔记评论内容
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null获取请求中传来的笔记id
		 * 根据笔记id调用业务层方法查询出所有的笔记评论内容
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//企业用户登录
			List<NoteComment> noteComments = noteService.findAllNoteComment(nid);
			request.setAttribute("noteComments", noteComments);
				return "redirect:/toNote.do";
		}
			//当前登录用户为普通用户
			List<NoteComment> noteComments = noteService.findAllNoteComment(nid);
			request.setAttribute("noteComments", noteComments);
			return "redirect:/toNote.do";
	}
	
	
	/**
	 * 根据nid查询笔记
	 * @param request
	 * @return
	 */
	@RequestMapping("/findNote.do")
	public String findNote(HttpServletRequest request){

		/*
		 * 先从session中取得当前登录的普通用户，（判断是否Wie普通用户登录）
		 * 如果不为null，获取请求中传来的笔记id
		 * 根据笔记id调用业务层方法查询出笔记内容
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null获取请求中传来的笔记id
		 * 根据笔记id调用业务层方法查询出笔记内容
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//企业用户登录
			int nid = Integer.parseInt(request.getParameter("nid"));
			Note note = noteService.findNote(nid);
			request.setAttribute("note", note);
			return "redirect:/toNote.do";
		}
		//当前登录用户为普通用户
		int nid = Integer.parseInt(request.getParameter("nid"));
		Note note = noteService.findNote(nid);
		request.setAttribute("note", note);
		return "redirect:/toNote.do";
	}
	
	
	/**
	 * 根据nid删除笔记
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteNote.do")
	public String deleteNote(HttpServletRequest request){

		/*
		 * 先从session中取得当前登录的普通用户，（判断是否Wie普通用户登录）
		 * 如果不为null，获取请求中传来的笔记id
		 * 根据笔记id调用业务层方法删除笔记
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null获取请求中传来的笔记id
		 * 根据笔记id调用业务层方法删除笔记
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//企业用户登录
			int nid = Integer.parseInt(request.getParameter("nid"));
			noteService.deleteNote(nid);
			return "redirect:/toNote.do";
		}
		//当前登录用户为普通用户
		int nid = Integer.parseInt(request.getParameter("nid"));
		noteService.deleteNote(nid);
		return "redirect:/toNote.do";
	}
	
	/**
	 * 添加笔记的评论内容
	 * @param noteComment
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNoteComment.do")
	public String addNoteComment(int nid,NoteComment noteComment,HttpServletRequest request){

		/*
		 * 先从session中取得当前登录的普通用户，（判断是否Wie普通用户登录）
		 * 如果不为null，得到当前登录普通用户的id已经用户名，并且设到NoteComment对象中
		 * 调用业务层方法完成对NoteComment对象的存储
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null得到当前登录企业用户的id已经用户名，并且设到NoteComment对象中
		 * 调用业务层方法完成对NoteComment对象的存储
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//企业用户登录
			noteComment.setNcCommentID(companyUser.getCid());
			noteComment.setNcCommentName(companyUser.getCusername());
			int ncid = noteService.addNoteComment(nid,noteComment);
			request.getSession().setAttribute("ncid", ncid);
			return "redirect:/toNote.do";
		}
		//当前登录用户为普通用户
		noteComment.setNcCommentID(user.getId());
		noteComment.setNcCommentName(user.getUsername());
		int ncid = noteService.addNoteComment(nid,noteComment);
		request.getSession().setAttribute("ncid", ncid);
		return "redirect:/toNote.do";
	}
	
	
	/**
	 * 删除笔记的某条评论
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteNoteComment.do")
	public String deleteNoteComment(HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的普通用户，（判断是否为普通用户登录）
		 * 如果不为null，获取请求中的参数 笔记评论id
		 * 用业务层方法完成对根据ncid删除对应的NOTEComment对象
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null，获取请求中的参数 笔记评论id
		 * 用业务层方法完成对根据ncid删除对应的NOTEComment对象
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//企业用户登录
			int ncid = Integer.parseInt(request.getParameter("ncid"));
			noteService.deleteNoteComment(ncid);
			return "redirect:/toNote.do";
		}
		//当前登录用户为普通用户
		int ncid = Integer.parseInt(request.getParameter("ncid"));
		noteService.deleteNoteComment(ncid);
		return "redirect:/toNote.do";
	}
	
	
//-------------------------------------------今日计划处理------------------------------------------	
	
	/**
	 * 添加今日计划
	 * @param todayPlan
	 * @param request
	 * @return
	 */
	@RequestMapping("/addTodayPlan.do")
	public String addTodayPlan(TodayPlan todayPlan,HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的普通用户，（判断是否为普通用户登录）
		 * 如果不为null，获取普通用户的id，将id设置到计划对象中
		 * 用业务层方法完成对添加计划对象
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null，获取企业用户的id，将id设置到计划对象中
		 * 用业务层方法完成对添加计划对象
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//当前登录对象为企业用户
			todayPlan.setTpcuserid(companyUser.getCid());
			todayPlan.setTpCreateTime(new Date());
			String lastTime = request.getParameter("tpEndTime");
			long last = Long.parseLong(lastTime);
			todayPlan.setTpEndTime(last*1000);
			int tpid = todayPlanService.addTodayPlanCompany(todayPlan);
			request.getSession().setAttribute("tpid", tpid);
			return "redirect:/toTodayPlan.do";
		}
		//当前登录对象为普通用户
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
	 * 修改今日计划
	 * @param todayPlan
	 * @param tpid
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateTodayPlan.do")
	public String updateTodayPlan(int tpid,TodayPlan todayPlan,HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的普通用户，（判断是否为普通用户登录）
		 * 如果不为null，
		 * 用业务层方法完成修改计划对象
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null，获取企业用户的id，将id设置到计划对象中
		 * 用业务层方法完成修改计划对象
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//当前登录对象为企业用户
			todayPlan.setTpCreateTime(new Date());
			todayPlanService.updateTodayPlan(tpid, todayPlan);
			return "redirect:/toTodayPlan.do";
		}
		//当前登录用户为普通用户
		todayPlan.setTpCreateTime(new Date());
		todayPlanService.updateTodayPlan(tpid, todayPlan);
		return "redirect:/toTodayPlan.do";
	}
	
	/**
	 * 查看今日计划
	 * @param tpid
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTodayPlan.do")
	public String findTodayPlan(int tpid,HttpServletRequest request){
		/*
		 * 先从session中取得当前登录的普通用户，（判断是否为普通用户登录）
		 * 如果不为null，从请求中获取计划id
		 * 用业务层方法根据计划id查询计划
		 * 
		 * 如果为null，就继续判断是否为企业用户登录
		 * 如果不为null，从请求中获取计划id
		 * 用业务层方法根据计划id查询计划
		 * 
		 * 如果普通用户和企业用户都为null，就抛出异常 说明非法用户 阻止该操作
		 */
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				throw new TodayPlanException("todayPlan非法用户的非法请求");
			}
			//当前登录对象为企业用户
			TodayPlan todayPlan = todayPlanService.findTodayPlan(tpid);
			request.setAttribute("todayPlan", todayPlan);
			return "redirect:/toTodayPlan.do";
		}
		//当前登录用户为普通用户
		TodayPlan todayPlan = todayPlanService.findTodayPlan(tpid);
		request.setAttribute("todayPlan", todayPlan);
		return "redirect:/toTodayPlan.do";
	}
	
	/**
	 * 查看所有计划
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAllTodayPlan.do")
	public String findAllTodayPlan(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//判断用户是否已经登录
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//用户未登录就跳回登录页面
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
	 * 删除今日计划
	 * @param tpid
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteTodayPlan.do")
	public String deleteTodayPlan(int tpid,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//判断用户是否已经登录
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//用户未登录就跳回登录页面
				return "userLogin";
			}else{
				todayPlanService.deleteTodayPlan(tpid);
				return "redirect:/toTodayPlan.do";
			}
		}
		todayPlanService.deleteTodayPlan(tpid);
		return "redirect:/toTodayPlan.do";
	}
	
	
//---------------------------------------专职----页面跳转------------------------------------------
	
	/**
	 * 从index.jsp页面跳转到普通用户登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toLogin(HttpServletRequest request){
			return "userLogin";
	}
	
	/**
	 * 从userLogin.jsp页面跳转到登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUserRegist.do")
	public String toUserRegist(HttpServletRequest request){
			return "userRegist";
	}
	
	/**
	 * 从index.jsp页面跳转到企业用户登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toCompanyUserLogin.do")
	public String toCompanyUserLogin(HttpServletRequest request){
			return "companyUserLogin";
	}
	
	/**
	 * 从companyUserLogin.jsp页面跳转到企业用户登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toCompanyUserRegist.do")
	public String toCompanyUserRegist(HttpServletRequest request){
			return "companyUserRegist";
	}
	
	/**
	 * 跳转到项目主页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toMain.do")
	public String toMain(HttpServletRequest request){
			return "main";
	}
	
	
	/**
	 * 跳转到笔记分享页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toNote.do")
	public String toNote(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//判断用户是否已经登录
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//用户未登录就跳回登录页面
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
	 * 跳转到今日计划页面
	 * 在今日计划页面中显示所有计划
	 * @param request
	 * @return
	 */
	@RequestMapping("/toTodayPlan.do")
	public String toTodayPlan(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		//判断用户是否已经登录
		if(user == null){
			CompanyUser companyUser = (CompanyUser)request.getSession().getAttribute("companyUser");
			if(companyUser == null){
				//用户未登录就跳回登录页面
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
	 * 跳转到会议记录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toMeeting.do")
	public String toMeeting(HttpServletRequest request,HttpServletResponse response){
		//判断是否为已经登录的企业人员
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
			//如果当前用户不是已经登录的企业用户，就跳转到项目主页面
			return "main";
		}
	}
	
	/**
	 * 跳转到修改会议记录页面
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
	 * 跳转到修笔记页面
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
	 * 跳转到修改今日计划页面
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
