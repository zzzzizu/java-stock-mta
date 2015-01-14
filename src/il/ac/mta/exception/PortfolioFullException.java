package il.ac.mta.exception;

/**
 * exception for full portfolio
 * @author daniel
 *
 */
public class PortfolioFullException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public PortfolioFullException() 
	{
		super("portfolio is full");
	}
}
