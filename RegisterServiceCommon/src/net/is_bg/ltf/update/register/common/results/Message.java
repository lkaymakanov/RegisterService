package net.is_bg.ltf.update.register.common.results;

import java.io.Serializable;

import net.is_bg.ltf.update.register.common.FutureFields;

public class Message extends FutureFields{
	
	public enum MESSAGE_STAT implements Serializable{
		OK(1),
		ERROR(0);
		long val;
		MESSAGE_STAT(long val){
			this.val = val;
		}
	};

	private static final long serialVersionUID = 5400518878431158486L;
	private Exception exception;
	private String msg = "";
	MESSAGE_STAT status = MESSAGE_STAT.OK;
	
	
	
	private Message(String msg, MESSAGE_STAT stat){
		this.msg = msg;
		this.status = stat;
	}
	
	private Message(String msg, Exception e, MESSAGE_STAT stat){
		this.msg = msg;
		this.status = stat;
		this.exception = e;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "Message: " + msg + " Status: " + status;
		if(exception != null) str+= " Exception : " + exception + "\n";
		return str; 
	}
	
	
	public static Message getErrorMessage(String msg){
		return new Message(msg, MESSAGE_STAT.ERROR);
	}
	
	public static Message getErrorMessage(String msg, Exception e){
		return new Message(msg, e, MESSAGE_STAT.ERROR);
	}
	
	public static Message getOkMessage(String msg){
		return new Message(msg, null, MESSAGE_STAT.OK);
	}
	
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public MESSAGE_STAT getStatus() {
		return status;
	}
	public void setStatus(MESSAGE_STAT status) {
		this.status = status;
	}	

}
