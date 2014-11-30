package il.ac.mta;

import java.util.Date;

public class stock 
{
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	
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
	
	public String getHtmlDescreption()
	{
		String stockHtmlDetailsString = "<br>stock symbol<b>: " + getSymbol() + "<br>bid<b>: " + getBid() + "<br>ask<b>: " + getAsk() + "<br>date<b>: " + getDate() + "<br>";
		return stockHtmlDetailsString;
	}
}
