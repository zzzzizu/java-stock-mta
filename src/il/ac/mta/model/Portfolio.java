package il.ac.mta.model;

import il.ac.mta.exception.*;

import java.util.List;
import java.util.logging.Logger;

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
	public static final int MAX_PORTFOLIO_SIZE = 5;
	private StockStatus[] stockStatus;
	private String title;

	private Logger log = Logger.getLogger(Portfolio.class.getSimpleName());
	
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
	
	public Portfolio(List<StockStatus> stockStatuses) {
		for(int i = 0 ; i < stockStatuses.size(); i++)
		{
			stockStatus[i] = stockStatuses.get(i);
		}
		this.portfolioSize = stockStatuses.size(); 
	}
	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	/*public StockStatus[] getStockStatus()
	{
		return stockStatus;
	}*/ // used to check my copy copy c'tor of StockStatus
	
	public StockStatus[] getStocks()
	{
		return stockStatus;
	}
	
	/**
	 * adding a new stock and stock status to the portfolio 
	 * @param stock
	 * @throws StockAlreadyExistsException 
	 * @throws PortfolioFullException 
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException
	{
		for(int i = 0; i < this.portfolioSize; i++)
		{
			if(stockStatus[i].getSymbol().equals(stock.getSymbol()))
			{
				log.warning("Stock " + stock.getSymbol() + " already exists");
				throw new StockAlreadyExistsException(stock.getSymbol());
			}
		}
		
		if(this.portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			//System.out.println("Can’t add new stock, portfolio can have only " + MAX_PORTFOLIO_SIZE + " stocks");
			log.warning("You had reached maximum portfolio size [" + MAX_PORTFOLIO_SIZE + "]");
			throw new PortfolioFullException();
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
	 * @throws StockNotExistException 
	 * @throws BalanceException 
	 */
	public void removeStock(String symbol) throws StockNotExistException, NotEnoughQuantityExepction, NegativeQuantityException
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
					this.stockStatus[i] = this.stockStatus[i+1];
				}
				else
				{
					sellStock(symbol, -1);
				}
				break;
			}	
		}
		if(isStock)
		{
			portfolioSize--;
		}
		else
		{
			//System.out.println("you don't have this stock in your list");
			log.warning("Stock " + symbol + " isn't exists");
			throw new StockNotExistException(symbol);
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
	 * @throws StockNotExistException 
	 * @throws BalanceException 
	 */
	public void sellStock(String symbol, int quantity) throws StockNotExistException, NotEnoughQuantityExepction, NegativeQuantityException
	{
		boolean isStock = false;
		if(quantity < -1)
		{
			log.warning("you can't enter negative quantity");
			throw new NegativeQuantityException();
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
						return;
					}
					else
					{
						if(quantity > this.stockStatus[i].getStockQuantity())
						{
							//System.out.println("you don't have " + quantity + " " + symbol + " stocks to sell");
							log.warning("you don't have " + quantity + " " + symbol + " stocks to sell");
							throw new NotEnoughQuantityExepction(symbol, quantity);
						}
						updateBalance(this.stockStatus[i].getBid()*quantity);
						this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity()-quantity);
						return;
					}
				}	
			}
			//System.out.println("you don't have a stock with this name on your list");
			log.warning("Stock " + symbol + " isn't exists");
			throw new StockNotExistException(symbol);
		}
	}
	
	/**
	 * buying any amount of any stock
	 * @author daniel
	 * @throws BalanceException 
	 * @throws StockNotExistException 
	 *
	 */
	
	public void buyStock(String symbol, int quantity) throws NegativeBalanceException, StockNotExistException, NegativeQuantityException
	{
		boolean isStock = false;
		if(quantity < -1)
		{
			log.warning("you can't enter negative quantity");
			throw new NegativeQuantityException();
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
						//System.out.println("you don't have enough money in your balance to buy " + quantity + " " + symbol);
						log.warning("you don't have enough money in your balance to buy " + quantity + " " + symbol);
						throw new NegativeBalanceException(quantity);
					}
					
					if(quantity == -1)
					{
						quantity = (int)maxQuantity;
						updateBalance(-(this.stockStatus[i].getAsk()*quantity));
						this.stockStatus[i].setStockQuantity(quantity);
						return;
					}
					else
					{
						updateBalance(-(this.stockStatus[i].getAsk()*quantity));
						this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity()+quantity);
						return;
					}
				}	
			}
			//System.out.println("you don't have a stock with this name on your list");
			log.warning("Stock " + symbol + " isn't exists");
			throw new StockNotExistException(symbol);
		}
	}
	
	/**
	 * method that suppose to return a string with arranged stock information 
	 * @return
	 */
	public String getHtmlString()
	{
		String titleAndPortfolioHtmlDetailsString;
		setTitle("<h1>Exercise 9 portfolio</h1>");
		titleAndPortfolioHtmlDetailsString = title + "Total Portfolio Value: " + getTotalValue() + "$, Total Stocks value: " + getStocksValue() + "$, Balance: " + getBalance() + "$<br><br>";
		for(int i = 0; i < this.portfolioSize; i++)
		{
			titleAndPortfolioHtmlDetailsString = titleAndPortfolioHtmlDetailsString + "<b>stock " + (i+1) + ": </b>" + stockStatus[i].getHtmlDescription() + ", quantity: " + stockStatus[i].getStockQuantity() + "<br>";
		} 
		
		return titleAndPortfolioHtmlDetailsString;
	}

	public StockStatus findBySymbol(String symbol)//to do 
	{
		return null;
	}
}
