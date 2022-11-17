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


@WebServlet("/admin/User/Edit")
public class EditUserServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	

   String nameUser;
   UserService user = null;
	

	public EditUserServlet() {
		
		user = new UserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
				PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>EDIT USER</title>"+css+ css2 +"</head>");
			    out.println("<body style='background-color: #ffa500;'>");
				out.println("<h2 align='center'>EDIT USER</h1>");
				String userName=request.getParameter("userName");
				nameUser = userName;
				System.out.println(nameUser);
				User userone = user.get(userName);
				out.print("<form action='/rwaprojekat/admin/User/Edit' method='post'>");
				out.print("<table id='edit' align='center'>");
				out.print("<tr><td><div class='ui label'>USERNAME:</div></td><td><div class='ui input'><input type='text' style='width:700px;' name='username' value='"+ userone.getName()+"'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>PASSWORD:</div></td><td><div class='ui input'><input type='text' style='width:700px;' name='password' value='"+ userone.getPassword() + "'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>POSITION:</div></td><td><div class='ui input'><input type='text' style='width:700px;' name='position' value='"+ userone.getRole()  +"'</div></td></tr>");
				out.print("</td></tr>");
				out.print("<tr><td colspan='2'><button class='ui button right floated' type='submit'>EDIT</button><button class='ui button right floated cancel'><a href='/rwaprojekat/admin/User'>CANCEL</a></td></button></td></tr>");
				out.print("</table>");
				out.print("</form>");
			    out.println("</body></html>");
	
		out.close();
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		int pos = Integer.valueOf(request.getParameter("position"));
		
		
		System.out.println("Username:" + userName + "\t Password:" + password + "\t Position:" + pos);
		User usertwo = new User(userName, password, pos);
		System.out.println(usertwo);
		user.updateUser(nameUser,usertwo.getName(),usertwo.getPassword(),usertwo.getRole());
		response.sendRedirect("/rwaprojekat/admin/User");
	}
	
}