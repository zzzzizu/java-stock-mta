package il.ac.mta.servlet;

import il.ac.mta.exception.NegativeBalanceException;
import il.ac.mta.exception.NegativeQuantityException;
import il.ac.mta.exception.NotEnoughQuantityExepction;
import il.ac.mta.exception.PortfolioFullException;
import il.ac.mta.exception.StockAlreadyExistsException;
import il.ac.mta.exception.StockNotExistException;
import il.ac.mta.model.Portfolio;
import il.ac.mta.service.PortfolioService;

import java.io.IOException;

import javax.servlet.ServletException;
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
			throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio;
		
		/**
		 * print all the stocks information and the head line or print an exception
		 * in case you have one
		 */
		
		try
		{
			portfolio = portfolioService.getPortfolio();
			resp.getWriter().println(portfolio.getHtmlString());
		}
		catch(NegativeBalanceException e)
		{
			resp.getWriter().println(e.getMessage());
		}
		catch(PortfolioFullException ee)
		{
			resp.getWriter().println(ee.getMessage());
		}
		catch(StockNotExistException eee)
		{
			resp.getWriter().println(eee.getMessage());
		}
		catch(StockAlreadyExistsException eeee)
		{
			resp.getWriter().println(eeee.getMessage());
		}
		catch(NotEnoughQuantityExepction eeeee)
		{
			resp.getWriter().println(eeeee.getMessage());
		}
		catch(NegativeQuantityException eeeeee)
		{
			resp.getWriter().println(eeeeee.getMessage());
		}
	}
}
