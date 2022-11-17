package servlets;


	import java.io.IOException;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import domain.User;
	import service.UserService;

	@WebServlet("/LoginServlet")
	public class LoginServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    public LoginServlet() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			UserService userService = new UserService();
			User user = userService.getUserByName(name);
			
			if(user != null) {
				user = userService.getUserByNameAndPw(name,password);
				if( user == null) {
					request.setAttribute("error", "TYPE VALID USERNAME AND PASSWORD!");
					dispatcher = request.getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
					return;
				}
				if(user.getRole() == 1) {
					request.getSession().setAttribute("user", user.getName());
					response.sendRedirect("/rwaprojekat/admin");
				}
				else if(user.getRole() == 2) {
					request.getSession().setAttribute("user", user.getName());
					response.sendRedirect("/rwaprojekat/Editor");
					
				}
				else {
					response.sendRedirect("/rwaprojekat");
				}
			}
			else {
				request.setAttribute("error", "TYPE VALID USERNAME AND PASSWORD!");
				dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
		}

	}
