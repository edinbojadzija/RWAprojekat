package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;


@WebServlet("/Editor/EditInfo")
public class EditInfo extends HttpServlet {
private static final long serialVersionUID = 1L;
	

   String nameUser;
   UserService user = null;
   User userone = null;
	

	public EditInfo() {
		
		user = new UserService();
		userone = new User();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		nameUser = (String) request.getSession().getAttribute("user");
		System.out.println(nameUser);
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
				PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>EDIT USER</title>"+css+ css2 +"</head>");
			    out.println("<body style='background-color: #ffa500;'>");
				out.println("<h2 align='center'>Edit User</h1>");
			    userone = user.get(nameUser);
				out.print("<form action='/rwaprojekat/Editor/EditInfo' method='post'>");
				out.print("<table id='edit' align='center'>");
				out.print("<tr><td><div class='ui label'>USERNAME:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='username' value='"+ userone.getName()+"'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>PASSWORD:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='password' value='"+ userone.getPassword() + "'</div></td></tr>");
				out.print("</td></tr>");
				out.print("<tr><td colspan='2'><button class='ui button right floated' type='submit'>EDIT</button><button class='ui button right floated cancel'><a href='/rwaprojekat/Editor'>CANCEL</a></td></button></td></tr>");
				out.print("</table>");
				out.print("</form>");
			    out.println("</body></html>");
	
		out.close();
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		System.out.println("Username:" + userName + "\t Password:" + password + "\t Position:" + userone.getRole());
		User usertwo = new User(userName, password, userone.getRole());
		System.out.println(usertwo);
		user.updateUser(nameUser,usertwo.getName(),usertwo.getPassword(),usertwo.getRole());
		response.sendRedirect("/rwaprojekat/Editor");
	}
	
}