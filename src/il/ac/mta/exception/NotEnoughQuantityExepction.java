package il.ac.mta.exception;

public class NotEnoughQuantityExepction extends Exception
{
	private static final long serialVersionUID = 1L;

	public NotEnoughQuantityExepction(String symbol, int quantity) 
	{
		super("you don't have " + quantity + " " + symbol + " in your portfolio");
	}
}
