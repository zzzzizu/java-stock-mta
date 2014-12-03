package il.ac.mta.model;

import il.ac.mta.Stock;

import java.util.Date;

public class Portfolio 
{
	int portfolioSize = 0;
	private final int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StocksStatus[] stocksStatus;
	public Portfolio()
	{
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StocksStatus[MAX_PORTFOLIO_SIZE];
	}
	public Stock[] getStocks()
	{
		return stocks;
	}
	
	public void addStock (Stock stock)
	{
		this.stocks[portfolioSize] = stock;
		this.portfolioSize++;
	}
	// inner class:
	public class StocksStatus
	{
		String symbol;
		float currentBid, currentAsk;
		Date date;
		int recommendation;
		int stockQuantity;
		final static int DO_NOTHING = 0;
		final static int BUY = 1;
		final static int SELL = 2;
	}
	public String getHtmlString()
	{
		String titleAndPortfolioHtmlDetailsString = "<h1>Portfolio Title:</h1>";
		for(int counter = 0; counter < 3; counter++)
		{
			String portfolioHtmlDetailsString = "<b>stock " + (counter+1) + ":</b> stock symbol: " + getStocks()[counter].getSymbol() + ", bid: " + getStocks()[counter].getBid() + ", ask: " + getStocks()[counter].getAsk() + ", date: " + getStocks()[counter].getDate() + "<br>";
			titleAndPortfolioHtmlDetailsString = titleAndPortfolioHtmlDetailsString + portfolioHtmlDetailsString;
		}
		return titleAndPortfolioHtmlDetailsString;
	}
}
