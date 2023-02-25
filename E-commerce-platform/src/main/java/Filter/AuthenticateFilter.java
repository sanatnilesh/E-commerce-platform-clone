package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class AuthenticateFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if(httpServletRequest.getRequestURI().equalsIgnoreCase("/SampleProject/getProfileDetails")) {
			HttpSession httpSession = httpServletRequest.getSession();
			if (httpSession.getAttribute("username") == null) {
				httpServletRequest.getRequestDispatcher("/html/login.jsp").forward(httpServletRequest, response);
			}
		}
		chain.doFilter(httpServletRequest, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
