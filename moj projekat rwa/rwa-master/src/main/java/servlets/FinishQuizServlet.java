package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Score;
import service.QuizService;
import service.ScoreService;
import service.UserService;

/**
 * Servlet implementation class FinishQuizServlet
 */
@WebServlet("/FinishQuizServlet")
public class FinishQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	QuizService quizService = new QuizService();
	UserService userService = new UserService();
	ScoreService scoreService = new ScoreService();

    public FinishQuizServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String points = (String) request.getParameter("score");
		Integer totalPoints = Integer.parseInt(points);
		String quizName = (String) request.getParameter("quizName");
		System.out.println(username + " " + email + " " + points + " " + quizName );

		Score score = new Score();
		score.setUsername(username);
		score.setQuizName(quizName);
		score.setUserEmail(email);
		score.setTotalPoints(totalPoints);
		scoreService.insertScore(score);
	}

}