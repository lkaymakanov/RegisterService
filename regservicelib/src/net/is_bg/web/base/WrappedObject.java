package net.is_bg.web.base;


/**
 * Contains  Byte array  representation of Object & it's String class name 
 * @author lubo
 *
 */
public class WrappedObject extends Base {

    private static final long serialVersionUID = 1L;

   
    /**
     * The byte Array representation of wrapped  object
     */
    private byte[] objBytes;
    

    

    public byte[] getObjBytes() {
    	return objBytes;
    }

    public void setObjBytes(byte[] objBytes) {
    	this.objBytes = objBytes;
    }
    
}
