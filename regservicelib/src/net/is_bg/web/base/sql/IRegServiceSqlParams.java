package net.is_bg.web.base.sql;

import java.io.Serializable;
import java.util.Map;

public interface IRegServiceSqlParams<K , V> extends Serializable{

	 Map<K, V> getParams();
	
}
