package il.ac.mta.model;

import java.util.Date;

/**
 * defining the portfolio parameters
 * @author daniel
 *
 */
public class Portfolio 
{
	int portfolioSize;
	private static final int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StocksStatus[] stocksStatus;
	
	public Portfolio()
	{
		portfolioSize = 0;
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StocksStatus[MAX_PORTFOLIO_SIZE];
	}
	
	//copy c'tor
	public Portfolio(Portfolio portfolio)
	{
		portfolioSize = portfolio.portfolioSize;
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i < 3; i++)
		{
			stocks[i] = new Stock(portfolio.stocks[i]);
		}
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	
	/**
	 * adding a new stock to the portfolio
	 * @param stock
	 */
	public void addStock(Stock stock)
	{
		this.stocks[portfolioSize] = stock;
		this.portfolioSize++;
	}
	
	/**
	 * removing the first stock in the portfolio
	 * @param stocks
	 */
	public void removeFirstStock(Stock[] stocks)
	{
		this.portfolioSize--;
		for(int i = 0; i < this.portfolioSize; i++)
		this.stocks[i] = this.stocks[i+1];
	}
	public int getSize()
	{
		return portfolioSize;
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
		
		public StocksStatus()
		{
			setSymbol(getSymbol());
			setCurrentBid(getCurrentBid());
			setCurrentAsk(getCurrentAsk());
			setDate(getDate());
			setRecommendation(getRecommendation());
			setStockQuantity(getStockQuantity());
		}
		
		public StocksStatus(StocksStatus stockStatus)
		{
			symbol = stockStatus.symbol;
			currentBid = stockStatus.currentBid;
			currentAsk = stockStatus.currentAsk;
			date = stockStatus.date;
			recommendation = stockStatus.recommendation;
			stockQuantity = stockStatus.stockQuantity;	
		}
		
		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
	/**
	 * method that suppose to return a string with arranged stock information 
	 * @return
	 */
	Stock stock = new Stock();
	public String getHtmlString(int counter)
	{
		String portfolioHtmlDetailsString = "<b>stock " + (counter+1) + ": </b>" + stock.getHtmlDescription(getStocks(), counter);
		return portfolioHtmlDetailsString;
	}
}
