package net.is_bg.web.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import net.is_bg.web.base.ClassInfo;
import net.is_bg.web.base.WrappedObject;

public class RegServices {
	
	IProcessorProvider  procProvider = new DefaultProcessorProvider();
	IStrategyProvider   strPovider = new DefaultStrategyProvider();
	
	
	public RegServices(IProcessorProvider procProvider, IStrategyProvider  strPovider ){
		this.procProvider = procProvider;
		this.strPovider = strPovider;
	}
	
	/**
     * Creates an Object instance based on info parameter and returns it through
     * WrappedObject!
     * 
     * @param info
     *            The className & constructor arguments if any!
     * @param result
     *            Result object passed by the calling function to report Result
     *            of operation!
     * @return Wrapped instance of the class requested in ClassInfo info
     *         parameter!
     * @throws Exception
     */
    public WrappedObject getInstance(ClassInfo info, WrappedObject result)  throws Exception {
    	
    
    	Object infoInstance = createInstance(info);
    	Object resultInstance = createInstance(result);
    	long resultStr =0;
    	long infoStr = 0;
    	long resultProc = 0;
    	long infoProc = 0;
    	
    	//apply processors
    	if(info != null && infoInstance!= null){
    		infoInstance = RegServiceAPI.applyObjectProcessor(procProvider, info.getProcessorType(), infoInstance);
    		infoStr = info.getStrategies();
    		infoProc = info.getProcessorType();
    	}
    	
    	if(result != null && resultInstance !=null){
    		resultInstance = RegServiceAPI.applyObjectProcessor(procProvider, result.getProcessorType(), resultInstance);
    		resultStr = result.getStrategies();
    		resultProc = result.getProcessorType();
    	}
    	
    	//create the wrapped object from instance - 
    	if(info != null && info.getUseResultTocreateInstance() != 0)  return  createWrappedObject(resultInstance, resultProc,  resultStr);
    	else return  createWrappedObject(infoInstance, infoProc, infoStr);    	
    }
    

    
    /**
     * 
     * @param info
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IOException
     */
    private Object createInstance(ClassInfo info) throws ClassNotFoundException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException{
    	if(info == null || info.getClassName() == null  || info.getClassName().equals(""))  return null;
		
		//get object class
		Class<?> c = Class.forName(info.getClassName());
		
		//get arguments
		byte [] args = info.getConstuctArgs();
		
		//create instance with the given class & arguments 
		Object o = RegServiceAPI.InstanceUtil.getInstance(c, args);
		
		return o;
    }
    
    /**
     * 
     * @param wrapobj
     * @return
     * @throws ClassNotFoundException 
     * @throws IOException 
     */
    private Object createInstance(WrappedObject wrapobj) throws IOException, ClassNotFoundException {
    	if(wrapobj == null || wrapobj.getObjBytes()== null  || wrapobj.getObjBytes().length == 0)  return null;
		
    	//bytes of Object
    	byte [] b = wrapobj.getObjBytes();

    	//apply revert strategies 
    	b = RegServiceAPI.applyRevertStrategies(strPovider, b, wrapobj.getStrategies());
    
    	//deserialize object from bytes
    	Object o = unwrapObject(b);
    	
    	//Apply Object Processor
    	//if(apllyprocesor) o = RegServiceAPI.applyObjectProcessor(procProvider, wrapobj.getProcessorType(), o);
		
		return o;
    }
    
    
    
    /**
     * Create Object, Process  it, Serialize it & return it!
     * @param info  The class of which we want an instance
     * @return Wrapped representation of given Object!
     * @throws ClassNotFoundException 
     * @throws IOException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     * @throws Exception
     */
    private WrappedObject createWrappedObject(Object o, long processorType, long strtype) throws ClassNotFoundException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException   {
    	if( o == null)  return null;
    
    	WrappedObject returnWrappedObject = new WrappedObject();
    	returnWrappedObject.setStrategies(strtype);
    	returnWrappedObject.setProcessorType(processorType);
    	
		//serialize
		byte [] sero = wrapObject(o);
		
		//apply convert  strategies
		sero = RegServiceAPI.applyConvertStrategies(strPovider, sero, strtype);
		
		//set Object bytes
		returnWrappedObject.setObjBytes(sero);
		
		//return it
		return returnWrappedObject;
    }
    
    
    private byte [] wrapObject(Object o) throws IOException{
    	if(o == null) return null;
		return  RegServiceAPI.serialize(o);
    }
    
    
    private Object unwrapObject(byte []  bytes) throws IOException, ClassNotFoundException{
    	if(bytes == null) return null;
		return  RegServiceAPI.deserialize(bytes);
    }
     
    
}
