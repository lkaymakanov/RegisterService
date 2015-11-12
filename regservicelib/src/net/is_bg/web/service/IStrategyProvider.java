package net.is_bg.web.service;

import net.is_bg.web.strategies.complexstrategy.IComplexStrategy;

public interface IStrategyProvider {
	IComplexStrategy getStrategy(long type);
}
