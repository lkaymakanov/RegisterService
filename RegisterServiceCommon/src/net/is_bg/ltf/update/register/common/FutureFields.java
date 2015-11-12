package net.is_bg.ltf.update.register.common;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * Fields aimed to be added to classes that  are moved across network on the fly!
 * @author lubo
 *
 */
public class FutureFields implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1062360236830475371L;
	//additional fields if necessary -  reserved for  future use!
	private Map<Integer, Object>  fields = new Hashtable<Integer, Object>();

	
	public Map<Integer, Object> getFields() {
		return fields;
	}

	public void setFields(Map<Integer, Object> fields) {
		this.fields = fields;
	}
	
}
