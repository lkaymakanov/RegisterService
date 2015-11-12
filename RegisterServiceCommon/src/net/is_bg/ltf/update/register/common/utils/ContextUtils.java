package net.is_bg.ltf.update.register.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;


import net.is_bg.ltf.update.register.common.DataBaseInfo;


/**
 * So u can suck  my dick if u don like my shit cos I was high when I wrote this 
 * @author lubo
 *
 */
public class ContextUtils {

	public static final String DB_CONN_RESOURCE = "jdbc"; 
	public static final String DB_CONN_REG =   "jdbc/regser";
	private static final String LOCALMAGIC = "localhost";
	private static Context ctx = null;
	
	
	public static synchronized Context getContext() throws NamingException{
		if(ctx == null) ctx = new InitialContext();
		return ctx;
	}
	
	public static synchronized Context getEnvContext() throws NamingException{
		Context envContext = (Context) getContext().lookup("java:/comp/env");
		return envContext;
	}

	
	//context names
	public static NamingEnumeration<NameClassPair> getNamesInContext() throws NamingException {
		return getNamesInContext(DB_CONN_RESOURCE);
	}
	
	
	//context bindings
	public static NamingEnumeration<Binding> getBindingsInContext() throws NamingException{
		return getContext().listBindings("java:/comp/env");
	}
	
	
	public static Map<Integer, DataBaseInfo> getDbConnections() throws NamingException, SQLException, UnknownHostException{
		return getDbConnections(DB_CONN_RESOURCE);
	}
	
		
	
	//get connections from context
	public static Map<Integer, DataBaseInfo> getDbConnections(String dbConresource) throws NamingException, SQLException, UnknownHostException{
		
		Map<Integer, DataBaseInfo> dbMap = new HashMap<Integer, DataBaseInfo>();
		try{
				NamingEnumeration<NameClassPair> connList = getNamesInContext(dbConresource);
				
				int i = 0;
				while(connList.hasMoreElements()){
					try{
						NameClassPair pair = connList.nextElement();
						String tmpStr = dbConresource + "/" + pair.getName();
						
						DataSource dataSource = (DataSource) getEnvContext().lookup(tmpStr);
						Connection conn = dataSource.getConnection();
						DatabaseMetaData ds = conn.getMetaData();
						DataBaseInfo info =  DataBaseInfo.ParseDBUrl(ds.getURL());
						info.setUserName(ds.getUserName().toUpperCase());
						info.setDbName(ds.getDatabaseProductName());
						info.setName(pair.getName());
						info.setDefDbCon(tmpStr);
						info.setTns(info.getUserName() + "@" + info.getiP() + ":" + info.getPort() + ":" + info.getSid());
						
						if(info.getiP() != null && info.getiP().toLowerCase().contains(LOCALMAGIC)){
							info.setInetAddress(InetAddress.getLocalHost());
						}else{
							info.setInetAddress(InetAddress.getByName(info.getiP()));
						}
					
						System.out.println( "DataBase URL: " + ds.getURL());
						System.out.println( "DataBase Productname: " + ds.getDatabaseProductName());
						System.out.println(info);
						
						dbMap.put(i, info);
					}catch (Exception e) {
						// TODO: handle exception
						i--;   //free the slot on exception
					}
					i++;
				}
		}
		catch (Exception e) {
				// TODO: handle exception
		}
		return dbMap;
	}
	
	
	//context names
	public static NamingEnumeration<NameClassPair> getNamesInContext(String dbConresource) throws NamingException{
			Context envContext = (Context) getContext().lookup("java:/comp/env");
			return  envContext.list(dbConresource);
			
			/*
			NamingEnumeration<Binding> namingEnum = initContext.listBindings("java:/comp/env");
			
			while (namingEnum.hasMore()){
				Binding tmpBind = namingEnum.nextElement();
				if (tmpBind.getName().toString().equals(SUPPORT_PHONE)) supportPhone = tmpBind.getObject().toString();
				if (tmpBind.getName().toString().equals(SUPPORT_MOBILE)) supportMobile = tmpBind.getObject().toString();
				if (tmpBind.getName().toString().equals(SUPPORT_URL)) supportURL = tmpBind.getObject().toString();
			}
			
			supportPhone = validatePhone(supportPhone);
			supportMobile = validatePhone(supportMobile);
			*/
	}
	
	
	
}
