package cn.swp.exception;
/**
 * 企业用户异常
 * @author Administrator
 *
 */
public class RegistException extends RuntimeException{
	
	public RegistException(String msg){
		super(msg);
	}
}
