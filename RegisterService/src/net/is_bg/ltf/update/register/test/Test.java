package net.is_bg.ltf.update.register.test;


import net.is_bg.ltf.db.common.UpdateSqlStatement;
import net.is_bg.ltf.update.register.common.sql.registertourist.v1.RegisterTouristInsertV1;
import net.is_bg.ltf.update.register.common.strategy.CompressionStrategyFactory.COMRP_STRATEGY;
import net.is_bg.ltf.update.register.common.strategy.Strategy;

import java.util.Date;

import net.is_bg.ltf.update.register.common.sql.registertourist.v1.RegisterTouristInsertV1;
import net.is_bg.ltf.update.register.common.strategy.Strategy;
import net.is_bg.ltf.update.register.common.strategy.StrategyUtil;
import net.is_bg.ltf.update.register.common.strategy.CompressionStrategyFactory.COMRP_STRATEGY;
import net.is_bg.ltf.update.register.services.RegisterService;
import net.is_bg.web.base.BindClassToObject;
import net.is_bg.web.base.ClassInfo;
import net.is_bg.web.base.WrappedObject;
import net.is_bg.web.service.DefaultStrategyProvider;
import net.is_bg.web.service.RegServiceAPI;
import net.is_bg.ltf.update.register.services.RegisterService;
import net.is_bg.ltf.update.register.services.SqlUtils;




/**
 * A class to perform lib tests in!!!!
 * @author lubo
 *
 */
public class Test {

	
	public static void main(String[] args) throws Exception {
		
		RegisterService ser = 	new RegisterService();
		
		Strategy str = new Strategy();
		str.setCompression(COMRP_STRATEGY.GZIP);
		
		RegisterTouristInsertV1 in =  getInstance(ser, RegisterTouristInsertV1.class, null, new Long(100), new Date());
		
		
		UpdateSqlStatement upd  = SqlUtils.toCleintSqlUpdateStatement(in);
		
		System.out.println(in);
		
		System.out.println(upd);
	
	}
	
	

	
	public static  <T> T  getInstance(RegisterService ser, Class<T> type,  Strategy str,  Object... args) throws Exception{
		//create object instance
		ClassInfo info = new ClassInfo();
		info.setClassName(type.getCanonicalName());
		
		//set strategies
		if(str != null){
			info.setStrategies(StrategyUtil.toRepresentation(str).getAllStrategy());
		}
		
		//create constructor args
		BindClassToObject bco = new BindClassToObject();

		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				bco.addParam(args[i].getClass(), args[i]);
			}
		}
		info.setConstuctArgs(RegServiceAPI.serialize(bco));
		
		//get instance
		WrappedObject obj = ser.getInstance(info, null);
		
		//get object bytes
		byte [] b = obj.getObjBytes();
		
		//apply strategies if any
		b = RegServiceAPI.applyRevertStrategies(new DefaultStrategyProvider(), b, obj.getStrategies());
		
		//deserialize & return instance
		return type.cast(RegServiceAPI.deserialize(b));
	}
}
