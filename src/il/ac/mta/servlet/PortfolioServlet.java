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
		
		resp.getWriter().println("<h1>Portfolio Title</h1>");
		for(int counter = 0; counter < 3; counter++)
		{
			resp.getWriter().println(portfolio.getHtmlString(counter));
		}
	}
}
