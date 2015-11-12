package net.is_bg.web.strategies.security;

public class NullEncrypter implements IEncrypter {
	
	
	public NullEncrypter(){
	}
	
	public byte [] encrypt(byte [] b){
		return b;
	}
	
	
	public byte [] decrypt(byte [] b){
		return b;
	}
	
	
		
}
