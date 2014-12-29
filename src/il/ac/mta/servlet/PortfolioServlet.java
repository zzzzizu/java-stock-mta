package il.ac.mta.servlet;

import il.ac.mta.model.Portfolio;
import il.ac.mta.model.Stock;
import il.ac.mta.model.StockStatus;
import il.ac.mta.service.PortfolioService;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * responsible to print according to html
 * @author daniel
 *
 */
public class PortfolioServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		resp.setContentType("text/html");
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();  // this line is here from the 3rd exercise and it is needless  
		// don't we need to erase it?
		
		/**
		 * print all the stocks information and the head line
		 */
		resp.getWriter().println(portfolio.getHtmlString());
	}
}
