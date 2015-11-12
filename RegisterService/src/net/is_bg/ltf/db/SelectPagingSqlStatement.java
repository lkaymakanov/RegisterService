package net.is_bg.ltf.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SelectPagingSqlStatement extends SelectSqlStatement {
	protected List<AbstractModel> result = new ArrayList<AbstractModel>();
	protected Integer comboIndex;
	protected long srcID;
	
//	private static final String BEFORE_SQL = "select * from (select a.*, ROW_NUMBER() OVER (ORDER BY (select 0 from dual)) rnum from ( ";
//	private static final String AFTER_SQL = " ) a ) b where rnum  >= ? and rnum <= ?  ";
	private int rowBegin =-1;
	private int rowEnd =-1;
	
	protected String rtnSqlString(String sql) {
		if (rowEnd <= 0) return sql;
		if (rowBegin > rowEnd) return sql;
		String resultStr = "select * from (select a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( " + sql + " ) a ) b where rnum  >= "+rowBegin+" and rnum <= "+rowEnd+" ";
        return resultStr;
    }

    protected void setParameters(PreparedStatement prStmt) throws SQLException {
        bindVarData.setParameters(prStmt);
    }

    
    public void setRows(int rowBegin, int rowEnd) {
		this.rowBegin = rowBegin;
		this.rowEnd = rowEnd;
	}

	@Override
    protected String getSqlString() {
	    return null;
    }
	
	public List<AbstractModel> getResult() {
		return result;
	}
	
}