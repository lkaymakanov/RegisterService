package net.is_bg.ltf.update.register.common.strategy;

import java.io.Serializable;

import net.is_bg.ltf.update.register.common.strategy.CompressionStrategyFactory.COMRP_STRATEGY;
import net.is_bg.ltf.update.register.common.strategy.EncryptionStrategyFactory.ENCR_STRATEGY;




public class Strategy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 336431117334002458L;
	private ENCR_STRATEGY encryption = ENCR_STRATEGY.NO;
	private COMRP_STRATEGY compression = COMRP_STRATEGY.NO;

	
	public Strategy(){
		
	}
	
	public ENCR_STRATEGY getEncryption() {
		return encryption;
	}

	public void setEncryption(ENCR_STRATEGY encryption) {
		this.encryption = encryption;
	}

	public COMRP_STRATEGY getCompression() {
		return compression;
	}

	public void setCompression(COMRP_STRATEGY compression) {
		this.compression = compression;
	}
	
	
}
