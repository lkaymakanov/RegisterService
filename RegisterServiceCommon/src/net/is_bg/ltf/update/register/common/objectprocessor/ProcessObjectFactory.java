package net.is_bg.ltf.update.register.common.objectprocessor;

import net.is_bg.ltf.update.register.common.results.ClientResult;
import net.is_bg.ltf.update.register.common.sql.adapters.RegServiceSqlAdapter;
import net.is_bg.web.base.processors.IProcessObject;
import net.is_bg.web.base.processors.ProcessorContainer;

/**
 * Gets an Object processor!
 * @author lubo
 *
 */
public class ProcessObjectFactory {
	

	
	public enum OBJ_PROCESSOR{
		BIND_CLASS_TO_OBJECT(0),
		SOME_DIFFERERNT_PROCESSOR(1);
		
		long val;
		OBJ_PROCESSOR(long val){
			this.val =val;
		};
		
		public long getval(){
			return val;
		}
		
		public static OBJ_PROCESSOR  valToObjProcessor(long val){
			OBJ_PROCESSOR [] st = OBJ_PROCESSOR.values();
			for(int i = 0; i < st.length; i++){
				if(st[i].getval() == val) return st[i];
			}
			return OBJ_PROCESSOR.BIND_CLASS_TO_OBJECT;
		}
	};

	
	
	public static IProcessObject<Object> getProcessObject(OBJ_PROCESSOR proctype){
		switch (proctype) {
		case BIND_CLASS_TO_OBJECT:
			return ProcessorContainer.getProcessContainer();
		case SOME_DIFFERERNT_PROCESSOR:
			return someotherProcessor;
		default:
			return ProcessorContainer.getProcessContainer();
		}
	}
	
	
	
	private static IProcessObject<Object> someotherProcessor = new IProcessObject<Object>() {
		@Override
		public Object processObject(Object arg0) {
			// TODO Auto-generated method stub
			if(arg0 == null) return null;
			else if(arg0 instanceof RegServiceSqlAdapter){
				((RegServiceSqlAdapter)arg0).setSql("This is some other object processor......");
			}else if (arg0 instanceof ClientResult){
				System.out.println(arg0);
			}
			return arg0;
		}
	};
}
