package net.is_bg.web.base.sql;

import java.util.List;

public interface IRegServiceSelectSqlStatement<K, V> extends IRegServiceSqlStatement<K, V>{
	
	//the result from select query
    public List<?> getResult();
    
}
