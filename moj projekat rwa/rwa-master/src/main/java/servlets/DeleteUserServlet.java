package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;


@WebServlet("/admin/User/Delete")
public class DeleteUserServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

        UserService user = null;

		public DeleteUserServlet() {
		 user = new UserService();
		}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userName");
		user.deleteUser(username);
		response.sendRedirect("/rwaprojekat/admin/User");
	}
}