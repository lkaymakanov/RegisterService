package net.is_bg.ltf.update.register.services;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.is_bg.ltf.db.common.BindVariableData;
import net.is_bg.ltf.db.common.SelectSqlStatement;
import net.is_bg.ltf.db.common.UpdateSqlStatement;
import net.is_bg.ltf.update.register.common.utils.CommonBindVariableDataImpl;
import net.is_bg.ltf.update.register.common.utils.CommonBindVariableDataImpl.BindVariableInfo;
import net.is_bg.ltf.update.register.common.utils.IBindVariableData;
import net.is_bg.ltf.update.register.common.utils.IBindVariableInfo;
import net.is_bg.web.base.sql.IRegServiceSelectSqlStatement;
import net.is_bg.web.base.sql.IRegServiceSqlParams;
import net.is_bg.web.base.sql.IRegServiceSqlStatement;



public class SqlUtils {

	/**
	 * Gets out only the hashTable out of BindVariableData
	 * @param inParams
	 * @return
	 */
	public static IRegServiceSqlParams<Integer, IBindVariableInfo>  toRegServiceParams(IBindVariableData inParams){
		
		class MyIRegServiceSqlParams implements IRegServiceSqlParams<Integer, IBindVariableInfo>{
			private static final long serialVersionUID = -5870696166855227703L;
			Map<Integer, IBindVariableInfo> p = new  HashMap<Integer, IBindVariableInfo>();
			
			@Override
			public Map<Integer, IBindVariableInfo> getParams() {
				// TODO Auto-generated method stub
				return p;
			}
		}
		
		MyIRegServiceSqlParams outparams = new MyIRegServiceSqlParams();
		if(inParams == null) return outparams;
		outparams.p = inParams.getValues();
		return outparams;
	}
	
	
	/**
	 * Creates BindVariableData from  parameters HashMap!
	 * @param inParams
	 * @return
	 */
	public static BindVariableData  toClientParams(IRegServiceSqlParams<Integer, IBindVariableInfo> inParams){
		BindVariableData data = new  BindVariableData();
		if(inParams == null || inParams.getParams() == null) return data;
		Map<Integer, IBindVariableInfo> p =   inParams.getParams();
		for(Entry<Integer, IBindVariableInfo> e: p.entrySet()){
			Integer i = e.getKey();
			IBindVariableInfo val = e.getValue();
			net.is_bg.ltf.db.common.BindVariableInfo info =new net.is_bg.ltf.db.common.BindVariableInfo(val.getValue(), val.getType(), val.getPosition());
			data.getValues().put(i, info);
		}
		return data;
	}
	
	
	
	/**
	 * Convert Web Service Select Statement to  Client Select Statement!
	 * @param webSqlSt
	 * @return
	 */
	
	public static SelectSqlStatement toClientSelectSqlStatement(IRegServiceSelectSqlStatement<Integer, IBindVariableInfo> webSqlSt){
		
		if(webSqlSt == null) return null;
		
		class MySelect extends SelectSqlStatement {
			IRegServiceSelectSqlStatement<Integer, IBindVariableInfo> webSqlStat;
			
			public MySelect(IRegServiceSelectSqlStatement<Integer, IBindVariableInfo> webSql ){
				webSqlStat = webSql;
				bindVarData = toClientParams(webSqlStat.getParams());
			}
			
			@Override
			protected String getSqlString() {
				// TODO Auto-generated method stub
				return webSqlStat.getSql();
			}
			
			@Override
			protected void setParameters(PreparedStatement prStmt)
					throws SQLException {
				// TODO Auto-generated method stub
				bindVarData.setParameters(prStmt);
			}
		}
		
		return  new MySelect(webSqlSt);
	
	}
	
	
	/**
	 * Converts Web Service Update Statement to Client Update Statement!
	 * @param webSqlSt
	 * @return
	 */
	
	public static UpdateSqlStatement toCleintSqlUpdateStatement(IRegServiceSqlStatement<Integer, IBindVariableInfo> webSqlSt){
		if(webSqlSt == null) return null;
		
		class MyUpdate extends UpdateSqlStatement {
			IRegServiceSqlStatement<Integer, IBindVariableInfo> webSqlStat;
			
			public MyUpdate(IRegServiceSqlStatement<Integer, IBindVariableInfo> webSql ){
				webSqlStat = webSql;
				bindVarData = toClientParams(webSqlStat.getParams());
			}
			
			@Override
			protected String getSqlString() {
				// TODO Auto-generated method stub
				return webSqlStat.getSql();
			}
			
			@Override
			protected void setParameters(PreparedStatement prStmt)
					throws SQLException {
				// TODO Auto-generated method stub
				bindVarData.setParameters(prStmt);
			}
		}
				
		return  new MyUpdate(webSqlSt);
	}
	
	

	public static java.sql.Date toSQLDate(java.util.Date date) {
		java.sql.Date sqlDate = null;
		if (date != null)
			sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
}
