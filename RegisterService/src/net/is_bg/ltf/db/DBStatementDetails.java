package net.is_bg.ltf.db;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




import org.apache.commons.logging.Log;




/**
 * Details such as user, transactionNo, time of execution
 * @author lubo
 *
 */
public class DBStatementDetails {
	
	private static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
	/**The user stuff
	*/
	
	private int transactionIsolationLevel = DBExecutor.DEFAULT_TRANSACTION_ISOLATION_LEVEL;
	private boolean loggable = true;
	/**begin, end time of execution and duration
	 * 
	 */
	private ElapsedTimer timer = new ElapsedTimer();

	private String  slqForlog = "";
	
	/**The user stuff
	*/
	
	
	
	/**begin, end time of execution and duration
	 * 
	 */
	public String getSlqForlog() {
		return slqForlog;
	}
	public void setSlqForlog(String slqForlog) {
		this.slqForlog = slqForlog;
	}
	
	public long getStartTime(){
		return timer.getStartTime();
	}
	
	
	public long getEndTime(){
		return  timer.getEndTime();
	}
	
	public long getDuration(){
		return timer.getDuration();
	}
	
	public void startTimer(){
		timer.start();
	}
	
	public void stopTimer(){
		timer.stop();
	}
	
	
	public int getTransactionIsolationLevel() {
		return transactionIsolationLevel;
	}
	public void setTransactionIsolationLevel(int transactionIsolationLevel) {
		this.transactionIsolationLevel = transactionIsolationLevel;
	}
	
	public enum ISOLATION_LEVEL{
			TRANSACTION_NON(0),

		    /**
		     * A constant indicating that
		     * dirty reads, non-repeatable reads and phantom reads can occur.
		     * This level allows a row changed by one transaction to be read
		     * by another transaction before any changes in that row have been
		     * committed (a "dirty read").  If any of the changes are rolled back, 
		     * the second transaction will have retrieved an invalid row.
		     */
		    TRANSACTION_READ_UNCOMMITTED (1),

		    /**
		     * A constant indicating that
		     * dirty reads are prevented; non-repeatable reads and phantom
		     * reads can occur.  This level only prohibits a transaction
		     * from reading a row with uncommitted changes in it.
		     */
		    TRANSACTION_READ_COMMITTED (2),

		    /**
		     * A constant indicating that
		     * dirty reads and non-repeatable reads are prevented; phantom
		     * reads can occur.  This level prohibits a transaction from
		     * reading a row with uncommitted changes in it, and it also
		     * prohibits the situation where one transaction reads a row,
		     * a second transaction alters the row, and the first transaction
		     * rereads the row, getting different values the second time
		     * (a "non-repeatable read").
		     */
		    TRANSACTION_REPEATABLE_READ(4),

		    /**
		     * A constant indicating that
		     * dirty reads, non-repeatable reads and phantom reads are prevented.
		     * This level includes the prohibitions in
		     * <code>TRANSACTION_REPEATABLE_READ</code> and further prohibits the 
		     * situation where one transaction reads all rows that satisfy
		     * a <code>WHERE</code> condition, a second transaction inserts a row that
		     * satisfies that <code>WHERE</code> condition, and the first transaction
		     * rereads for the same condition, retrieving the additional
		     * "phantom" row in the second read.
		     */
		    TRANSACTION_SERIALIZABLE(8);

		
		int val;
		ISOLATION_LEVEL(int val){
			this.val =val;
		};
		
		public long getval(){
			return val;
		}
		
		public static ISOLATION_LEVEL  valToIsolationLevel(int val){
			ISOLATION_LEVEL [] st = ISOLATION_LEVEL.values();
			for(int i = 0; i < st.length; i++){
				if(st[i].getval() == val) return st[i];
			}
			
			return ISOLATION_LEVEL.TRANSACTION_NON;
		}
	};
	
	
	public boolean isLoggable() {
		return loggable;
	}
	public void setLoggable(boolean loggable) {
		this.loggable = loggable;
	}

	public String  printDetails(Log LOG){
		if(!isLoggable()) return "";

		String forLog = 
				  "\nTransaction isolation Level:" + ISOLATION_LEVEL.valToIsolationLevel(transactionIsolationLevel) +
				  "\nStart Time:  " + formatter.format(new Date(getStartTime())) +
				  "\nEnd Time:    " + formatter.format(new Date(getEndTime())) +
				  "\nDuration:    " + getDuration() + " ms" +
				  "\nSQL:         " + getSlqForlog() +
				  "\n";
		LOG.debug(forLog);
		return forLog;
	}
	
}
