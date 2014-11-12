package il.ac.mta;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Stock_applicationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		int num1; 
		int num2;
		int num3;
		num1 = 3;
		num2 = 4;
		num3 = 7;
		int result = (num1+num2)*num3;
		String resultStr = new String("<h1>Result of ("+num1+"+"+num2+")*"+num3+"="+result+"</h1>");
		resp.getWriter().println(resultStr);
		resp.getWriter().println("Hello, my name is daniel lang");
	}
}

