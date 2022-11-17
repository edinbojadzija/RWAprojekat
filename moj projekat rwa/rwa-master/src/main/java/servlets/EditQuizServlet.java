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


@WebServlet("/admin/EditQuiz")
public class EditQuizServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	

   
   UserService user = null;
	

	public EditQuizServlet() {
		
		user = new UserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
		PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>QUIZ EDIT</title>"+css+ css2 + "</head>");
			    out.println("<body style='background-color: #ffa500;'>");
			    String nameQuiz=request.getParameter("id");
				System.out.println(nameQuiz);
				out.println("<h2 align='center'>Quiz edit " + nameQuiz + " </h1>");
				out.print("<div align='center'>");
				out.println("<button class='ui button center floated'><a href='/rwaprojekat/admin/EditInfoQuiz?id=" + nameQuiz +  " '>EDIT QUIZ INFO</a></button>");
				out.println("<button class='ui button center floated'><a href='/rwaprojekat/admin/AddQuizQuestion?id=" + nameQuiz + "'>ADD NEW QUESTION</a></button>");
				out.println("<button class='ui button right floated'><a href='/rwaprojekat/admin'>BACK</a></button>");
			
		out.close();
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}