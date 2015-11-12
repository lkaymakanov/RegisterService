package net.is_bg.ltf.update.register.services;

import java.sql.SQLException;

import javax.naming.NamingException;

import net.is_bg.ltf.update.register.common.providers.MyProcessorProvider;
import net.is_bg.ltf.update.register.common.providers.MyStrategyProvider;
import net.is_bg.web.service.RegServices;


public class ServiceProvider {
	
	private  static RegisterServiceWrappper servicesWrapper = null;
	
	private ServiceProvider(){}
	

	/**Get the one and single instance of RegisterServiceWrappper
	 * 
	 * @return
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public static synchronized RegisterServiceWrappper getServiceWrapper() 
	{
		try{
			if(servicesWrapper == null){
				//Map<Integer, DataBaseInfo> m  = ContextUtils.getDbConnections();
				servicesWrapper = new RegisterServiceWrappper(new RegServices(new MyProcessorProvider(), new MyStrategyProvider()));
				InitProcessorContainer.initProcessContainer();
				System.out.println("=================================RegisterServiceWrappper INITIALIZED SUCCESSFULLY====================================================");
			}
			return servicesWrapper;
		}catch (Exception e) {
			// TODO: handle exception
			servicesWrapper = null;
			System.out.println("=================================FAILED TO INITIALIZE RegisterServiceWrappper========================================================");
			throw new RuntimeException(e);
		}
	}
}
