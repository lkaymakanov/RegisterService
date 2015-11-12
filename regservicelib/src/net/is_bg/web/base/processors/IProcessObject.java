package net.is_bg.web.base.processors;

/**
 * Interface for processing object of given type T!
 * The intent of this method is to fill the Object with the necessary info
 * & return it!
 * @author lubo
 *
 * @param <T>
 */
public interface IProcessObject<T> {
	/**
	 *  * The intent of this method is to fill the Object with the necessary info
	 * & return it!
	 * @param object
	 * @return processed Object
	 */
      T	processObject(T object);
}
