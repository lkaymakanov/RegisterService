package net.is_bg.ltf.update.register.common;

import java.net.InetAddress;


public class DataBaseInfo extends FutureFields{
	

	private static final long serialVersionUID = 255822004483706943L;
	private String url = "";
	private String defDbCon = "";
	private String iP = "";
	private String port = "";
	private String sid = "";
	private String userName = "";
	private String dbName = "";
	private String tns = "";
	private String name = "";
	private InetAddress inetAddress = null;
	private int hashCode = 0;

	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return hashCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null) return false;
		if(!(obj instanceof DataBaseInfo)) return false;
		
		DataBaseInfo  in = (DataBaseInfo)obj;		
		if((in.getSid() != null && getSid() != null )
				&& ( in.getPort() != null || getPort() != null) &&
				in.getPort().equals(getPort()) && (in.getSid().equals(getSid()))) return true;
		
		return false;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return   "url:" + url + "\n" + 
				 "Inet address:" + inetAddress +"\n"+
				 "defDbCon:" + defDbCon + "\n"+
		         "ip:"+ iP + "\n"+
				 "port:"+ port  + "\n"+
		         "sid:"+ sid + "\n"+
				 "userName:"+ userName + "\n"+
		         "dbName:"+ dbName  + "\n"+
				 "tns:" + tns + "\n" +
		         "name:" + name + "\n" +
		         "hashCode:" + hashCode +"\n";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getDefDbCon() {
		return defDbCon;
	}

	public void setDefDbCon(String defDbCon) {
		this.defDbCon = defDbCon;
	}

	public String getiP() {
		return iP;
	}

	public void setiP(String iP) {
		this.iP = iP;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSid() {
		return sid;
	}

    public void setSid(String sid) {
		this.sid = sid;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTns() {
		return tns;
	}


	public void setTns(String tns) {
		this.tns = tns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public InetAddress getInetAddress() {
		return inetAddress;
	}
	


	private int calcHashFromBytes(byte [] b){
		if(b == null) return 0;
		int h = 0;
		int shift = 24;
		
		for(int i = 0; i < b.length && i < 4; i++){
			int sh = b[i];
			sh = sh & 0xff;   //last byte
			h = h | ((sh) << shift);
			shift-=8;
		}
		return h;
	}

	public void setInetAddress(InetAddress inetAddres) {
		hashCode = 0;
		if(inetAddres != null) {
			hashCode = calcHashFromBytes(inetAddres.getAddress());
		}
		this.inetAddress = inetAddres;
	}

	public static DataBaseInfo ParseDBUrl(String dbUrl){
		DataBaseInfo attrr = new DataBaseInfo();
			
		if(dbUrl == null)  return attrr;
		
		//replace all slashes with @
		dbUrl = dbUrl.replaceAll("/", "@");
		if(dbUrl == null)  return attrr;
		
		//replace all semicolon with @
		dbUrl = dbUrl.replaceAll(":", "@");
		if(dbUrl == null)  return attrr;
		
		//split by double @
		String [] arr = dbUrl.split("@@");
		if(arr == null || arr.length < 2 || arr[1] == null) return attrr;
		
		
		//.DebugLog(ar[1]);
		
		//now split by @
		String [] at = arr[1].split("@");
		
		if(at == null || at.length < 3)  return attrr;
		
		//now fill attributes take the last 3 strings
		if(at[0] != null )attrr.iP = at[at.length-3];
		if(at[1] != null )attrr.port = at[at.length-2];
		if(at[2] != null )attrr.sid = at[at.length-1];
		
		return attrr;
	}


}
