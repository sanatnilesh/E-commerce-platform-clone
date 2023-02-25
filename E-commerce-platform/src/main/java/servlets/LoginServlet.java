package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDao;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String html = "<html><h3>Please login</h3></html>";
		resp.getWriter().write(html + " ");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/html/login.jsp");
		dispatcher.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String password = req.getParameter("password");

		ApplicationDao applicationDao = new ApplicationDao();
		boolean auth = applicationDao.authenticateUser(userName, password);

		if (auth) {
			HttpSession session = req.getSession();
			session.setAttribute("username", userName);
			req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "Invalide User");
			req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
		}	

	}

}
