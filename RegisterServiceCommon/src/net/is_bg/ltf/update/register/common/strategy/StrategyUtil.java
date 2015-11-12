package net.is_bg.ltf.update.register.common.strategy;

import net.is_bg.ltf.update.register.common.strategy.CompressionStrategyFactory.COMRP_STRATEGY;
import net.is_bg.ltf.update.register.common.strategy.EncryptionStrategyFactory.ENCR_STRATEGY;
import net.is_bg.web.strategies.complexstrategy.StrategyRepresentation;



public class StrategyUtil {
    //Strategy Util
    /**
     * 
     * @param str
     * @return
     */
    
	public static StrategyRepresentation toRepresentation(Strategy str){
		long compress = (str.getCompression().getval() & 0xff);
		long encr = ((str.getEncryption().getval() << 8) & 0xff00);
		StrategyRepresentation rep = new StrategyRepresentation();
		rep.setAllStrategy(compress | encr);
		return rep;
	}
	
	public static Strategy toStrategy(StrategyRepresentation rep){
		Strategy str = new Strategy();
		long allSt = rep.getAllStrategy();
		str.setCompression(COMRP_STRATEGY.valToStrategy(allSt & 0xff));
		str.setEncryption(ENCR_STRATEGY.valToStrategy((allSt >>> 8) & 0xff));
		return str;
	}
	//end of  Strategy Util
}
