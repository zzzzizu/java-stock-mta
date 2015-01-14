package il.ac.mta.exception;

public class NegativeQuantityException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public NegativeQuantityException() 
	{
		super("you can't enter negative quantity of stocks");
	}
}
