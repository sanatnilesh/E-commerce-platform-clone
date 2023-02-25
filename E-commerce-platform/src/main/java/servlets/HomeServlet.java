package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/home", initParams = @WebInitParam(name = "URL", value = "https://weatherservice.com"))
public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig servletConfig = getServletConfig();
		ServletContext servletContext= getServletContext();
		System.out.println(servletContext.getInitParameter("dbConnectionURL"));
		System.out.println(servletConfig.getInitParameter("URL"));
		req.getRequestDispatcher("/html/index.html").forward(req, resp);
	}

}
