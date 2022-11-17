package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.QuizService;
import domain.Quiz;

@WebServlet("/admin")
public class Admin extends HttpServlet {
	
	/**
	 * 
	 */
	
	QuizService quiz = null;
	
	private static final long serialVersionUID = 1L;
	public Admin()
	{
	   quiz = new QuizService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String css = "<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css''>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css''>";
		
		
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><title>Admin Page</title>"+css+ css2 + "</head>");
		out.println("<body>");
		out.print("<div align='center'>");
		out.println("<button class='ui button center floated'><a href='/rwaprojekat/admin/User'>LIST OF USERS</a></button>");
		out.println("<button class='ui button center floated'><a href='/rwaprojekat/Logout'>LOGOUT</a></button>");
	
		
		out.print("</div>");
		out.print("<div>");
		out.println("<h1 align='center'>LIST OF QUIZES</h1>");


	
		
		List<Quiz> quizes = getQuizs();

		out.print("<table border='1' width='100%'");
		out.print("<tr><th>ROLES </th><th>QUIZ NAME</th><th>DESCRIPTION</th><th>EDIT</th><th>DELETE</th></tr>");
		for(Quiz v:quizes){
			out.print("<tr><td><div class='ui label admin'>"+v.getOwnerName()+"</div></td><td><div class='ui label admin'>"+v.getName()+
					"</div></td>"
					+ "<td><div class='ui label admin'>" + v.getDescription() +"</div></td>"  
					+ "<td><a href='/rwaprojekat/admin/EditQuiz?id="+v.getName()+"'>"
					+ "<i class='edit icon'></i>" + 
					"</a></td><td><a href='/rwaprojekat/admin/Delete?id="+v.getName()+"'>"
					+ "<i class='trash icon'></i>" + 
					"</a></td></tr>");
		}
		out.print("</table>");
		out.print("</div>");
		out.println("</body></html>");

		out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	private List<Quiz> getQuizs() {
		List<Quiz>quizes = quiz.getAllQuizzes();
		return quizes;
	}

}

	