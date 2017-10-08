package cn.swp.exception;
/**
 * 会议记录异常类
 * @author Administrator
 *
 */
public class MeetingException extends RuntimeException {
	
	public MeetingException(String msg){
		super(msg);
	}
}
