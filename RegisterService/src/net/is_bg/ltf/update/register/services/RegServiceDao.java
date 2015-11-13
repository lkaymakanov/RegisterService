package net.is_bg.ltf.update.register.services;


import java.sql.SQLException;
import java.util.Date;

import net.is_bg.ltf.db.common.AbstractMainDao;
import net.is_bg.ltf.db.common.interfaces.IConnectionFactory;
import net.is_bg.ltf.update.register.tourist.SelectTouristRegInsertScript;

public class RegServiceDao extends AbstractMainDao {

	public RegServiceDao(IConnectionFactory connectionFactory) {
		super(connectionFactory);
		// TODO Auto-generated constructor stub
	}
	
	
	public RegServiceDao () throws  SQLException{
		this(new DataSourceConnectionFactory());
	}

	public SelectTouristRegInsertScript getTouristInsertScript(long pageNum, String municiaplityList, Date maxInsertDate){
		SelectTouristRegInsertScript sel = new SelectTouristRegInsertScript( pageNum,  municiaplityList,  maxInsertDate);
		execute(sel);
		return sel;
	}
}