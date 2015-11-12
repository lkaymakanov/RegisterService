package net.is_bg.ltf.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UpdateSqlStatement extends SqlStatement {

	@Override
	protected final void executeStatement(PreparedStatement prStmt) throws SQLException {
	    prStmt.executeUpdate();
	}
}
