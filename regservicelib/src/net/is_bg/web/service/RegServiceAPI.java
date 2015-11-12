package net.is_bg.web.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import net.is_bg.web.base.BindClassToObject;
import net.is_bg.web.base.processors.IProcessObject;

/**
 * Contains the low - level routines managing reading / writing row bytes over
 * network
 * 
 * @author lubo
 * 
 */
public class RegServiceAPI {

    /**
     * Converts Object to bytes
     * @param o Object to convert to bytes
     * @return byte array of object
     * @throws IOException 
     */
    public static byte[] serialize(Object o) throws IOException  {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os;
		os = new ObjectOutputStream(out);
		os.writeObject(o);
		return out.toByteArray();
    }

    /**
     * Converts byte array to Object
     * 
     * @param data
     *            Byte array to be converted to Object
     * @param size
     *            The size of array in bytes
     * @return Converted Object - Null if fails
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static Object deserialize(byte[] data, int size) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data, 0, size);
		ObjectInputStream is;
		is = new ObjectInputStream(in);
		return is.readObject();
    }

    /**
     * Converts byte array to Object
     * 
     * @param data
     *            Byte array to be converted to Object
     * @param size
     *            The size of array in bytes
     * @return Converted Object - Null if fails
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException  {
		ByteArrayInputStream in = new ByteArrayInputStream(data, 0, data.length);
		ObjectInputStream is;
		is = new ObjectInputStream(in);
		return is.readObject();
    }
    
    

	
	
	/**
     * Apply Object Processor to fill the object with necessary info & returns it! 
     * @param type
     * @param o
     * @return
     */
	/*
    public static  Object applyObjectProcessor(OBJ_PROCESSOR type, Object o){
    	//apply object processor
		IProcessObject<Object> processor = ProcessObjectFactory.getProcessObject(type); 
		if(processor != null)  o = processor.processObject(o);
		return o;
    }*/
    
    
	/**
     * Apply Object Processor to fill the object with necessary info & returns it! 
     * @param type
     * @param o
     * @return
     */
    public static  Object applyObjectProcessor(IProcessorProvider pr, long type, Object o){
    	
    	IProcessObject<Object> processor = pr.getProcessObject(type); 
		if(processor != null)  o = processor.processObject(o);
    	//apply object processor
    	/*
		IProcessObject<Object> processor = ProcessObjectFactory.getProcessObject(OBJ_PROCESSOR.valToObjProcessor(type)); 
		if(processor != null)  o = processor.processObject(o);*/
		return o;
    }
    
    
    
    
    /**
     * Apply convert Strategies to byte [] array!
     * @param bytes
     * @param stategies
     * @return
     */
    public static byte [] applyConvertStrategies(IStrategyProvider str, byte[] bytes, long type){
    	return str.getStrategy(type).convert(bytes);
    	//apply  convert strategies to byte [] array
    	/*
		StrategyRepresentation strep =  new StrategyRepresentation();
		strep.setAllStrategy(stategies);
		ComplexStrategy str = ComplexStrategyFactory.getComplexStrategy(RegServiceAPI.toStrategy(strep));
		bytes = str.convert(bytes);*/
		//return bytes;
    }
    
    
    /**
     * Apply revert Strategies to byte [] array!
     * @param bytes
     * @param stategies
     * @return
     */
    public static byte[] applyRevertStrategies(IStrategyProvider str, byte[] bytes, long type){
    	
    	return str.getStrategy(type).revert(bytes);
    	/*
    	//apply  convert strategies to byte [] array
		StrategyRepresentation strep =  new StrategyRepresentation();
		strep.setAllStrategy(stategies);
		ComplexStrategy str = ComplexStrategyFactory.getComplexStrategy(RegServiceAPI.toStrategy(strep));
		bytes = str.revert(bytes);*/
		//return bytes;
    }
    
	
	
    public static class InstanceUtil {

	/**
	 * Get an instance of Object of Class<T> type invoking a constructor
	 * with argument types specified by argtypes and argument References
	 * args!
	 * 
	 * @param type
	 *            Class object!
	 * @param argtypes
	 *            Class objects of arguments!
	 * @param args
	 *            References to arguments!
	 * @return Instance of T!
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	public static <T> T getInstance(Class<T> type,
					Class<?>[] argtypes,
					Object[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException  {
	    if (argtypes == null || args == null)
		return type.newInstance();
	    return type.getConstructor(argtypes).newInstance(args);
	}

	/**
	 * Get an instance of Object of Class<T> type invoking a constructor
	 * with arguments in the Serialized BindClassToObject args as byte array
	 * 
	 * @param type
	 *            Class object!
	 * @param args
	 *            Arguments passed to constructor when creating instance!
	 * @return Instance of T!
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws Exception
	 */
	public static <T> T getInstance(Class<T> type, byte[] args) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, ClassNotFoundException  
		 {
	    if (args == null || args.length == 0)
		return getInstance(type, null, null);
	    BindClassToObject o = (BindClassToObject) RegServiceAPI.deserialize(args);
	    return getInstance(type, o);
	}

	/**
	 * Get an instance of Object of Class<T> type invoking a constructor
	 * with arguments in BindClassToObject args
	 * 
	 * @param type
	 *            Class object!
	 * @param args
	 *            Arguments passed to constructor when creating instance!
	 * @return Instance of T!
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	public static <T> T getInstance(Class<T> type, BindClassToObject args) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
		 {
	    if (args == null || args.getParams() == null
		    || args.getParams().size() == 0)
		return getInstance(type, null, null);
	    Class<?>[] types = new Class<?>[args.getParams().size()];
	    Object[] argso = new Object[args.getParams().size()];
	    for (int i = 0; i < args.getParams().size(); i++) {
			types[i] = args.getParams().get(i).getType();
			argso[i] = args.getParams().get(i).getObjref();
	    }
	    return getInstance(type, types, argso);
	}

    }
}
