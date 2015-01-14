package il.ac.mta.exception;

/**
 * exception for negative balance
 * @author daniel
 *
 */
public class NegativeBalanceException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public NegativeBalanceException(int quantity) 
	{
		super("this quantity, " + quantity + ", will make negative balance");
	}
}
