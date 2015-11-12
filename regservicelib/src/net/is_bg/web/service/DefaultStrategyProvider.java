package net.is_bg.web.service;

import net.is_bg.web.strategies.complexstrategy.IComplexStrategy;
import net.is_bg.web.strategies.complexstrategy.NullComplexStrategy;

public class DefaultStrategyProvider implements IStrategyProvider{

	@Override
	public IComplexStrategy getStrategy(long type) {
		// TODO Auto-generated method stub
		return new NullComplexStrategy();
	}

	
}
