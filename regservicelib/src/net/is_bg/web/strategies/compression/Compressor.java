package net.is_bg.web.strategies.compression;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.is_bg.web.base.IStreamCopy;
import net.is_bg.web.strategies.compression.ICompressor;


/**
 * Compression Strategy send to me by <b>Cecka mi(Toni Mamata)!</b>
 * @author lubo
 *
 */
public class Compressor implements ICompressor{
	
	
	IStreamCopy strcopy = null;
	
	public Compressor(IStreamCopy copier){
		strcopy = copier;
	}
	
	
    public  byte[] compress(byte[] content){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        System.out.printf("Compression ratio %f\n", (1.0f * content.length/byteArrayOutputStream.size()));
        return byteArrayOutputStream.toByteArray();
    }

    public  byte[] decompress(byte[] contentBytes){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
        	strcopy.copy(new GZIPInputStream(new ByteArrayInputStream(contentBytes)), out);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }

    public static boolean notWorthCompressing(String contentType){
        return contentType.contains("jpeg")
                || contentType.contains("pdf")
                || contentType.contains("zip")
                || contentType.contains("mpeg")
                || contentType.contains("avi");
    }
  
}
