package net.is_bg.web.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that binds together a class of given type and instance of that class!
 * 
 * @author lubo
 * 
 */
public class BindClassToObject implements Serializable {

    private static final long serialVersionUID = -1511431119973903504L;
    List<BindClassToObjectInfo> params = new ArrayList<BindClassToObject.BindClassToObjectInfo>();

    public class BindClassToObjectInfo implements Serializable {
			private static final long serialVersionUID = -8899471433703615296L;
			Class<?> type;
			Object objref;
		
			public Class<?> getType() {
			    return type;
			}
		
			public Object getObjref() {
			    return objref;
			}
    }

    /**
     * Binds Any class to any object instance!
     * @param type
     * @param inst
     */
    public void addParam(Class<? extends Object> type, Object inst) {
		BindClassToObjectInfo info = new BindClassToObjectInfo();
		info.type = type;
		info.objref = inst;
		params.add(info);
    }
    
    
    /**
     * Binds A class T  to any object instance of T!
     * @param type
     * @param inst
     */
    public <T> void addParamTypeSafe(Class<T> type, T inst) {
  		BindClassToObjectInfo info = new BindClassToObjectInfo();
  		info.type = type;
  		info.objref = inst;
  		params.add(info);
    }
    

    /**
     * A list with binded class & Instance!
     * 
     * @return
     */
    public List<BindClassToObjectInfo> getParams() {
    	return params;
    }

    public void clearParams() {
    	params.clear();
    }
}
