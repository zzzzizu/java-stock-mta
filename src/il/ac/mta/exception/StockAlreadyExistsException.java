package il.ac.mta.exception;

/**
 * exception for multiply stocks
 * @author daniel
 *
 */
public class StockAlreadyExistsException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol) 
	{
		super("Stock " + symbol + " already exists!");
	}

}
