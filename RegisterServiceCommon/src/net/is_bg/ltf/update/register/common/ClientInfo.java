package net.is_bg.ltf.update.register.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientInfo extends FutureFields{
	
	private static final long serialVersionUID = 6050982976655454260L;
	private List<DataBaseInfo> clientDatabases  =  new ArrayList<DataBaseInfo>();
	private InetAddress clientAddress;
	
	public ClientInfo(List<DataBaseInfo> clientDatabases) throws UnknownHostException{
		this.clientDatabases = clientDatabases;
		initInetAddress();
	}
	
	private void initInetAddress() throws UnknownHostException{
		this.clientAddress = InetAddress.getLocalHost();
	}

	public ClientInfo(InetAddress clientAddress, List<DataBaseInfo> clientDatabases){
		this.clientAddress = clientAddress;
		this.clientDatabases = clientDatabases;
	}
	
	public List<DataBaseInfo> getClientDatabases() {
		return clientDatabases;
	}

	public InetAddress getClientAddress() {
		return clientAddress;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "Client Address:  ";
		
		//inet address
		if(clientAddress != null) {
			str += clientAddress.toString();
			str+="\n";
		}
		
		//databases info
		for(int i= 0; i < clientDatabases.size(); i++){
			DataBaseInfo info = clientDatabases.get(i);
			str+="\n";
			if(info != null) str+=  info.toString() + "\n";
		}
		
		return str;
	}
}
