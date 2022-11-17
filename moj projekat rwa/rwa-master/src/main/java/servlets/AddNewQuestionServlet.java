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

import domain.Answer;
import domain.Question;
import domain.Quiz;
import domain.User;
import service.AnswerService;
import service.QuestionService;
import service.QuizService;
import service.UserService;


@WebServlet("/admin/AddQuizQuestion")
public class AddNewQuestionServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

   QuizService quiz = null;
   Question pitanje = null;
   Answer odg = null; 
   String nameQuiz = null;
   
   QuestionService questionService = null;
   AnswerService answerService = null;
   

   public AddNewQuestionServlet()
   {
	   quiz = new QuizService();
	   questionService = new QuestionService();
	   pitanje = new Question();
	   odg = new Answer();
	   answerService = new AnswerService();
	   
   }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String csst="<link rel='stylesheet' type='text/css' href='css/admin.css''>";
		System.out.println(csst);
				PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>NEW QUESTION</title>"+css+ csst + "</head>");
			    out.println("<body style='background-color: #ffa500;'>");
			    nameQuiz = request.getParameter("id");
			    
				out.println("<h1 align='center'>Add new Question for Quiz " + nameQuiz + " </h1>");
				out.print("<form action='/rwaprojekat/admin/AddQuizQuestion' method='post'>");
				out.print("<table id='edit' align='center'>");
				out.print("<tr><td><div class='ui label'>QUESTION</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='question' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ANSWER 1:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer1' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ANSWER 2:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer2' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ANSWER 3:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer3' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ANSWER 4:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer4' value=''</div></td></tr>");
				
				out.print("<tr><td><div class='ui label'>TIME:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='time' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>CORRECT STATUS:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='cstatus' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>POINTS:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='points' value=''</div></td></tr>");
				
				out.print("</td></tr>");
				out.print("<tr><td colspan='2'><button class='ui button right floated' type='submit'>SAVE</button><button class='ui button right floated cancel'><a href='/rwaprojekat/admin'>CANCEL</a></td></button></td></tr>");
				out.print("</table>");
				out.print("</form>");
			    out.println("</body></html>");
	
		out.close();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String question = request.getParameter("question");
		String answer1 = request.getParameter("answer1");
		String answer2 = request.getParameter("answer2");
		String answer3 = request.getParameter("answer3");
		String answer4 = request.getParameter("answer4");
		String time = request.getParameter("time");
		String cstatus = request.getParameter("cstatus");
		String points = request.getParameter("points");
		
		int tacan = Integer.valueOf(cstatus);
		
		System.out.println(question + "," + answer1 + "," + answer2 + "," + answer3 + "," + time + "," + cstatus + "," + points);
		
		List<String> questionList = new ArrayList<String>();
		questionList.add(question);
		
		List<String> answerList = new ArrayList<String>();
		answerList.add(answer1);
		answerList.add(answer2);
		answerList.add(answer3);
		answerList.add(answer4);
		pitanje.setTime(Integer.valueOf(time));
		pitanje.setName(question);
		pitanje.setQuizName(nameQuiz);
		pitanje.setPoints(Integer.valueOf(points));
		pitanje.setAnswerList(answerList);
		
		for(int i = 0; i < answerList.size(); ++i)
		{
			odg.setName(answerList.get(i));
			odg.setQuestionName(question);
			if(i == tacan-1)
			{
				odg.setCorrectStatus(true);
			}
			else
			{
				odg.setCorrectStatus(false);
			}
			answerService.insertAnswer(odg);
			
		}
	
		quiz.insertQuestionList(nameQuiz, questionList);
		questionService.insertQuestion(pitanje);
		
		response.sendRedirect("/rwaprojekat/admin");
	}
}
	