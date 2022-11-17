package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import domain.User;

@WebServlet("/admin/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserService users = null;
	
	public UserServlet() {
		
		users = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";

		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><title>Users</title>"+ css+ css2 + "</head>");
		out.println("<body style='background-color: #ffa500;'>");
		out.print("<div align='center' id='header-buttons'>");
		out.println("<button class='ui button right floated'><a href='/rwaprojekat/admin'>BACK</a></button>");
		out.print("</div>");
		out.print("<div>");
		out.println("<h1 align='center'>USERS</h1>");
		
		List<User> allUsers = users.getAllUsers();
		
		out.print("<table border='1' width='100%'");
		for(User user: allUsers){
			out.print("<tr><td><div class='ui label admin'>"+user.getName()+"</div></td><td><div class='ui label admin'>"+user.getPassword()+
					"</div></td><td><div class='ui label admin'>"+user.getRoles()+"</div></td>" + "<td><a href='/rwaprojekat/admin/User/Edit?userName="+user.getName()+"'>"
					+ "<i class='edit icon'></i>" +  
					"</a></td></tr>"); }
		out.print("</table>");
		out.print("</div>");
		out.println("</body></html>");

		out.close();

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}