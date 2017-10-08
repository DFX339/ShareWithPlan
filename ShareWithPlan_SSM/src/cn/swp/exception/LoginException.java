package cn.swp.exception;
/**
 * 普通用户异常
 * @author Administrator
 *
 */
public class LoginException extends RuntimeException{
	
	public LoginException(String msg){
		super(msg);
	}
}
