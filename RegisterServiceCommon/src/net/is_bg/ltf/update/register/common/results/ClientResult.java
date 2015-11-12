package net.is_bg.ltf.update.register.common.results;

import net.is_bg.ltf.update.register.common.ClientInfo;
import net.is_bg.ltf.update.register.common.FutureFields;

public class ClientResult extends FutureFields{
	private static final long serialVersionUID = 7806790956682746035L;
	private ClientInfo clientInfo;
	private Message msg;
	
	
	public ClientResult(Message msg, ClientInfo info) {
		// TODO Auto-generated constructor stub
		this.clientInfo = info;
		this.msg= msg;
	}
	

	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str="Client info: ";
		if(clientInfo != null){
			str+=clientInfo.toString();
		}
		if(msg != null){
			str+=msg.toString();
		}
		return str;
	}
}
