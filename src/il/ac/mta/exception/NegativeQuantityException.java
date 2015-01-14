package il.ac.mta.exception;

public class NegativeQuantityException extends Exception 
{
	public NegativeQuantityException() 
	{
		super("you can't enget negative quantity of stocks");
	}
}
