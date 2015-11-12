package net.is_bg.ltf.update.register.services;


import java.sql.SQLException;
import javax.naming.NamingException;
import net.is_bg.ltf.db.RegServiceDao;
import net.is_bg.web.service.RegServices;

/**
 * Service Wrapper containing clients info....
 * @author lubo
 *
 */
public class RegisterServiceWrappper {
	private  RegServiceDao dao = null;
	private RegServices  services = null;
	
	RegisterServiceWrappper(RegServices services) throws NamingException, SQLException{
		this.services = services;
		this.dao = new RegServiceDao();
	}

	public RegServices getServices() {
		return services;
	}

	public RegServiceDao getDao() {
		return dao;
	}
}
