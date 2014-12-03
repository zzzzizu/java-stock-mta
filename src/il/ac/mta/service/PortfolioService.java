package il.ac.mta.service;

import il.ac.mta.Stock;
import il.ac.mta.model.Portfolio;

import java.util.Date;
import java.util.Calendar;

public class PortfolioService
{	
	Portfolio myPortfolio = new Portfolio();
	public Portfolio getPortfolio()
	{	
		Stock stock1 = new Stock();
		stock1.setSymbol("PIH");
		stock1.setAsk((float) 12.4);
		stock1.setBid((float) 13.1);
		Calendar c = Calendar.getInstance();
		c.set(2014,10,15,0,0,0);
		Date date = c.getTime();
		stock1.setDate(date);
		myPortfolio.addStock(stock1);
		
		Stock stock2 = new Stock();
		stock2.setSymbol("AAL");
		stock2.setAsk((float) 5.5);
		stock2.setBid((float) 5.78);
		stock2.setDate(date);
		myPortfolio.addStock(stock2);
	
		Stock stock3 = new Stock();
		stock3.setSymbol("CAAS");
		stock3.setAsk((float) 31.5);
		stock3.setBid((float) 31.2);
		stock3.setDate(date);
		myPortfolio.addStock(stock3);
		return myPortfolio;
	}
}
