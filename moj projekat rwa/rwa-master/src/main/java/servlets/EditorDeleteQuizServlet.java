package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AnswerService;
import service.QuestionService;
import service.QuizService;


@WebServlet("/Editor/Delete")
public class EditorDeleteQuizServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
QuizService quiz = null;
QuestionService question = null;
AnswerService answer = null;

public EditorDeleteQuizServlet() {
	quiz = new QuizService();
	question = new QuestionService();
	answer = new AnswerService();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String id=request.getParameter("id");
quiz.deleteQuiz(id);
answer.deleteAnswerByQuestionNameMe(question.getQuestionByQuizName(id));
question.deleteQuestionByQuizName(id);
		response.sendRedirect("/rwaprojekat/Editor");
	}
}