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


@WebServlet("/Admin/User/Save")
public class SaveUserServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	

   UserService user = null;
	
	public SaveUserServlet() {
		
		user = new UserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
				PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>ADD NEW USER</title>"+css+ css2 + "</head>");
			    out.println("<body style='background-color: #ffa500;'>");
				out.println("<h1 align='center'>ADD NEW USER</h1>");
				out.print("<form action='/rwaprojekat/admin/User/Save' method='post'>");
				out.print("<table id='edit' align='center'>");
				out.print("<tr><td><div class='ui label'>USERNAME:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='username' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>PASSWORD:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='password' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>POSITION:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='position' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>QUIZES LIST:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='quizlist' value=''</div></td></tr>");
				out.print("</td></tr>");
				out.print("<tr><td colspan='2'><button class='ui button left floated' type='submit'>Save</button><button class='ui button left floated cancel'><a href='/rwaprojekat/admin/User' id='cancel'>Cancel</a></td></button></td></tr>");
				out.print("</table>");
				out.print("</form>");
			    out.println("</body></html>");
	
		out.close();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int position = Integer.valueOf((request.getParameter("position")));
		String quizlist = request.getParameter("quizlist");
		System.out.println("Username: " + username + "\t Password: " + password + "\t Position: " + position + "\t QuizList: " + quizlist);
	
		User usertwo = new User(username,password,position);
		user.insertUser(usertwo);
		response.sendRedirect("/rwaprojekat/admin/User");
	}
	

	
}