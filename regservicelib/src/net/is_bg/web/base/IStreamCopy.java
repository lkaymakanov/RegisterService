package net.is_bg.web.base;

import java.io.InputStream;
import java.io.OutputStream;

import net.is_bg.web.strategies.compression.Compressor;

/**
 * This Stream copy interface is provided only to ensure that nested GZIP Compressor doesn't depend on Apache IO copy Utils!
 * @see 
 * Compressor
 * 
 *@author lubo
 *
 */
public interface IStreamCopy {

	public void copy(InputStream in, OutputStream out);
}
