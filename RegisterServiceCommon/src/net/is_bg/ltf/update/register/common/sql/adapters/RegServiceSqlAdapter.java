package net.is_bg.ltf.update.register.common.sql.adapters;

import java.util.HashMap;
import java.util.Map;

import net.is_bg.ltf.update.register.common.ClientInfo;
import net.is_bg.ltf.update.register.common.FutureFields;
import net.is_bg.ltf.update.register.common.utils.CommonBindVariableDataImpl;
import net.is_bg.ltf.update.register.common.utils.IBindVariableData;
import net.is_bg.ltf.update.register.common.utils.IBindVariableInfo;
import net.is_bg.web.base.sql.IRegServiceSqlParams;
import net.is_bg.web.base.sql.IRegServiceSqlStatement;

public abstract class RegServiceSqlAdapter extends FutureFields implements IRegServiceSqlStatement<Integer, IBindVariableInfo> {
	private static final long serialVersionUID = 1870996275002630930L;
	private String sql = "";
	private ClientInfo clientInfo;
	
	private IRegServiceSqlParams<Integer , IBindVariableInfo> params = new IRegServiceSqlParams<Integer, IBindVariableInfo>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -435083728322590766L;
		private Map<Integer, IBindVariableInfo> paramMap = new HashMap<Integer, IBindVariableInfo>();
		
		@Override
		public Map<Integer, IBindVariableInfo> getParams() {
			// TODO Auto-generated method stub
			return paramMap;
		}
	};
	
	protected transient IBindVariableData bindVarData = new CommonBindVariableDataImpl(params.getParams());
	
	public IRegServiceSqlParams<Integer, IBindVariableInfo> getParams() {
		return params;
	};
		
	public Map<Integer, IBindVariableInfo> getParamsMap(){
		return getParams().getParams();
	}
	
	@Override
	public String getSql() {
		// TODO Auto-generated method stub
		return sql;
	}
	
	public void setSql(String sql){
		this.sql = sql;
	}
	
	//stub execute
	public void execute() {
			// TODO Auto-generated method stub
		    	
    }

	public IBindVariableData getBindVarData() {
		return bindVarData;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
}
