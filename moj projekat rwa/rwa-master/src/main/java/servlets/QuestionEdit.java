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
import service.AnswerService;
import service.QuestionService;
import service.QuizService;

@WebServlet("/admin/QuestionEdit")
public class QuestionEdit extends HttpServlet {
private static final long serialVersionUID = 1L;

   QuizService quiz = null;
   Question pitanje = null;
   Question novoPitanje = null;
   Answer noviodg = null;
   Answer odg = null; 
   String nameQuiz = null;
   QuestionService questionService = null;
   AnswerService answerService = null;
   List<String> stariOdgovori = null; 
   String staroImePitanja = null; 

   public QuestionEdit()
   {
	   quiz = new QuizService();
	   questionService = new QuestionService();
	   odg = new Answer();
	   pitanje = new Question();
	   novoPitanje = new Question();
	   answerService = new AnswerService();
	   stariOdgovori = new ArrayList<String>();
	   novoPitanje = new Question();
	   noviodg = new Answer();
   }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Usao sam u get");
		response.setContentType("text/html");
		String css="<link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>";
		String css2= "<link rel='stylesheet' type='text/css' href='css/admin.css'>";
				PrintWriter out=response.getWriter();
			    out.println("<html>");
			    out.println("<head><title>NEW QUESTION</title>"+css+ css2 + "</head>");
			    out.println("<body style='background-color: #ffa500;'>");
			    nameQuiz = request.getParameter("id");
			    
			    pitanje = questionService.getQuestionByName(nameQuiz);

			    
			    
			    stariOdgovori.add(pitanje.getAnswerList().get(0));
			    stariOdgovori.add(pitanje.getAnswerList().get(1));
			    stariOdgovori.add(pitanje.getAnswerList().get(2));
			    stariOdgovori.add(pitanje.getAnswerList().get(3));
			    			    
				out.println("<h1 align='center'>Edit question for " +  nameQuiz + "</h1>");
				out.print("<form action='/rwaprojekat/admin/QuestionEdit' method='post'>");
				out.print("<table id='edit' align='center'>");
				
				out.print("<tr><td><div class='ui label'>ANSWER 1:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer1' value='" + pitanje.getAnswerList().get(0) + "'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ANSWER 2:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer2' value='" + pitanje.getAnswerList().get(1) + "'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ANSWER 3:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer3' value='" + pitanje.getAnswerList().get(2) + "'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>ANSWER 4:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='answer4' value='" + pitanje.getAnswerList().get(3) + "'</div></td></tr>");
				
				out.print("<tr><td><div class='ui label'>TIME:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='time' value='" + pitanje.getTime() + "'</div></td></tr>");
				out.print("<tr><td><div class='ui label'>CORRECT STATUS:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='cstatus' value=''</div></td></tr>");
				out.print("<tr><td><div class='ui label'>POINTS:</div></td><td><div class='ui input'><input type='text' style='width:500px;' name='points' value='" + pitanje.getPoints() + "'</div></td></tr>");
				
				out.print("</td></tr>");
				out.print("<tr><td colspan='2'><button class='ui button right floated' type='submit'>SAVE</button><button class='ui button right floated cancel'><a href='/rwaprojekat/admin'>CANCEL</a></td></button></td></tr>");
				out.print("</table>");
				out.print("</form>");
			    out.println("</body></html>");
	
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Uso sam u doPost");
		String answer1 = request.getParameter("answer1");
		String answer2 = request.getParameter("answer2");
		String answer3 = request.getParameter("answer3");
		String answer4 = request.getParameter("answer4");
		String time = request.getParameter("time");
		String cstatus = request.getParameter("cstatus");
		String points = request.getParameter("points");
		
		
		int tacan = Integer.valueOf(cstatus);

		
		List<String> answerList = new ArrayList<String>();
		answerList.add(answer1);
		answerList.add(answer2);
		answerList.add(answer3);
		answerList.add(answer4);
		
		questionService.updateQuestionAll(nameQuiz,Integer.valueOf(time),Integer.valueOf(points),answerList);
		answerService.deleteAnswerByQuestionName(nameQuiz);
		
		List<Answer> answerListtwo = new ArrayList<Answer>();
		for(int i=0;i<answerList.size();++i)
		{
			Answer novi = new Answer();
			novi.setName(answerList.get(i));
			novi.setQuestionName(nameQuiz);
			if(i==tacan-1)
			{
				novi.setCorrectStatus(true);
			}
			else
			{
				novi.setCorrectStatus(false);
			}
			answerService.insertAnswer(novi);
		}
		
		response.sendRedirect("/rwaprojekat/admin");
	}
}
