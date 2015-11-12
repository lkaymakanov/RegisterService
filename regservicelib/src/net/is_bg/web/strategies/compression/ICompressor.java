package net.is_bg.web.strategies.compression;

/**
 * ICompressor - Compression Interface
 * @author lubo
 *
 */
public interface ICompressor {
	public byte [] compress(byte [] b);
	public byte [] decompress(byte [] b);
}
