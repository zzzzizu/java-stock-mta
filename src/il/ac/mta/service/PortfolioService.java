package il.ac.mta.service;

import il.ac.mta.exception.NegativeBalanceException;
import il.ac.mta.exception.NegativeQuantityException;
import il.ac.mta.exception.NotEnoughQuantityExepction;
import il.ac.mta.exception.PortfolioFullException;
import il.ac.mta.exception.StockAlreadyExistsException;
import il.ac.mta.exception.StockNotExistException;
import il.ac.mta.model.Portfolio;
import il.ac.mta.model.Stock;

import java.util.Date;
import java.util.Calendar;

/** responsible to manage my stocks
 * 	changing any details on the stocks
 * 12/2014
 * @author daniel
 *
 */
public class PortfolioService
{	
	Portfolio myPortfolio = new Portfolio();
	
	public Portfolio getPortfolio() throws StockAlreadyExistsException, PortfolioFullException, NegativeBalanceException, StockNotExistException, NotEnoughQuantityExepction, NegativeQuantityException
	{
		myPortfolio.updateBalance((float)10000);
		Calendar c = Calendar.getInstance();
		c.set(2014,11,15,0,0,0);
		Date date = c.getTime();
		Stock stock1 = new Stock("PIH", 10, (float)8.5, date);
		myPortfolio.addStock(stock1);
		
		Stock stock2 = new Stock("AAL", 30, (float)25.5, date);
		myPortfolio.addStock(stock2);
	
		Stock stock3 = new Stock("CAAS", 20, (float)15.5, date);
		myPortfolio.addStock(stock3);
		myPortfolio.addStock(stock3);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}