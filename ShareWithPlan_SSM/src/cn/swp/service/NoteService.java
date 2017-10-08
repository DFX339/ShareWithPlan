package cn.swp.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.swp.bean.Note;
import cn.swp.bean.NoteComment;
import cn.swp.dao.ShareWithPlanMapper;

/**
 * �ʼ����ݵ�ҵ����
 * @author Administrator
 * @version1.0 2017/9/25
 */
public class NoteService {
	
	//ע��ҵ������
	@Resource(name="Mapper")
	private ShareWithPlanMapper noteDaoImpl;
	
	//��ӱʼ�
	public int addNote(Note note){
		note.setNsendTime(new Date());
		return noteDaoImpl.addNote(note);
	}
	//��ӱʼ�company
	public int addNoteCompany(Note note){
		return noteDaoImpl.addNoteCompany(note);
	}
	
	//�޸ıʼ�
	public boolean updateNote(int nid,Note note){
		note.setNid(nid);
		return noteDaoImpl.updateNote(note);
	}
	
	
	//����id��ѯ�ʼ�
	public Note findNote(int nid){
		return noteDaoImpl.findNote(nid);
	}
	
	
	//��ѯ��ͨ�û����еıʼ�
	public List<Note> findAllNote(int nuserid){
		return noteDaoImpl.findAllNote(nuserid);
	}
	//��ѯ��ҵ�û����еıʼ�
	public List<Note> findAllNoteCompany(int ncuserid){
		return noteDaoImpl.findAllNoteCompany(ncuserid);
	}
	
	
	//��ѯ���еıʼ�����
	public List<NoteComment> findAllNoteComment(int nid){
		return noteDaoImpl.findAllNoteComment(nid);
	}
	
	
	//���ݱʼ�idɾ���ʼ�
	public boolean deleteNote(int nid){
		return noteDaoImpl.deleteNote(nid);
	}
	
	//��ӱʼ�����
	public int addNoteComment(int nid,NoteComment noteComment){
		noteComment.setNcnid(nid);
		noteComment.setNcTime(new Date());
		return noteDaoImpl.addNoteComment(noteComment);
	}
	
	//���ݱʼ�����idɾ���ʼ�����
	public boolean deleteNoteComment(int ncid){
		return noteDaoImpl.deleteNoteComment(ncid);
	}
}
