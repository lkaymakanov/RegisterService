package net.is_bg.ltf.update.register.common.strategy;

import net.is_bg.web.strategies.security.IEncrypter;
import net.is_bg.web.strategies.security.NullEncrypter;

/**
 * Provide Encryption implementation routines if necessary
 * or no actions - return NullEncryptor!
 * @author lubo
 *
 */

public class EncryptionStrategyFactory {
	
	private static NullEncrypter nillEncrypter = new NullEncrypter();
	private static SwapEncryptor swapEncryptor = new SwapEncryptor();
	
	public enum ENCR_STRATEGY{
		NO(0),
		SWAP(1);
		
		long val;
		ENCR_STRATEGY(long val){
			this.val =val;
		};
		
		public long getval(){
			return val;
		}
		
		public static ENCR_STRATEGY  valToStrategy(long val){
			ENCR_STRATEGY [] st = ENCR_STRATEGY.values();
			for(int i = 0; i < st.length; i++){
				if(st[i].getval() == val) return st[i];
			}
			
			return ENCR_STRATEGY.NO;
		}
	};
	
	
	public static IEncrypter getEncryptionStrategy(ENCR_STRATEGY strategy){
		
		switch (strategy) {
		case NO:
			return nillEncrypter;
		case SWAP:
			return swapEncryptor;
		default:
			break;
		}
		return nillEncrypter;
	}
}
