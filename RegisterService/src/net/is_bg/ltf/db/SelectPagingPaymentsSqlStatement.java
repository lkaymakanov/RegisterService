package net.is_bg.ltf.db;

import java.util.ArrayList;
import java.util.List;



public class SelectPagingPaymentsSqlStatement  extends SelectSqlStatement {
	protected List<AbstractModel> result = new ArrayList<AbstractModel>();
	protected Integer comboIndex;
	protected long srcID;
	
	private static final String BEFORE_SQL = "select * from (select rtbl.*,  ROW_NUMBER() OVER (ORDER BY (select 0 from dual))  rnum from ( ";
	private static final String ROW_BEGIN = " ) rtbl ) as foo  where RNUM between ";
	private static final String ROW_END = " and ";
	private int rowBegin;
	private int rowEnd;
	
	protected String rtnSqlString(String sql) {
		if (rowEnd <= 0) return sql;
		if (rowBegin > rowEnd) return sql;
		return BEFORE_SQL + sql + ROW_BEGIN + String.valueOf(rowBegin) + ROW_END + String.valueOf(rowEnd);
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