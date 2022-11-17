package servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */

@WebFilter("/Editor/*")
public class EditorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EditorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {    
        
    	HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/LoginServlet";

        System.out.println("URI:" + loginURI);
        
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        
        if (loggedIn || loginRequest) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginURI);
        }
    }

    // ...

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}