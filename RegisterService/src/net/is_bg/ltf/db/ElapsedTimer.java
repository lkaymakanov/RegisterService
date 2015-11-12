package net.is_bg.ltf.db;


/**
 * <pre>
 * Wrap ElapsedTimeAttributes & 
 * ElapsedTime Helper functions in separate class 
 * to restrict access to public members in ElapsedTimeAttributes
 * and provide a higher level
 * API for measuring Activity time!
 * </pre>
 * @author lubo
 *
 */
public class ElapsedTimer {
	
	protected ElapsedTimeAttributes attrib = new ElapsedTimeAttributes();
	
	/**
	 * Start timer 
	 */
	public void start(){
		ElapsedTimeUtil.getStartTimeMillis(attrib);
	}
	
	/**
	 * Stop  timer
	 */
	public void stop(){
		ElapsedTimeUtil.getEndTimeMillis(attrib);
	}
	
	
	public long getStartTime(){
		return attrib.startTime;
	}
	
	public long getEndTime(){
		return attrib.endTime;
	}
	
	public long getDuration(){
		return attrib.duration;
	}

}