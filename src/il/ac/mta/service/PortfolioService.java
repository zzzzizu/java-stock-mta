package il.ac.mta.service;

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
	
	public Portfolio getPortfolio()
	{
		myPortfolio.updateBalance((float)10000);
		Calendar c = Calendar.getInstance();
		c.set(2014,11,15,0,0,0);
		Date date = c.getTime();
		Stock stock1 = new Stock("PIH", 10, (float)8.5, date);
		/*stock1.setSymbol("PIH");
		stock1.setAsk((float) 10);
		stock1.setBid((float) 8.5);
		Calendar c = Calendar.getInstance();
		c.set(2014,11,15,0,0,0);
		Date date = c.getTime();
		stock1.setDate(date);*/
		myPortfolio.addStock(stock1);
		
		Stock stock2 = new Stock("AAL", 30, (float)25.5, date);
	/*	stock2.setSymbol("AAL");
		stock2.setAsk((float) 30);
		stock2.setBid((float) 25.5);
		stock2.setDate(date);*/
		myPortfolio.addStock(stock2);
	
		Stock stock3 = new Stock("CAAS", 20, (float)15.5, date);
		/*stock3.setSymbol("CAAS");
		stock3.setAsk((float) 20);
		stock3.setBid((float) 15.5);
		stock3.setDate(date);*/
		myPortfolio.addStock(stock3);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}
