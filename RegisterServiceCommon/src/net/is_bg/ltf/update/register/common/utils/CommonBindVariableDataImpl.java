package net.is_bg.ltf.update.register.common.utils;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CommonBindVariableDataImpl  implements IBindVariableData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6260552133927784523L;

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	private static final SimpleDateFormat TIMESTAMP_FORMAT  = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
	private static final SimpleDateFormat TIME_FORMAT=new SimpleDateFormat("HH:mm:ss");
	
	public CommonBindVariableDataImpl(Map<Integer, IBindVariableInfo> v){
		if(v != null){
			values = v;
		}
	}
	
	public CommonBindVariableDataImpl(){
		
	}
	
	public class BindVariableInfo implements IBindVariableInfo{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Object 	value;
		private int		type;
		private int 	position;
		private boolean outputparam = false;
		
		public BindVariableInfo(Object value,int type,int position){
			this.value=value;
			this.type=type;
			this.position=position;
			this.outputparam = false;
		}
		
		public BindVariableInfo(Object value,int type,int position, boolean output){
			this.value=value;
			this.type=type;
			this.position=position;
			this.outputparam = output;
		}

		@Override
		public Object getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public int getType() {
			// TODO Auto-generated method stub
			return type;
		}

		@Override
		public int getPosition() {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public boolean IsOutputParam() {
			// TODO Auto-generated method stub
			return outputparam;
		}	
	}
	
	private Map<Integer,IBindVariableInfo> 	values=new HashMap<Integer,IBindVariableInfo>();

	
	public void setInt(Integer val,int pos){
		values.put(pos,new BindVariableInfo(val, Types.INTEGER, pos));
	}
	
	public int setInt(Integer val){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(val, Types.INTEGER, position));
		return position;
	}
	
	public void setString(String str,int pos){
			values.put(pos,new BindVariableInfo(str, Types.VARCHAR, pos));
	}
	
	public void setLong(Long val,int pos){
		values.put(pos,new BindVariableInfo(val, Types.INTEGER, pos));
	}
	
	public int setLong(Long val){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(val, Types.BIGINT, position));
		return position;
	}
	
	public void setBigDecimal(BigDecimal val,int pos){
		values.put(pos,new BindVariableInfo(val, Types.DECIMAL, pos));
	}
	
	public int setBigDecimal(BigDecimal val){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(val, Types.DECIMAL, position));
		return position;
	}
	
	public void setDate(Date val,int pos){
		values.put(pos,new BindVariableInfo(val, Types.DATE, pos));
	}
	
	public int setDate(Date val){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(val, Types.DATE, position));
		return position;
	}
	
	public void setTime(Time val,int pos){
		values.put(pos,new BindVariableInfo(val, Types.TIME, pos));
	}
	
	public int setTime(Time val){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(val, Types.TIME, position));
		return position;
	}
	
	public void setTimestamp(Timestamp val,int pos){
		values.put(pos,new BindVariableInfo(val, Types.TIMESTAMP, pos));
	}
	
	public int setTimestamp(Timestamp val){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(val, Types.TIMESTAMP, position));
		return position;
	}
	
	public int setString(String str){
		int position = values.size()+1;
		if (str != null && str.isEmpty()) values.put(position,new BindVariableInfo(null, Types.VARCHAR, position));
		else values.put(position,new BindVariableInfo(str, Types.VARCHAR, position));
		return position;
	}
	
	
	/**
	 * Specify type explicitly with <code> setNull(int type)  </code>
	 */
	@Deprecated
	public void setNull() {
		// TODO Auto-generated method stub
		int position = values.size()+1;
		values.put(position,  new BindVariableInfo(null, Types.NULL, position));
	}
	
	
	public void setNull(int type) {
		// TODO Auto-generated method stub
		int position = values.size()+1;
		values.put(position,  new BindVariableInfo(null, type, position));
	}
	
	public void setFloat(Float f){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(BigDecimal.valueOf(f), Types.DECIMAL, position));
	}
	
	public void setDouble(Double d){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(BigDecimal.valueOf(d), Types.DECIMAL, position));
	}
	
	public void setBoolean(boolean b){
		int position = values.size()+1;
		if(b == true) values.put(position,new BindVariableInfo(1, Types.NUMERIC, position));
		else  values.put(position,new BindVariableInfo(0, Types.NUMERIC, position));
	}
	
	public void setObject(Object o){
		int position = values.size()+1;
		values.put(position,new BindVariableInfo(o, Types.OTHER, position));
	}
	
	public void setObject(Object o,int pos){
		values.put(pos,new BindVariableInfo(o, Types.OTHER, pos));
	}
	
	public void registerOutParameter(int type){
		int position = values.size()+1;
		BindVariableInfo infoObj = new BindVariableInfo(new Object(), type, position, true);
		
		infoObj.type = Types.VARCHAR;
		/*if(type == Types.CLOB){
			if(Visit.getDbType() == DB_TYPE.PGR)  infoObj.type = Types.VARCHAR;
			else if(Visit.getDbType() == DB_TYPE.ORCL)  infoObj.type  = Types.CLOB;
		}*/
		
		values.put(position, infoObj);
	}
	
	public PreparedStatement setParameters(PreparedStatement prStmt) throws SQLException{
		for(IBindVariableInfo var:values.values()){
			if(var.getValue()!=null){
			    prStmt.setObject(var.getPosition(), var.getValue(), var.getType());
			}else{
				prStmt.setNull(var.getPosition(), var.getType());
			}
		}
		return prStmt;
	}
	
	public CallableStatement setParameters(CallableStatement callStmt) throws SQLException{
		for(IBindVariableInfo var:values.values()){
			if(var.getValue()!=null){
				if(var.IsOutputParam() == true) callStmt.registerOutParameter(var.getPosition(), var.getType());
				else callStmt.setObject(var.getPosition(), var.getValue());
			}else{
				callStmt.setNull(var.getPosition(), var.getType());
			}
		}
		return callStmt;
	}
	
	public BufferedReader getClobAsBufferedReader(CallableStatement callStmt, int paramIndex){
		CharArrayReader rdr;
		try{
			
				Object obj = callStmt.getObject(paramIndex);
				String s  = obj!=null? obj.toString() : "";
				char [] charar = new char[s.length()];
				s.getChars(0, s.length(), charar, 0);
				rdr = new CharArrayReader(charar);
				return  new BufferedReader(rdr);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedReader getClobAsBufferedReader(ResultSet rs, int paramIndex){
		CharArrayReader rdr;
		try{
			Object obj = rs.getObject(paramIndex);
			String s  = obj!=null? obj.toString() : "";
			char [] charar = new char[s.length()];
			s.getChars(0, s.length(), charar, 0);
			rdr = new CharArrayReader(charar);
			return  new BufferedReader(rdr);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	//TODO Wrap other setXXX methods in PreparedStatement
	
	public String sqlForLog(String sql){
		if(sql!=null){
			char[] chars=sql.toCharArray();
			StringBuilder sb=new StringBuilder();
			for(int i=0,j=1;i<chars.length;i++){
				//TODO Handle question marks that are escaped
				if('?'==chars[i]){
					IBindVariableInfo pstmp=values.get(j);
					j++;
					if(pstmp!=null){
						sb.append(strForLog(pstmp));
						continue;
					}
				}
				sb.append(chars[i]);
			}
			return sb.toString();
		}
		return null;
	}
	

	public Map<Integer, IBindVariableInfo> getValues() {
		return values;
	}

	

	private String strForLog(IBindVariableInfo aPstmp) {
		if(aPstmp.getValue()!=null){
			switch(aPstmp.getType()){
			//TODO 
			case Types.INTEGER:
			case Types.DOUBLE:
			case Types.BIGINT:
			case Types.DECIMAL:
			case Types.FLOAT:
				return ""+aPstmp.getValue();
			case Types.BOOLEAN:
				return (Boolean)aPstmp.getValue() ?""+1:""+0;
			case Types.DATE:
				return "'"+DATE_FORMAT.format(aPstmp.getValue())+"'";
			case Types.TIMESTAMP:
				return "'"+TIMESTAMP_FORMAT.format(aPstmp.getValue())+"'";
			case Types.TIME:
				return "'"+TIME_FORMAT.format(aPstmp.getValue())+"'";
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.LONGVARCHAR:
			case Types.LONGNVARCHAR:
				return "'"+aPstmp.getValue()+"'";
			default:
				return aPstmp.getValue().getClass().getName();
			}
		}
		return "null";
	}

	
}
