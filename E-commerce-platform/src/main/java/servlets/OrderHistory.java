package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Order;
import dao.ApplicationDao;

@SuppressWarnings("serial")
@WebServlet("/orderHistory")
public class OrderHistory extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) req.getSession().getAttribute("username");
		ApplicationDao applicationDao = new ApplicationDao();
		List<Order> orderlist = applicationDao.getOrderHistory(username);
		System.out.println(orderlist.toString());
		req.setAttribute("items", orderlist);
		req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
	}
}
