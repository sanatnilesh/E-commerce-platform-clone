package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.ApplicationDao;

@SuppressWarnings("serial")
@WebServlet("/getProfileDetails")
public class ViewProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//get the username from the session
		System.out.println("User name in profile servlet  :"+ request.getSession().getAttribute("username"));
		
		ApplicationDao applicationDao = new ApplicationDao();
		User user = applicationDao.getUser((String)request.getSession().getAttribute("username"));
		request.setAttribute("user", user);
		//forward control to profile jsp
		request.getRequestDispatcher("/html/profile.jsp").forward(request, response);
		
	}


}
