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
		resp.getWriter().println("<h1>ex1+ex2:<h1>");
		resp.getWriter().println("<h1>Hello, my name is daniel lang<h1>");
		resp.getWriter().println(resultStr);
		
		resp.getWriter().println("<h1>ex3:<h1>");
		double radius = 50;
		double area = Math.PI*(Math.pow(radius, 2));
		String line1 = new String("1) Area of circle with radius " + radius +" is: " + area + " square cm.");
		
		double angleB = 30;
		double hypotenuse = 50;
		double opposite = Math.sin(Math.toRadians(angleB))*hypotenuse;
		String line2 = new String("2) Length of opposite where angle B is " + angleB + " degrees and Hypotenuse length is " + hypotenuse + " cm is: " + opposite+" cm");
		
		double base = 20;
		double exp = 13;
		double result2 = Math.pow(base, exp);
		String line3 = new String("3) Power of " + base + " with exp of "+ exp + " is " + result2);
		
		String resultStr2 = line1 + "<br>" + line2 + "<br>" + line3;
		resp.getWriter().println(resultStr2);
		
	}
}

