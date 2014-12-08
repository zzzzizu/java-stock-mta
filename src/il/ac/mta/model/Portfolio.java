package il.ac.mta.model;

import java.util.Date;

/**
 * defining the portfolio parameters
 * @author daniel
 *
 */
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
	
	//copy c'tor
	public Portfolio(Portfolio portfolio)
	{
		portfolioSize = portfolio.portfolioSize;
		for(int i = 0; i < MAX_PORTFOLIO_SIZE; i++)
		{
			this.addStock(portfolio.getStocks()[i]);
			stocks[i] = portfolio.stocks[i];
			stocksStatus[i] = portfolio.stocksStatus[i];
		}
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	
	public void addStock(Stock stock)
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
		
		public StocksStatus(StocksStatus stockStatus)
		{
			symbol = stockStatus.symbol;
			currentBid = stockStatus.currentBid;
			currentAsk = stockStatus.currentAsk;
			date = stockStatus.date;
			recommendation = stockStatus.recommendation;
			stockQuantity = stockStatus.stockQuantity;
			
		}
	}
	/**
	 * responsible to print the portfolio tile and all the stocks information
	 * @return
	 */
	Stock stock = new Stock();
	public String getHtmlString(int counter)
	{
		String portfolioHtmlDetailsString = "<b>stock " + (counter+1) + ": </b>" + stock.getHtmlDescription(getStocks(), counter);
		return portfolioHtmlDetailsString;
	}
}
