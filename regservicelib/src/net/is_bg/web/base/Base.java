package net.is_bg.web.base;

import java.io.Serializable;

public class Base implements Serializable{

	private static final long serialVersionUID = -8896459826819078764L;
	
	 /**
     * Encryption, compression strategies flags reserve one byte for each strategy
     */
    private long strategies = 0;

    /**
     * What object processor to be applied at the other side 
     * either when creating the object 
     * or when sending created object!
     */
    private long processorType = 0;
    
    
    /**
     * Object class name
     */
    private String className;
    
    
    /**
     * The className of the wrapped Object
     */
    public String getClassName() {
    	return className;
    }
    
    /**
     * The className of the wrapped Object
     */
    public void setClassName(String className) {
    	this.className = className;
    }
    
    
	public long getStrategies() {
		return strategies;
	}

	public void setStrategies(long strategies) {
		this.strategies = strategies;
	}

    
	public long getProcessorType() {
		return processorType;
	}

	public void setProcessorType(long processorType) {
		this.processorType = processorType;
	}
}
