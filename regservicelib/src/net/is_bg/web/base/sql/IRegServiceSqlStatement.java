package net.is_bg.web.base.sql;

import java.io.Serializable;

public interface IRegServiceSqlStatement<K,V> extends Serializable{
	
    public  String getSql();
    
    //the parameters
    public IRegServiceSqlParams<K, V> getParams();
    
    
    public void execute();
}
