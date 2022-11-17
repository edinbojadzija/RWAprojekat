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

@WebServlet("/Editor")
public class Editor extends HttpServlet {
	
	/**
	 * 
	 */
	String editorName = null;
	QuizService quiz = null;
	
	private static final long serialVersionUID = 1L;
	public Editor()
	{
	   quiz = new QuizService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		editorName = (String) request.getSession().getAttribute("user");
		response.setContentType("text/html");
		String css = "<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css''>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
		
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head><title>EDITOR PAGE</title>"+css+ css2 + "</head>");
		out.println("<body style='background-color: #ffa500;'>");
		out.print("<div align='center'>");
		out.println("<button class='ui button center floated'><a href='/rwaprojekat/Editor/NewQuiz'>ADD NEW QUIZ</a></button>");
		out.println("<button class='ui button center floated'><a href='/rwaprojekat/Logout'>LOGOUT</a></button>");
	
		
		out.print("</div>");
		out.print("<div>");
		out.println("<h1 align='center'>List of Quiz</h1>");


	
		
		List<Quiz> quizes = getQuizsByOwner(editorName);

		out.print("<table border='1' width='100%'");
		out.print("<tr><th>ROLES </th><th>QUIZ NAME</th><th>DESCRIPTION</th><th>EDIT</th><th>DELETE</th></tr>");
		for(Quiz v:quizes){
			out.print("<tr><td><div class='ui label admin'>"+v.getOwnerName()+"</div></td><td><div class='ui label admin'>"+v.getName()+
					"</div></td>"
					+ "<td><div class='ui label admin'>" + v.getDescription() +"</div></td>"  
					+ "<td><a href='/rwaprojekat/Editor/EditorEdit?id="+v.getName()+"'>"
					+ "<i class='edit icon'></i>" + 
					"</a></td><td><a href='/rwaprojekat/Editor/Delete?id="+v.getName()+"'>"
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

	private List<Quiz> getQuizsByOwner(String owner) {
		List<Quiz>quizes = quiz.getQuizByOwner(owner);
		return quizes;
	}

}