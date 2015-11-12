package net.is_bg.web.base;


/**
 * Simple generator of ids
 * Statement ids, command ids, etc.
 * @author lubo
 *
 */
public class IDGenerator {
	private  long idCnt = 0;

	public  synchronized long getNextId(){
		return idCnt++;
	}
	
	public  synchronized long getCurrentId(){
		return idCnt;
	}
}
