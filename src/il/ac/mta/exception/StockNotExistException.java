package il.ac.mta.exception;

/**
 * exception for tring to make an action on not exist stock
 * @author daniel
 *
 */
public class StockNotExistException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public StockNotExistException(String symbol) 
	{
		super("the stock " + symbol + " isn't exist");
	}
}
