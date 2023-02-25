package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Product;
import dao.ApplicationDao;

@WebServlet("/addProducts")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get the HTTPSession object
		
		HttpSession httpSession = request.getSession();
		
		// create a cart as arraylist for the user
		List<String> cart = (ArrayList<String>) httpSession.getAttribute("cart");
		
		if (cart == null) {
			cart = new ArrayList<String>();
		}
		
		// add the selected product to the cart
		if (request.getParameter("product") != null) {
			cart.add(request.getParameter("product"));
		}
		
		httpSession.setAttribute("cart", cart);

		// get search criteria from search servlet
		String search = (String) httpSession.getAttribute("search");
		
		// get the search results from dao
		ApplicationDao applicationDao = new ApplicationDao();
		List<Product> products = applicationDao.searchProducts(search);
		
		// set the search results in request scope
		request.setAttribute("products", products);
		
		// forward to searchResults.jsp
		request.getRequestDispatcher("/html/searchResults.jsp").forward(request, response);
	}

}
