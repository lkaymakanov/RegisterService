package net.is_bg.ltf.update.register.common.strategy;

import net.is_bg.ltf.update.register.common.strategy.CompressionStrategyFactory.COMRP_STRATEGY;
import net.is_bg.ltf.update.register.common.strategy.EncryptionStrategyFactory.ENCR_STRATEGY;
import net.is_bg.web.strategies.complexstrategy.IComplexStrategy;




public class ComplexStrategy implements IComplexStrategy {
	
	ENCR_STRATEGY enc = ENCR_STRATEGY.NO;
	COMRP_STRATEGY cmp = COMRP_STRATEGY.NO;
	
	public ComplexStrategy(Strategy str){
		this.enc = str.getEncryption();
		this.cmp = str.getCompression();
	}
	

	@Override
	public byte[] convert(byte[] bytes) {
		// TODO Auto-generated method stub
		return  EncryptionStrategyFactory.getEncryptionStrategy(enc).encrypt(
				CompressionStrategyFactory.getCompressionStrategy(cmp).compress(bytes));
	}

	@Override
	public byte[] revert(byte[] bytes) {
		// TODO Auto-generated method stub
		return  EncryptionStrategyFactory.getEncryptionStrategy(enc).decrypt(
				CompressionStrategyFactory.getCompressionStrategy(cmp).decompress(bytes));
	}
	
	
}
