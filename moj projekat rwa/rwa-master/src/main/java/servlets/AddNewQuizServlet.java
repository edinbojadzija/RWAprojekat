package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Quiz;
import domain.User;
import service.QuizService;
import service.UserService;


@WebServlet("/admin/QuizNew")
public class AddNewQuizServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

   QuizService quiz = null;
   Quiz novi = null;

   public AddNewQuizServlet()
   {
	   quiz = new QuizService();
	   novi = new Quiz();
   }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
				PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>NEW QUIZ</title>"+css+ css2 + "</head>");
			    out.println("<body style='background-color: #ffa500;'>");
				out.println("<h1 align='center'>ADD NEW QUIZ</h1>");
				out.print("<form action='/rwaprojekat/admin/QuizNew' method='post'>");
				out.print("<table id='edit' align='center'");
				out.print("<tr><td><div class='ui label'>QUIZ NAME:</div></td><td><div class='ui input'><input  type='text' style='width:500px;' name='quizname' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>DESCRIPTION:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='description' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ACTIVE STATUS:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='activeStatus' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>OWNER NAME:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='ownername' value=''</div></td></tr>");
				out.print("</td></tr>");
				out.print("<tr><td colspan='2'><button class='ui button right floated' type='submit'>SAVE</button><button class='ui button right floated cancel'><a href='/rwaprojekat/admin'>CANCEL</a></td></button></td></tr>");
				out.print("</table>");
				out.print("</form>");
			    out.println("</body></html>");
	
		out.close();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String namequiz = request.getParameter("quizname");
		String description = request.getParameter("description");
		String activestatus = request.getParameter("activeStatus");
		String ownername = request.getParameter("ownername");
		System.out.println("QuizName: " + namequiz + "\tDescription: " + description + "\t ActiveStatus: " + activestatus + "\t OwnerName: " + ownername);
	
		boolean active = (Integer.valueOf(activestatus) == 0) ? false : true;
		
		List<String> prazna = new ArrayList<String>();
		novi.setName(namequiz);
		novi.setDescription(description);
		novi.setActiveStatus(active);
		novi.setOwnerName(ownername);
		novi.setQuestionList(prazna);
		novi.setImagePath("");
		novi.setScoreList(prazna);
		
		quiz.insertQuiz(novi);
		
		
		response.sendRedirect("/rwaprojekat/admin");
		
	}
	

	
}