package net.is_bg.ltf.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class SqlStatement extends DBStatementAdapter {
	
	public static final DateFormat ddMMyyyyFormat = new SimpleDateFormat("dd.MM.yyyy");
	protected static final Log LOG = LogFactory.getLog(SelectSqlStatement.class);
	protected int resultSetType = ResultSet.TYPE_FORWARD_ONLY;
	protected int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY; 
	
	
	public SqlStatement() {
		super();
	}

	public void execute(Connection connection) {
		PreparedStatement prStmt = null;
		try {
			sql = getSqlString();
			prStmt = connection.prepareStatement(sql, resultSetType, resultSetConcurrency);//,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			setParameters(prStmt);
			
			//get the start time of execution
			details.startTimer();
			
			executeStatement(prStmt);  //execute
			
			//get the end time of execution
			details.stopTimer();

		} catch (SQLException e) {
			throw new JDBCException(e);
		} finally {
			try {
				if (prStmt != null) {
					prStmt.close();
				}
				//get user details
				collectUserDetails();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
	


	protected abstract String getSqlString();

	protected abstract void executeStatement(PreparedStatement prStmt) throws SQLException;

	protected void setParameters(PreparedStatement prStmt) throws SQLException {
		// no implementation
	}

	public void setResultSetType(int resultSetType) {
		this.resultSetType = resultSetType;
	}

	public void setResultSetConcurrency(int resultSetConcurrency) {
		this.resultSetConcurrency = resultSetConcurrency;
	}

}
