package net.is_bg.ltf.db;


public class ElapsedTimeUtil {

	/**
	 * This is used to calculate how long something takes to  be executed... 
	 * Gets the current value of the most precise available system
     * timer, in nanoseconds AND STORES IT IN <code> ElapsedTimeAttributes.startTime member</code>
	 * @param attrib - output param  attrib  to be filled MUST NOT BE NULL
	 * 
	 * <p>
	 * EXAMPLE
	 * <p>ElapsedTimeAttributes attrib = new ElapsedTimeAttributes(); 
	 * <code>ElapsedTimeUtil.getStartTimeNano(attrib);
	 * <p>//do something time consuming
	 * <p> ElapsedTimeUtil.getEndTimeNano(attrib);  //get the elapsed time
	 *  </code>
	 */
	public static void getStartTimeNano(ElapsedTimeAttributes attrib){
		//fill attributes start time
		attrib.startTime = System.nanoTime();
	}
	
	/**
	 * Fills the <code>ElapsedTimeAttributes.endTime & ElapsedTimeAttributes.duration members of output parameter attrib</code>
	 * @param attrib - output param  attrib  to be filled MUST NOT BE NULL
	 */
	public static void getEndTimeNano(ElapsedTimeAttributes attrib){
		long endTime =  System.nanoTime() -  attrib.startTime;
		attrib.endTime = endTime;
		attrib.duration = attrib.endTime - attrib.startTime;
	}
	
	/**
	 * This is used to calculate how long something takes to  be executed... 
	 * If duiration is less than 1 ms duration is 0
	 * @param attrib - output param  attrib  to be filled MUST NOT BE NULL
	 * 
	 * <p>
	 * EXAMPLE
	 * <p>ElapsedTimeAttributes attrib = new ElapsedTimeAttributes(); 
	 * <code>ElapsedTimeUtil.getStartTimeMillis(attrib);
	 * <p>//do something time consuming
	 * <p> ElapsedTimeUtil.getEndTimeMillis(attrib);  //get the elapsed time
	 *  </code>
	 */
	public static void getStartTimeMillis(/*out param */ElapsedTimeAttributes attrib){
		attrib.startTime = System.currentTimeMillis();
	}
	
	/**
	 * Fills the <code>ElapsedTimeAttributes.endTime & ElapsedTimeAttributes.duration members of output parameter attrib</code>
	 * @param attrib - output param  attrib  to be filled MUST NOT BE NULL
	 */
	public static void getEndTimeMillis(/*out param */ElapsedTimeAttributes attrib){
		long duration = System.currentTimeMillis() - attrib.startTime;
		long endTime = duration + attrib.startTime;
		
		//fill attributes
		if(attrib != null){
			attrib.duration = duration;
			attrib.endTime = endTime;
		}
	}
	
	
	
}
