package net.is_bg.ltf.db;

import org.apache.commons.logging.Log;

public class DBStatementDetailsCompound extends DBStatementDetails{
	
	private DBStatementDetails [] details;
	
	public DBStatementDetailsCompound(DBStatementDetails [] details){
		this.details = details;
	}
	
	@Override
	public String printDetails(Log LOG) {
		// TODO Auto-generated method stub
		for(int i = 0; i < details.length; i++){
			details[i].printDetails(LOG);
		}
		return "";
	}
	

}
