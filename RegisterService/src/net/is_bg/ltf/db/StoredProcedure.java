package net.is_bg.ltf.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class StoredProcedure extends DBStatementAdapter {
	private static final Log LOG = LogFactory.getLog(StoredProcedure.class);
	protected static final String SUCCESS = "OK";
	
	public void execute(Connection connection) {
		CallableStatement callableStatement = null;
		try {
			sql = getProcedureName();
			callableStatement = connection.prepareCall(sql);
			setParameters(callableStatement);
			
			//get the start time of execution
			details.startTimer();
			
			//execute procedure
			callableStatement.executeUpdate();
			
			//get the end time of execution
			details.stopTimer();
			
			retrieveResult(callableStatement);
		} catch (SQLException e) {
			throw new JDBCException(e);
		} finally {
			try {
				if (callableStatement != null)
					callableStatement.close();
				//get user details
				collectUserDetails();
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}

	protected abstract String getProcedureName();

	protected abstract void setParameters(CallableStatement callableStatement) throws SQLException;

	protected void retrieveResult(CallableStatement callableStatement) throws SQLException {
		// no implementation
	}
	
}
