package net.is_bg.web.base.processors;

import java.util.HashMap;
import java.util.Map;



/**
 * <pre>
 * The whole idea behind this class is to encapsulate 
 * classes & methods that process Object of that class!
 * The process means filling their attributes accordingly before sending
 * objects to clients or any custom actions provided! The Binding methods are handled &
 * implemented by the WEB SERVICE!
 * 
 * This is A Singleton!
 * </pre>
 * @author lubo
 *
 */
public class ProcessorContainer implements IProcessObject<Object>{
	
	
	private Map<Class<?>,  Object> map = new HashMap<Class<?>, Object>();
	private static ProcessorContainer container = new ProcessorContainer();
	
	private ProcessorContainer(){

	}

	/**
	 * Add An object processor to the container map!
	 * @param type
	 * @param processor
	 */
	public <T>  void addProcessor(Class<T> type, IProcessObject<T> processor){
		map.put(type, processor);
	}
	
	/**
	 * Gets A processor for the given type the container map!
	 * @param type
	 * @param processor
	 */
	@SuppressWarnings("unchecked")
	public IProcessObject<Object> getProcessor(Class<?> type){
		return  (IProcessObject<Object>)map.get(type);
	}
	
	/**
	 * get the SingleTon Instance!
	 * @return
	 */
	public static ProcessorContainer getProcessContainer(){
		return container;
	}

	
	@Override
	public Object processObject(Object object) {
		// TODO Auto-generated method stub
		IProcessObject<Object> proc = getProcessor(object.getClass());
		if(proc == null) return object;
		return getProcessor(object.getClass()).processObject(object);
	}
	
}
