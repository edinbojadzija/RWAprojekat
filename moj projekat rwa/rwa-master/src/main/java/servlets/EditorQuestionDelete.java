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
import service.UserService;

@WebServlet("/Editor/QuestionDelete")
public class EditorQuestionDelete extends HttpServlet {
private static final long serialVersionUID = 1L;

        QuizService quiz = null;
        QuestionService question = null;
        AnswerService answer = null;

		public EditorQuestionDelete() {
		 quiz = new QuizService();
		 question = new QuestionService();
		 answer = new AnswerService();
		}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionName = request.getParameter("id");
		question.deleteQuestion(questionName);
		answer.deleteAnswerByQuestionName(questionName);
		response.sendRedirect("/rwaprojekat/Editor");
	}
}