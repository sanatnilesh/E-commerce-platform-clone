package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.ApplicationDao;

@SuppressWarnings("serial")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String age = req.getParameter("age");
		String activity = req.getParameter("activity");
		Connection connection = (Connection) getServletContext().getAttribute("connection");

		User user = new User(userName, password, fname, lname, Integer.parseInt(age), activity);
		ApplicationDao registerDao = new ApplicationDao();
		int value = registerDao.insertUser(user, connection);
		String infoMessage;
		if (value == 1) {
			infoMessage = "User added!";
		} else {
			infoMessage = "User not added Somethig wrong!";
		}
		String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), infoMessage);
		resp.getWriter().write(page);
	}

	private String getHTMLString(String realPath, String infoMessage) {
		String page = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));
			String line = "";
			StringBuffer buffer = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				buffer.append(line);
			}
			bufferedReader.close();
			page = buffer.toString();
			page = MessageFormat.format(page, infoMessage);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), "");
		resp.getWriter().write(page);
	}
}
