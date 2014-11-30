package il.ac.mta;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		resp.setContentType("text/html");
		
		stock stockDetails1 = new stock();
		
		stockDetails1.setSymbol("PIH");
		stockDetails1.setAsk((float) 12.4);
		stockDetails1.setBid((float) 13.1);
		Calendar c = Calendar.getInstance();
		c.set(2014,10,15,0,0,0);
		Date date = c.getTime();
		stockDetails1.setDate(date);
		
		stock stockDetails2 = new stock();
		
		stockDetails2.setSymbol("AAL");
		stockDetails2.setAsk((float) 5.5);
		stockDetails2.setBid((float) 5.78);
		Calendar c2 = Calendar.getInstance();
		c.set(2014,10,15,0,0,0);
		Date date2 = c2.getTime();
		stockDetails2.setDate(date);
		
		stock stockDetails3 = new stock();
		
		stockDetails3.setSymbol("CAAs");
		stockDetails3.setAsk((float) 31.5);
		stockDetails3.setBid((float) 31.2);
		Calendar c3 = Calendar.getInstance();
		c.set(2014,10,15,0,0,0);
		Date date3 = c3.getTime();
		stockDetails3.setDate(date);
		
		resp.getWriter().println("<b>stock 1:<b><br>");
		resp.getWriter().println(stockDetails1.getHtmlDescreption() + "<br>");
		
		resp.getWriter().println("stock 2:<br>");
		resp.getWriter().println(stockDetails2.getHtmlDescreption() + "<br>");
		
		resp.getWriter().println("stock 3:<br>");
		resp.getWriter().println(stockDetails3.getHtmlDescreption() + "<br>");
	}	
}
