package net.is_bg.ltf.db;

import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;

import net.is_bg.ltf.update.register.common.utils.CommonBindVariableDataImpl;
import net.is_bg.ltf.update.register.common.utils.IBindVariableData;


public abstract  class  DBStatementAdapter  implements DBStatement{

	protected IBindVariableData bindVarData =  new CommonBindVariableDataImpl();
	DBStatementDetails details = new DBStatementDetails();
	public static  boolean test = false;
	/**
	 * The name of procedure or the query string
	 */
	protected String sql = "";
	
	//ADD COMMON STUFF TO STORED PROCEDURE CLASSES & SQL STATEMENT CLASSES
	
	/**
	 * Displays resulset metadata
	 * @param metadata
	 */
	protected void displayMetaData(ResultSetMetaData metadata){
		try
		{
			if(metadata == null ) return;
			ResultSetMetaData meta =  metadata;
			int cols = meta.getColumnCount();
			int cur = 0;
			System.out.println("Printing column MetaData ==================================================");
			for(int i = 0; i < cols; i++){
				cur = i+1;
				System.out.println();
				System.out.println("COLUMN NUMBER = " + cur);
				System.out.println("column class name = " + meta.getColumnClassName(cur));
				System.out.println("column display size =  " +  meta.getColumnDisplaySize(cur));
				System.out.println("column label = " +  meta.getColumnLabel(cur));
				System.out.println("column name = " +  meta.getColumnName(cur));
				System.out.println("column type = " +  meta.getColumnType(cur));
				System.out.println("column type name  = " +  meta.getColumnTypeName(cur));
				System.out.println("presicion =  " +  meta.getPrecision(cur));
				System.out.println("scale =  " +  meta.getScale(cur));
				System.out.println("schema name = " +  meta.getSchemaName(cur));
				System.out.println("table name = " +  meta.getTableName(cur));
			}
			System.out.println("End printing column MetaData ==================================================");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Gather user info
	 * @throws SQLException 
	 */
	public void collectUserDetails()  {
		//set the user attributes
		details.setSlqForlog(sqlForLog());
	}
	
	public DBStatementDetails getDetails() {
		// TODO Auto-generated method stub
		return details;
	}
	
	
	public boolean IntToBool(Integer in) {
		return (in != 0) ? true : false;
	}

	public int BoolToInt(boolean boo) {
		return (boo) ? 1 : 0;
	}

	
	
	public String RoundFloat(Float f) {
		// За закръгляне на float до два символа след десетичната запетая
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			return df.format(f).replace(",", ".");
		} catch (Exception e) {
			//LOG.warn(e.getMessage());
			return "";
		}
	}
	
	public String RoundDouble(Double f) {
		// За закръгляне на float до два символа след десетичната запетая
		DecimalFormat df = new DecimalFormat("#.##");
		try {
			return df.format(f).replace(",", ".");
		} catch (Exception e) {
			//LOG.warn(e.getMessage());
			return "";
		}
	}
	
	public String RoundBigDecimal(BigDecimal bd) {
		// За закръгляне на float до два символа след десетичната запетая
		if (bd == null)
			return "";
		DecimalFormat df = new DecimalFormat("#.##");
		double doublePayment = bd.doubleValue();
		String s = df.format(doublePayment).replace(",", ".");
		return s;
	}

	public String sqlForLog() {
		return bindVarData.sqlForLog(sql);
	}

	public static boolean isTest() {
		return test;
	}

	public static void setTest(boolean test) {
		DBStatementAdapter.test = test;
	}	
	
	
	
	
}
