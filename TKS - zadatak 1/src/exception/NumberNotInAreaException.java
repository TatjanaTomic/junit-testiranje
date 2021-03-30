package exception;

/**
 * Exception is thrown if operation is called with value out of the assigned range.
 * 
 * @author Tatjana Tomic
 * @version 1.0
 * @since 2020-12-24
 */
@SuppressWarnings("serial")
public class NumberNotInAreaException extends Exception {
	
	/**
	 * Constructs a new NumberNotInAreaException with the passed detailed message.
	 * @param message Passed detailed message. 
	 */
	public NumberNotInAreaException(String message) {
		super(message);
	}
}
