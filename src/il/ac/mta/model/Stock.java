package il.ac.mta.model;

import java.util.Date;

/**
 * defining the stock parameters
 * @author daniel
 *
 */
public class Stock 
{
	protected String symbol;
	protected float ask;
	protected float bid;
	protected Date date;
	
	public Stock(String Symbol, float Ask, float Bid, Date Date)
	{
		symbol = Symbol;
		ask = Ask;
		bid = Bid;
		date = Date;
	}
	//copy c'tor
	public Stock(Stock stock)
	{
		setSymbol(stock.getSymbol());
		setAsk(stock.getAsk());     
		setBid(stock.getBid());
		date = new Date(stock.date.getTime());
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
	public String getHtmlDescription()
	{
		String stockHtmlDetailsString = "stock symbol: " + getSymbol() + ", bid: " + getBid() + "$, ask: " + getAsk() + "$, date: " + getDate();
		return stockHtmlDetailsString;
	}
}
