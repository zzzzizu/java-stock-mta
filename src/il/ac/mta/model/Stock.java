package il.ac.mta.model;

import java.util.Date;

/**
 * defining the stock parameters
 * @author daniel
 *
 */
public class Stock 
{
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	
	public Stock()
	{
		setSymbol(getSymbol());
		setAsk(getAsk());
		setBid(getBid());
		setDate(getDate());
	}
	//copy c'tor
	public Stock(Stock stock)
	{
		setSymbol(stock.getSymbol());
		//symbol = stock.getSymbol(); **thats the way you did it in class it works with it and without it 
		setAsk(stock.getAsk());       //i think that this line and the previous line are doing exactly  
		//ask = stock.getAsk();		  **the same action (if i am wrong i will be glad to get an explain why)
		setBid(stock.getBid());
		//bid = stock.getBid();
		setDate(stock.getDate());
		//date = stock.getDate();
	}
	
	public String getSymbol() 
	{
		return symbol;
	}
	public void setSymbol(String symbol) 
	{
		this.symbol = symbol;
	}
	public float getAsk() 
	{
		return ask;
	}
	public void setAsk(float ask) 
	{
		this.ask = ask;
	}
	public float getBid() 
	{
		return bid;
	}
	public void setBid(float bid) 
	{
		this.bid = bid;
	}
	public Date getDate() 
	{
		return date;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	/**
	 * method that suppose to return a string specific stock information
	 * @return
	 */
	public String getHtmlDescription(Stock[] stocks, int counter)
	{
		String stockHtmlDetailsString = "stock symbol: " + stocks[counter].getSymbol() + ", bid: " + stocks[counter].getBid() + ", ask: " + stocks[counter].getAsk() + ", date: " + stocks[counter].getDate() + "<br>";
		return stockHtmlDetailsString;
	}
}
