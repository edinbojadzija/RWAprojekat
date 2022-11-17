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
import service.QuestionService;
import domain.Question;

@WebServlet("/Editor/Question")
public class EditorQuestion extends HttpServlet {
	
	/**
	 * 
	 */
	
	QuestionService questionService = null;
	
	private static final long serialVersionUID = 1L;
	public EditorQuestion()
	{
	   questionService = new QuestionService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String css = "<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css''>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
		
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><title>Questions</title>"+css+ css2 + "</head>");
		out.println("<body style='background-color: #ffa500;'>");
		out.print("<div align='center'>");
		out.println("<button class='ui button right floated'><a href='/rwaprojekat/Editor'>BACK</a></button>");
	
		String id = request.getParameter("id");
		
		
		
		out.print("</div>");
		out.print("<div>");
		out.println("<h1 align='center'>Questions for " + id + "</h1>");


	
		
		List<Question> listaPitanja = questionService.getQuestionByQuizName(id);

		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Question</th><th>Time</th><th>Points</th><th>Edit</th><th>Delete</th></tr>");
		for(Question v:listaPitanja){
	
			out.print("<tr><td><div class='ui label admin'>"+v.getName()+"</div></td><td><div class='ui label admin'>" + v.getTime() +"</div></td>"
					+ "<td><div class='ui label admin'>" + v.getPoints() +"</div></td>"  
					+ "<td><a href='/rwaprojekat/Editor/QuestionEdit?id="+v.getName()+"'>"
					+ "<i class='edit icon'></i>"
					+ "<td><a href='/rwaprojekat/Editor/QuestionDelete?id="+v.getName()+"'>"
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

}
	