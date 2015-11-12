package net.is_bg.web.base;

/**
 * Provides static factory methods to get current id & next id!
 * @author lubo
 *
 */
public class IDGeneratorFactory {
	private static  IDGenerator sqlIdGenerator = new IDGenerator();
	
	
	public static long  getNextIdSql(){
		return  sqlIdGenerator.getNextId();
	}
	
	public static long getCurrentIdSql(){
		return sqlIdGenerator.getCurrentId();
	}
	
}
