package net.is_bg.web.strategies.complexstrategy;

public interface IComplexStrategy {
	byte [] convert(byte [] bytes);
	byte [] revert(byte[] bytes);
}
