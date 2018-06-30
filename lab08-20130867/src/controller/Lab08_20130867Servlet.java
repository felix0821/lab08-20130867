package controller;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Lab08_20130867Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hola que hace el trabajo esta en proceso \\:v/");
	}
}
