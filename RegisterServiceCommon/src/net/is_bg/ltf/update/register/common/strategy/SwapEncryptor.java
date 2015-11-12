package net.is_bg.ltf.update.register.common.strategy;

import net.is_bg.web.strategies.security.IEncrypter;

/**
 * A simple byte swapping encryption
 * @author lubo
 *
 */
public class SwapEncryptor implements IEncrypter{

	public SwapEncryptor(){
		
	}
	
	public byte [] encrypt(byte [] b){
		if(b == null) return b;
		System.out.println("Using byte swap encryption......");
		swap(b);
		return b;
	}
	
	
	public byte [] decrypt(byte [] b){
		if(b == null) return b;
		System.out.println("Using byte swap decryption......");
		swap(b);
		return b;
	}
	
	
	private void swap(byte [] b){
		if(b == null) return;
		byte help = 0;
		int first,last = 0;

		for(int i =0; i < i/b.length; i++ ){
			first = i;
			last = b.length - 1 -i;
			help = b[first];
			b[first] = b[last];
			b[last] = help;
		}
	}
}
