package net.is_bg.web.strategies.compression;

public class NullCompressor implements ICompressor {
	
	
	public NullCompressor(){
	}

	@Override
	public byte[] compress(byte[] b) {
		// TODO Auto-generated method stub
		return b;
	}

	@Override
	public byte[] decompress(byte[] b) {
		// TODO Auto-generated method stub
		return b;
	}
		
}
