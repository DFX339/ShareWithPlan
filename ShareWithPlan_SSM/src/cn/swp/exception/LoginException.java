package cn.swp.exception;
/**
 * ��ͨ�û��쳣
 * @author Administrator
 *
 */
public class LoginException extends RuntimeException{
	
	public LoginException(String msg){
		super(msg);
	}
}
