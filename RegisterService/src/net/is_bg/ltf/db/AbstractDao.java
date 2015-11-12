package net.is_bg.ltf.db;

import java.math.BigDecimal;
import java.util.List;


public abstract class AbstractDao {
	protected static final String SUCCESS = "OK";

	private DBExecutor dbExecutor;

	public AbstractDao(IConnectionFactory connectionFactory) {
		dbExecutor = new DBExecutor(connectionFactory);
	}

	public final void execute(DBStatement statement) {
	    dbExecutor.execute(statement);
	}

	public final void execute(DBStatement[] statement) {
		dbExecutor.execute(statement);
	}
	
	public final void execute(List<DBStatement> dblist){
		dbExecutor.execute(dblist.toArray(new DBStatement[0]));
	}

	
	public BigDecimal RoundBigDecimal(BigDecimal bd) {
		return bd.setScale(2);
	}

	
}
