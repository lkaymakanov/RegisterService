package net.is_bg.web.strategies.security;

/**
 * IEncryption - Encrytor Interface
 * @author lubo
 *
 */
public interface IEncrypter {
	public byte [] encrypt(byte [] b);
	public byte [] decrypt(byte [] b);
}
