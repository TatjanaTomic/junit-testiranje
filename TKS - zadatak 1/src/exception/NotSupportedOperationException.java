package exception;

/**
 * Exception is thrown in case of calling not implemented operation.
 * 
 * @author Tatjana Tomic
 * @version 1.0
 * @since 2020-12-24
 */
@SuppressWarnings("serial")
public class NotSupportedOperationException extends Exception {
	
	/**
	 * Constructs a new NotSupportedOperationException with the specified detailed message. 
	 */
	public NotSupportedOperationException() {
		super("Nepostojeca operacija!");
	}
}
