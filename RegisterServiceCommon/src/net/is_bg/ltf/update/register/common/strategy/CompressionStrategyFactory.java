package net.is_bg.ltf.update.register.common.strategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import net.is_bg.web.base.IStreamCopy;
import net.is_bg.web.strategies.compression.Compressor;
import net.is_bg.web.strategies.compression.ICompressor;
import net.is_bg.web.strategies.compression.NullCompressor;



/**
 * Provide Compression implementation routines if necessary
 * or no actions - return NullCompressor!
 * @author lubo
 *
 *@see
 *EncryptionStrategyFactory
 */
public class CompressionStrategyFactory {
	
	private static ICompressor nullCompressor = new NullCompressor();
	private static ICompressor gZipCompressor = new Compressor(new IStreamCopy() {
		@Override
		public void copy(InputStream in, OutputStream out) {
			try {
				IOUtils.copy(in, out);
				out.flush();
	        	out.close();
	        	in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
	
	public enum COMRP_STRATEGY{
		NO(0),
		GZIP(1);
		
		long val;
		COMRP_STRATEGY(long val){
			this.val =val;
		};
		
		public long getval(){
			return val;
		}
		
		public static COMRP_STRATEGY  valToStrategy(long val){
			COMRP_STRATEGY [] st = COMRP_STRATEGY.values();
			for(int i = 0; i < st.length; i++){
				if(st[i].getval() == val) return st[i];
			}
			return COMRP_STRATEGY.NO;
		}
	};
	
	public static ICompressor getCompressionStrategy(COMRP_STRATEGY strategy){
		
		switch (strategy) {
			case  NO:
				return nullCompressor;
			case GZIP:
				return gZipCompressor;
			default:
				return new NullCompressor();
		}
		
	}
}
