package net.is_bg.ltf.db;

import java.io.Serializable;


public abstract class AbstractModel implements Serializable{
	private static final long serialVersionUID = -2245832058738573909L;
	private long id = -1;
	private long index = 0;             //index of model
	private static long indexCnt = 0;   //index counter 
	
	
	public AbstractModel() {
		// default constructor
		index = indexCnt++; 
	}
	
	public AbstractModel(long id) {
		this.id = id;
		index = indexCnt++; 
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * get the index of model*/
	public  long getIndex(){
		return index;
	}
	
	/**
	 * Add 1 to index counter
	 */
	public static long getNextIndex(){
		return indexCnt++; 
	}
	
	/**
	 * set the index of model*/
	public void setIndex(long index) {
		this.index = index;
	}
}
