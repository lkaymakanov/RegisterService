package net.is_bg.web.base;


/**
 * <pre>
 * Represents a class name & arguments passed to the constructor
 * when instanciating an Object!
 * If constuctArgs is null or an empty array no - argument constructor is invoked!!!
 * </pre>
 * 
 * @author lubo
 */
public class ClassInfo extends Base {
    private static final long serialVersionUID = 6245017168009245548L;

    /**
     * Serialized Arguments passed to constructor when instanciating
     */
    private byte[] constuctArgs;
    private long   useResultTocreateInstance;

    public byte[] getConstuctArgs() {
    	return constuctArgs;
    }

    public void setConstuctArgs(byte[] constuctArgs) {
    	this.constuctArgs = constuctArgs;
    }

	public long getUseResultTocreateInstance() {
		return useResultTocreateInstance;
	}

	public void setUseResultTocreateInstance(long useResultTocreateInstance) {
		this.useResultTocreateInstance = useResultTocreateInstance;
	}
    
}
