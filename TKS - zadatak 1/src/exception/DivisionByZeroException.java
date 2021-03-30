package exception;

/**
 *  Exception is thrown in case of division by zero.
 * 
 * @author Tatjana Tomic
 * @version 1.0
 * @since 2020-12-24
 */
@SuppressWarnings("serial")
public class DivisionByZeroException extends Exception {

	/**
	 * Constructs a new DivisionByZeroException with the specified detailed message. 
	 */
	public DivisionByZeroException() {
		super("Dijeljenje nulom nije dozvoljeno!");
	}
}
