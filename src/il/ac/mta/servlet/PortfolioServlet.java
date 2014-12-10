package il.ac.mta.servlet;

import il.ac.mta.model.Portfolio;
import il.ac.mta.model.Stock;
import il.ac.mta.service.PortfolioService;

import java.io.IOException;

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
		Stock[] stocks = portfolio.getStocks();
		
		/**
		 * print all the stocks information
		 */
		resp.getWriter().println("<h1>Portfolio Title</h1>");
		for(int counter = 0; counter < 3; counter++)
		{
			resp.getWriter().println(portfolio.getHtmlString(counter));
		}
		Portfolio portfolio2 = new Portfolio(portfolio);
		
		resp.getWriter().println("<h1>Portfolio Title #2</h1>");
		/** 
		 * print the 2 samples of stock as they are
		 */
		resp.getWriter().println("sample1:<br>");
		for(int counter = 0; counter < portfolio.getSize(); counter++)
		{
			resp.getWriter().println(portfolio.getHtmlString(counter));
		}
		
		resp.getWriter().println("<br>sample2:<br>");
		for(int counter = 0; counter < portfolio2.getSize(); counter++)
		{
			resp.getWriter().println(portfolio2.getHtmlString(counter));
		}
		resp.getWriter().println("<h1></h1>");
		
		//remove stock 1
		portfolio.removeFirstStock(stocks);
		/**
		 * print the 2 samples with the first stock in sample 1 removed
		 */
		resp.getWriter().println("sample1:<br>");
		for(int counter = 0; counter < portfolio.getSize(); counter++)
		{
			resp.getWriter().println(portfolio.getHtmlString(counter));
		}
		
		resp.getWriter().println("<br>sample2:<br>");
		for(int counter = 0; counter < portfolio2.getSize(); counter++)
		{
			resp.getWriter().println(portfolio2.getHtmlString(counter));
		}
		
		resp.getWriter().println("<h1></h1>");
		//change stock 3
		portfolio2.getStocks()[2].setBid((float) 55.5);
		
		/**
		 * print the 2 samples with new bid to stock 3 in sample 2
		 */
		resp.getWriter().println("sample1:<br>");
		for(int counter = 0; counter < portfolio.getSize(); counter++)
		{
			resp.getWriter().println(portfolio.getHtmlString(counter));
		}
		
		resp.getWriter().println("<br>sample2:<br>");
		for(int counter = 0; counter < portfolio2.getSize(); counter++)
		{
			resp.getWriter().println(portfolio2.getHtmlString(counter));
		}

	}
}
