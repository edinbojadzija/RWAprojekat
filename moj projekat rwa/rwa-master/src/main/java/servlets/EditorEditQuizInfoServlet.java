package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Quiz;
import domain.User;
import service.QuizService;
import service.UserService;

@WebServlet("/Editor/EditInfoQuiz")
public class EditorEditQuizInfoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	

   QuizService quiz = null;
   Quiz novi = null;

	public EditorEditQuizInfoServlet() {
		
		quiz = new QuizService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
				PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>EDIT QUIZ INFO</title>"+css+ css2 + "</head>");
			    out.println("<body style='background-color: #ffa500;'>");
				out.println("<h2 align='center'>EDIT QUIZ INFO </h1>");
				String quizName=request.getParameter("id");
				System.out.println(quizName);
				
			    novi = quiz.getQuizByName(quizName);
				out.print("<form action='/rwaprojekat/Editor/EditInfoQuiz' method='post'>");
				out.print("<table id='edit' align='center'>");
				out.print("<tr><td><div class='ui label'>QUIZ NAME:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='name' value='"+ novi.getName()+"'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>DESCRIPTION:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='description' value='"+ novi.getDescription() + "'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ACTIVE STATUS:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='activestatus' value='"+ novi.getActiveStatus() +"'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>IMAGE PATH:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='imagepath' value='"+ novi.getImagePath() +"'</div></td></tr>");
				
				out.print("</td></tr>");
				out.print("<tr><td colspan='2'><button class='ui button right floated' type='submit'>EDIT</button><button class='ui button right floated cancel'><a href='/rwaprojekat/Editor'>CANCEL</a></td></button></td></tr>");
				out.print("</table>");
				out.print("</form>");
			    out.println("</body></html>");
	
		out.close();
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		boolean activeStatus = request.getParameter("activestatus").equals("false") ? false : true;
		String imagePath = request.getParameter("imagepath");
		
		System.out.println("Staro ime:" + novi.getName() + "\tNovo:" + name + "\t Description:" + description + "\t ActiveStatus:" + activeStatus + "\tImagePath:" + imagePath + "\n");
		
		quiz.updateQuiz(novi.getName(), name, description, imagePath, activeStatus);
		response.sendRedirect("/rwaprojekat/Editor");
	}
	
}