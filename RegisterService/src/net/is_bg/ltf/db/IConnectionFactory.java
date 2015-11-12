package net.is_bg.ltf.db;

import java.sql.Connection;

public interface IConnectionFactory {

	public abstract Connection getConnection();
}