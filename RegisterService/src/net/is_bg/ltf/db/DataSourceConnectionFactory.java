package net.is_bg.ltf.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import net.is_bg.ltf.update.register.common.utils.ContextUtils;



public class DataSourceConnectionFactory implements IConnectionFactory {
	
	public Connection getConnection(String name) {
		try {
			DataSource ds = (DataSource) ContextUtils.getEnvContext().lookup(name);
			Connection conn = ds.getConnection();
			return conn;
		} catch (NamingException e) {
			throw new JDBCException(e);
		} catch (SQLException e) {
			throw new JDBCException(e);
		}
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return getConnection(ContextUtils.DB_CONN_REG);
	}
}
