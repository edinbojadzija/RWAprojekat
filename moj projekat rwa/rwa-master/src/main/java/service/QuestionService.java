package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Question;
import domain.Quiz;

public class QuestionService {
	
	Connection connection;
	
	public QuestionService() {
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rwa", "root", "root");
		}
		catch (SQLException e) {
			 System.out.println("Error while connecting to the database");
		}
	}
	
	public void insertQuestion(Question question) {
		try {
		PreparedStatement preparedStatement=connection.prepareStatement("insert into question values(?,?,?,?,?)");
		preparedStatement.setString(1,question.getName());
		preparedStatement.setString(2,question.getQuizName());
		preparedStatement.setInt(3, question.getPoints());
		preparedStatement.setInt(4, question.getTime());
		String answerList = "";
		for(int i = 0; i < question.getAnswerList().size(); ++i) {
			answerList += question.getAnswerList().get(i);
			if(i != question.getAnswerList().size() - 1) {
				answerList += ";";
			}
		}
		
		preparedStatement.setString(5,answerList);
		
		preparedStatement.executeUpdate();	
		}
		catch (SQLException e) {
			 System.out.println("Error while connecting to the database while inserting");
		System.out.println(e.getMessage());
		}
	}
	
	public int getQuestionsCount() {
		int result = 0;
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT COUNT(*) FROM question" ;
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				result = rs.getInt(1);
			}
			}
			catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		
		return result;
	}
	
	public List<Question> getAllQuestions() {
		List<Question> list = new ArrayList<Question>();
		
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT id,name,quizName,points,time,answerList FROM question" ;
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				Question question = new Question();
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String quizName = rs.getString(3);
				Integer points = rs.getInt(4);
				Integer time = rs.getInt(5);
				String aList = rs.getString(6);
				List<String> answerList = Arrays.asList(aList.split(";"));
				
				question.setId(id);
				question.setName(name);
				question.setQuizName(quizName);
				question.setPoints(points);
				question.setTime(time);
				question.setAnswerList(answerList);
				list.add(question);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		
		return list;
	}
	public List<Question> getQuestionByQuizName(String quizNamee) {
		List<Question> list = new ArrayList<Question>();
		
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT id,name,quizName,points,time,answerList FROM question WHERE quizName='" + quizNamee + "'";
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				Question question = new Question();
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String quizName = rs.getString(3);
				Integer points = rs.getInt(4);
				Integer time = rs.getInt(5);
				String aList = rs.getString(6);
				List<String> answerList = Arrays.asList(aList.split(";"));
				
				question.setId(id);
				question.setName(name);
				question.setQuizName(quizName);
				question.setPoints(points);
				question.setTime(time);
				question.setAnswerList(answerList);
				list.add(question);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		
		return list;
	}
	
	public void updateQuestion(String questionName, int timeToAnswer, int points) {
		try {
			String sql1 = "update question set points=?,time=? where name=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.setInt(1, points);
			preparedStatement.setInt(2, timeToAnswer);
			preparedStatement.setString(3, questionName);
			
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	}
	public void updateQuestionAll(String pitanje, int timeToAnswer, int points,List<String> answerList) {
		String odg = "";
		for(int i = 0; i < answerList.size(); ++i) {
			odg += answerList.get(i);
			if(i != answerList.size() - 1) {
				odg += ";";
			}
		}
		try {
				
			String sql1 = "update question set points='" + points  +   "',time='" + timeToAnswer + "',answerList='" + odg  +  "'where name='" + pitanje  + "'";
			
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			System.out.println(sql1);
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
	
				}
	}
	
	public Question getQuestionById(int id) {
		List<Question> list = this.getAllQuestions();
		for(Question q : list) {
			if(q.getId() == id) {
				return q;
			}
		}
	return null;
	}
	
	
	public Question getQuestionByName(String questionName) {
		List<Question> list = this.getAllQuestions();
		for(Question q : list) {
			if(q.getName().equals(questionName)) {
				return q;
			}
		}
	return null;
	}
	
	public void deleteQuestion(String questionName) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("delete from question where name=?");
			preparedStatement.setString(1,questionName);
			preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	public void deleteQuestionByQuizName(String quizName) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("delete from question where quizName=?");
			preparedStatement.setString(1,quizName);
			preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	


}