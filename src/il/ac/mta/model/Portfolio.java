package il.ac.mta.model;

import java.util.Date;


/**
 * defining the portfolio parameters
 * @author daniel
 *
 */
public class Portfolio 
{
	public enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL};
	int portfolioSize;
	private float balance;
	private static final int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StocksStatus[] stocksStatus;
	
	public Portfolio()
	{
		portfolioSize = 0;
		balance = 0;
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StocksStatus[MAX_PORTFOLIO_SIZE];
	}
	
	//copy c'tor
	public Portfolio(Portfolio portfolio)
	{
		portfolioSize = portfolio.portfolioSize;
		balance = portfolio.balance;
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stocksStatus = new StocksStatus[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i < 3; i++)
		{
			stocks[i] = new Stock(portfolio.stocks[i]);
			stocksStatus[i] = new StocksStatus(portfolio.stocksStatus[i]);
		}
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	
	/**
	 * adding a new stock and stock status to the portfolio 
	 * @param stock
	 */
	public void addStock(Stock stock)
	{
		for(int i = 0; i < this.portfolioSize; i++)
		{
			if(stocks[i].getSymbol().equals(stock.getSymbol()))
			{
				System.out.println("you already have this stock in your portfolio");
				return;
			}
		}
		
		if(this.portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			System.out.println("Can’t add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks");
		}
		
		else
		{
			stocksStatus[portfolioSize] = new StocksStatus();
			this.stocks[portfolioSize] = stock;
			this.stocksStatus[portfolioSize].setSymbol(getStocks()[portfolioSize].getSymbol());
			this.stocksStatus[portfolioSize].setCurrentAsk(getStocks()[portfolioSize].getAsk());
			this.stocksStatus[portfolioSize].setCurrentBid(getStocks()[portfolioSize].getBid());
			this.stocksStatus[portfolioSize].setDate(new Date(getStocks()[portfolioSize].getDate().getTime()));
			this.stocksStatus[portfolioSize].setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			this.stocksStatus[portfolioSize].setStockQuantity(0);
			this.portfolioSize++;
		}
	}
	
	/**
	 * removing the first stock in the portfolio
	 * @param stocks
	 */
	public boolean removeStock(String symbol)// to complete
	{
		boolean isStock = false;
		for(int i = 0; i < this.portfolioSize; i++)
		{
			if(stocks[i].getSymbol().equals(symbol))
			{
				isStock = true;
			}
			if(isStock)
			{
				if(portfolioSize != i+1)
				{
					sellStock(symbol, -1);
					this.stocks[i] = this.stocks[i+1];
					this.stocksStatus[i] = this.stocksStatus[i+1];
				}
				else
				{
					sellStock(symbol, -1);
				}
			}	
		}
		if(isStock)
		{
			portfolioSize--;
			return isStock;
		}
		else
		{
			System.out.println("you dont have this stock in your list");
			return isStock;
		}
	}
	public int getSize()
	{
		return portfolioSize;
	}
	/**
	 * return the current balance of your account
	 * @param amount
	 */
	public float getBalance()
	{
		return balance;
	}
	
	/**
	 * adding an amount to the current balance
	 * @param amount
	 */
	public void updateBalance(float amount)
	{
		this.balance = this.balance + amount;
		if(this.balance < 0)
		{
			System.out.println("pay attention the balance is negative");
		}
	}
	
	/** 
	 * return the value of all the stocks
	 * @return
	 */
	public float getStocksValue()
	{
		float stocksValue = 0;
		for(int i = 0;i < this.portfolioSize;i++)
		{
			stocksValue += stocksStatus[i].getCurrentBid()*stocksStatus[i].getStockQuantity();
		}
		return stocksValue;
	}
	
	/**
	 * return the value of all the stocks and the balance together
	 */
	public float getTotalValue()
	{
		return getStocksValue() + getBalance();
	}
	
	/**
	 * selling an amount of any stock
	 * @param symbol
	 * @param quantity
	 */
	public boolean sellStock(String symbol, int quantity)
	{
		boolean isStock = false;
		if(quantity < -1)
		{
			System.out.println("you can't enter negative quantity");
			return false;
		}
		else
		{
			for(int i = 0; i < this.portfolioSize; i++)
			{
				if(stocks[i].getSymbol().equals(symbol))
				{
					isStock = true;
				}
				if(isStock)
				{
					if(quantity == -1)
					{
						updateBalance(this.stocksStatus[i].getCurrentBid()*this.stocksStatus[i].getStockQuantity());
						this.stocksStatus[i].setStockQuantity(0);
						return isStock;
					}
					else
					{
						if(quantity > this.stocksStatus[i].getStockQuantity())
						{
							System.out.println("you don't have " + quantity + " " + symbol + " stocks to sell");
							return false;
						}
						updateBalance(this.stocksStatus[i].getCurrentBid()*quantity);
						this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity()-quantity);
						return isStock;
					}
				}	
			}
			System.out.println("you don't have a stock with this name on your list");
			return isStock;
		}
	}
	
	/**
	 * buying any amoung of any stock
	 * @author daniel
	 *
	 */
	
	public boolean buyStock(String symbol, int quantity)
	{
		boolean isStock = false;
		if(quantity < -1)
		{
			System.out.println("you can't enter negative quantity");
			return false;
		}
		else
		{
			for(int i = 0; i < this.portfolioSize; i++)
			{
				if(stocks[i].getSymbol().equals(symbol))
				{
					isStock = true;
				}
				if(isStock)
				{
					float maxQuantity;
					maxQuantity = getBalance()/stocksStatus[i].getCurrentAsk();
		
					if(quantity > maxQuantity)
					{
						System.out.println("you don't have enough money in your balance to buy " + quantity + " " + symbol);
						return false;
					}
					
					if(quantity == -1)
					{
						quantity = (int)maxQuantity;
						updateBalance(-(this.stocksStatus[i].getCurrentAsk()*quantity));
						this.stocksStatus[i].setStockQuantity(quantity);
						return isStock;
					}
					else
					{
						updateBalance(-(this.stocksStatus[i].getCurrentAsk()*quantity));
						this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity()+quantity);
						return isStock;
					}
				}	
			}
			System.out.println("you don't have a stock with this name on your list");
			return isStock;
		}
	}
	
	/**
	 * method that suppose to return a string with arranged stock information 
	 * @return
	 */
	public String getHtmlString()
	{
		String titleAndPortfolioHtmlDetailsString = "<h1>Exercise 7 portfolio</h1>";
		titleAndPortfolioHtmlDetailsString = titleAndPortfolioHtmlDetailsString + "Total Portfolio Value: " + getTotalValue() + "$, Total Stocks value: " + getStocksValue() + "$, Balance: " + getBalance() + "$<br><br>";
		for(int i = 0; i < this.portfolioSize; i++)
		{
			titleAndPortfolioHtmlDetailsString = titleAndPortfolioHtmlDetailsString + "<b>stock " + (i+1) + ": </b>" + stocks[i].getHtmlDescription() + ", quantity: " + stocksStatus[i].getStockQuantity() + "<br>";
		} 
		
		return titleAndPortfolioHtmlDetailsString;
	}
	
	// inner class:
	public class StocksStatus
	{
		String symbol;
		float currentBid, currentAsk;
		Date date;
		ALGO_RECOMMENDATION recommendation;
		int stockQuantity;
		
		public StocksStatus(){}
		// i defined the operators in the addStock method
		
		public StocksStatus(StocksStatus stockStatus)
		{
			symbol = stockStatus.symbol;
			currentBid = stockStatus.currentBid;
			currentAsk = stockStatus.currentAsk;
			date = new Date(stockStatus.date.getTime());
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

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
	
}
