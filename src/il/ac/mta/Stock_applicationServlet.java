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
		num1 = 2;
		num2 = 5;
		num3 = num1*num2;
		String resultStr = new String("<h1>Result of"+num1+"*"+num2+"="+num3+"</h1>");
		resp.getWriter().println(resultStr);
		resp.getWriter().println("Hello, world daniel daniel");
	}
}

