package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Product;
import dao.ApplicationDao;

@WebServlet("/search") 
@SuppressWarnings("serial")
public class GetServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String value = req.getParameter("search").toString();
		req.getSession().setAttribute("search", value);
		ApplicationDao applicationDao = new ApplicationDao();
		List<Product> products = applicationDao.searchProducts(value);
//		String page = getHTMLString(req.getServletContext().getRealPath("/html/searchResults.html"), products);
//		resp.getWriter().write(page);
		req.setAttribute("products", products);
		req.getRequestDispatcher("/html/searchResults.jsp").forward(req, resp);
	}

	private String getHTMLString(String realPath, List<Product> products) {
		String page = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));
			String line = "";
			StringBuffer buffer = new StringBuffer();
			while((line = bufferedReader.readLine())!=null) {
				buffer.append(line);
			}
			bufferedReader.close();
			page = buffer.toString();
			page = MessageFormat.format(page, products.get(0).getProductImgPath(),products.get(1).getProductImgPath(),products.get(2).getProductImgPath(),
					products.get(0).getProductName(),products.get(1).getProductName(),products.get(2).getProductName(),0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}

}
