package cn.swp.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.swp.bean.Note;
import cn.swp.bean.NoteComment;
import cn.swp.dao.ShareWithPlanMapper;

/**
 * 笔记内容的业务类
 * @author Administrator
 * @version1.0 2017/9/25
 */
public class NoteService {
	
	//注入业务层对象
	@Resource(name="Mapper")
	private ShareWithPlanMapper noteDaoImpl;
	
	//添加笔记
	public int addNote(Note note){
		note.setNsendTime(new Date());
		return noteDaoImpl.addNote(note);
	}
	//添加笔记company
	public int addNoteCompany(Note note){
		return noteDaoImpl.addNoteCompany(note);
	}
	
	//修改笔记
	public boolean updateNote(int nid,Note note){
		note.setNid(nid);
		return noteDaoImpl.updateNote(note);
	}
	
	
	//根据id查询笔记
	public Note findNote(int nid){
		return noteDaoImpl.findNote(nid);
	}
	
	
	//查询普通用户所有的笔记
	public List<Note> findAllNote(int nuserid){
		return noteDaoImpl.findAllNote(nuserid);
	}
	//查询企业用户所有的笔记
	public List<Note> findAllNoteCompany(int ncuserid){
		return noteDaoImpl.findAllNoteCompany(ncuserid);
	}
	
	
	//查询所有的笔记评论
	public List<NoteComment> findAllNoteComment(int nid){
		return noteDaoImpl.findAllNoteComment(nid);
	}
	
	
	//根据笔记id删除笔记
	public boolean deleteNote(int nid){
		return noteDaoImpl.deleteNote(nid);
	}
	
	//添加笔记评论
	public int addNoteComment(int nid,NoteComment noteComment){
		noteComment.setNcnid(nid);
		noteComment.setNcTime(new Date());
		return noteDaoImpl.addNoteComment(noteComment);
	}
	
	//根据笔记评论id删除笔记评论
	public boolean deleteNoteComment(int ncid){
		return noteDaoImpl.deleteNoteComment(ncid);
	}
}
