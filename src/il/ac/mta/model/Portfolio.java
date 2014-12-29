package il.ac.mta.model;


/**
 * defining the portfolio parameters
 * @author daniel
 *
 */
public class Portfolio 
{
	public enum ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL};
	private int portfolioSize;
	private float balance;
	private static final int MAX_PORTFOLIO_SIZE = 5;
	private StockStatus[] stockStatus;
	
	public Portfolio()
	{
		portfolioSize = 0;
		balance = 0;
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}
	
	//copy c'tor
	public Portfolio(Portfolio portfolio)
	{
		portfolioSize = portfolio.portfolioSize;
		balance = portfolio.balance;
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		for(int i = 0; i < 3; i++)
		{
			stockStatus[i] = new StockStatus(portfolio.stockStatus[i], portfolio.stockStatus[i].getRecommendation(), portfolio.stockStatus[i].getStockQuantity());
		}
	}
	
	/*public StockStatus[] getStockStatus()
	{
		return stockStatus;
	}*/ // used to check my copy copy c'tor of StockStatus
	
	public Stock[] getStocks()
	{
		return stockStatus;
	}
	
	/**
	 * adding a new stock and stock status to the portfolio 
	 * @param stock
	 */
	public void addStock(Stock stock)
	{
		for(int i = 0; i < this.portfolioSize; i++)
		{
			if(stockStatus[i].getSymbol().equals(stock.getSymbol()))
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
			stockStatus[portfolioSize] = new StockStatus(stock, ALGO_RECOMMENDATION.DO_NOTHING, 0);
			this.portfolioSize++;
		}
	}
	
	/**
	 * removing a chosen stock from the portfolio
	 * @param stocks
	 */
	public boolean removeStock(String symbol)
	{
		boolean isStock = false;
		for(int i = 0; i < this.portfolioSize; i++)
		{
			if(stockStatus[i].getSymbol().equals(symbol))
			{
				isStock = true;
			}
			if(isStock)
			{
				if(portfolioSize != i+1)
				{
					sellStock(symbol, -1);
					//this.stocks[i] = this.stocks[i+1];
					this.stockStatus[i] = this.stockStatus[i+1];
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
			stocksValue += stockStatus[i].getBid()*stockStatus[i].getStockQuantity();
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
				if(stockStatus[i].getSymbol().equals(symbol))
				{
					isStock = true;
				}
				if(isStock)
				{
					if(quantity == -1)
					{
						updateBalance(this.stockStatus[i].getBid()*this.stockStatus[i].getStockQuantity());
						this.stockStatus[i].setStockQuantity(0);
						return isStock;
					}
					else
					{
						if(quantity > this.stockStatus[i].getStockQuantity())
						{
							System.out.println("you don't have " + quantity + " " + symbol + " stocks to sell");
							return false;
						}
						updateBalance(this.stockStatus[i].getBid()*quantity);
						this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity()-quantity);
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
				if(stockStatus[i].getSymbol().equals(symbol))
				{
					isStock = true;
				}
				if(isStock)
				{
					float maxQuantity;
					maxQuantity = getBalance()/stockStatus[i].getAsk();
		
					if(quantity > maxQuantity)
					{
						System.out.println("you don't have enough money in your balance to buy " + quantity + " " + symbol);
						return false;
					}
					
					if(quantity == -1)
					{
						quantity = (int)maxQuantity;
						updateBalance(-(this.stockStatus[i].getAsk()*quantity));
						this.stockStatus[i].setStockQuantity(quantity);
						return isStock;
					}
					else
					{
						updateBalance(-(this.stockStatus[i].getAsk()*quantity));
						this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity()+quantity);
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
			titleAndPortfolioHtmlDetailsString = titleAndPortfolioHtmlDetailsString + "<b>stock " + (i+1) + ": </b>" + stockStatus[i].getHtmlDescription() + ", quantity: " + stockStatus[i].getStockQuantity() + "<br>";
		} 
		
		return titleAndPortfolioHtmlDetailsString;
	}
}
