package net.is_bg.ltf.update.register.common.strategy;


public class ComplexStrategyFactory {
	public static ComplexStrategy getComplexStrategy(Strategy str){
		return new ComplexStrategy(str);
	}
}
