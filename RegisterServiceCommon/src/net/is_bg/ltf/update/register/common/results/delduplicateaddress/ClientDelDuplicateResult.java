package net.is_bg.ltf.update.register.common.results.delduplicateaddress;

import net.is_bg.ltf.update.register.common.ClientInfo;
import net.is_bg.ltf.update.register.common.results.ClientResult;
import net.is_bg.ltf.update.register.common.results.Message;

public class ClientDelDuplicateResult extends ClientResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8851594431952577993L;

	public ClientDelDuplicateResult(Message msg, ClientInfo info){
		super(msg, info);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		
		if(getClientInfo() != null){
			str += getClientInfo().toString();
		}
		
		if(getMsg() != null){
			str += getMsg();
		}
		
		Object databaseInfo = getFields().get(0);
		
		if(databaseInfo != null) {
			System.out.println("Data Base Info : " + databaseInfo);
		}
		
		return str;
	}
	
	
}
