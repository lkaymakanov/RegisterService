package net.is_bg.ltf.update.register.common.providers;

import net.is_bg.ltf.update.register.common.strategy.ComplexStrategyFactory;
import net.is_bg.ltf.update.register.common.strategy.StrategyUtil;
import net.is_bg.web.service.IStrategyProvider;
import net.is_bg.web.strategies.complexstrategy.IComplexStrategy;
import net.is_bg.web.strategies.complexstrategy.StrategyRepresentation;



public class MyStrategyProvider implements IStrategyProvider {

	@Override
	public IComplexStrategy getStrategy(long type) {
		// TODO Auto-generated method stub
		StrategyRepresentation rep = new StrategyRepresentation();
		rep.setAllStrategy(type);
		return ComplexStrategyFactory.getComplexStrategy(StrategyUtil.toStrategy(rep));
	}

}
