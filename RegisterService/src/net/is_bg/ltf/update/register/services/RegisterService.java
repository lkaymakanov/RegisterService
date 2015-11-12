package net.is_bg.ltf.update.register.services;

import net.is_bg.web.base.ClassInfo;
import net.is_bg.web.base.WrappedObject;






public class RegisterService {

	public String getName(){
		return "";
	}
	
	
	 public WrappedObject getInstance(ClassInfo info, WrappedObject result)   {
		 try {
			 return ServiceProvider.getServiceWrapper().getServices().getInstance(info, result);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
			return null;
		}
	 }
}
