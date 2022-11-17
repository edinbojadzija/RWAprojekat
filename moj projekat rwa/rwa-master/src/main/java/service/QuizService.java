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
import java.util.Random;

import domain.Question;
import domain.Quiz;
import domain.User;

public class QuizService {
	
	static int id = 1;
	Connection connection;
	
	public QuizService() {
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rwa", "root", "root");
		pokusaj();
		}
		catch (SQLException e) {
			 System.out.println("Error while connecting to the database");
		}
	}
	
	public void pokusaj()
	{
		try {
			String sql = "Select id from quiz";
			PreparedStatement preparedStatement1=connection.prepareStatement(sql);
			ResultSet rs = preparedStatement1.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void insertQuestionList(String nameQuiz,List<String> questionList)
	{
		try {
			
			String sql = "Select questionList from quiz where name='" + nameQuiz + "'";
			PreparedStatement preparedStatement1=connection.prepareStatement(sql);
			
			String result = "";
			ResultSet rs = preparedStatement1.executeQuery(sql);
			while(rs.next()) {
				result = rs.getString(1);
				if(!result.equals(""))
				{
					result+=";";
				}
			}
			
			for(int i = 0; i < questionList.size(); ++i) {
					result += questionList.get(i);
					if(i != questionList.size() - 1) {
						result += ";";
					}
			}
			
			String sql1 = "update quiz set questionList=? where name=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.setString(1, result);
			preparedStatement.setString(2, nameQuiz);
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	
	}
	
	public void insertQuiz(Quiz quiz) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("insert into quiz(name,description,questionList,activeStatus,ownerName,imagePath,scoreList,id) values(?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1,quiz.getName());
			preparedStatement.setString(2,quiz.getDescription());
			String questionList = "";
			for(int i = 0; i < quiz.getQuestionList().size(); ++i) {
				questionList += quiz.getQuestionList().get(i);
				if(i != quiz.getQuestionList().size() - 1) {
					questionList += ";";
				}
			}
			preparedStatement.setString(3, questionList);
			preparedStatement.setBoolean(4, quiz.isActiveStatus());
			preparedStatement.setString(5, quiz.getOwnerName());
			preparedStatement.setString(6, quiz.getImagePath());
			String scoreList = "";
			
			for(int i = 0; i < quiz.getScoreList().size(); ++i) {
				scoreList += quiz.getScoreList().get(i);
				if(i != quiz.getScoreList().size() - 1) {
					scoreList += ";";
				}
			}
			UserService userService = new UserService();
			User owner = userService.getUserByName(quiz.getOwnerName());
			System.out.println("Owner name: " + owner.getName());
			System.out.println("Owner name: " + owner.getQuizListString());
			
			List<String> newList = new ArrayList<String>();
			if(owner.getQuizList().size() > 0) {
				for(String q: owner.getQuizList()) {
					newList.add(q);
				}
			} 
			newList.add(quiz.getName());
			for(int i = 0 ; i < newList.size(); ++i) {
				System.out.println("newList: " + newList.get(i));
			}
			System.out.println("newList size: " + newList.size());
			
			userService.refreshQuizList(owner, newList);
			
			preparedStatement.setString(7, scoreList);
			preparedStatement.setInt(8, ++id);
			
			preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
				 System.out.println("Error while connecting to the database while inserting");
			System.out.println(e.getMessage());
			}
	
	}
	
	
	public List<Quiz> getAllQuizzes(){
		List<Quiz> list = new ArrayList<Quiz>();
		
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT questionListId,name,description,questionList,activeStatus,ownerName,imagePath,scoreList,id FROM quiz" ;
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				Quiz quiz = new Quiz();
				String qListId = rs.getString(1);
				List<String> questionListId = Arrays.asList(qListId.split(";"));
				String name = rs.getString(2);
				String description = rs.getString(3);
				String qList = rs.getString(4);
				List<String> questionList = Arrays.asList(qList.split(";"));
				Boolean activeStatus = rs.getBoolean(5);
				String ownerName = rs.getString(6);
				String imagePath = rs.getString(7);
				String sList = rs.getString(8);
				Integer id = rs.getInt(9);
				List<String> scoreList = new ArrayList<String>();
				
				System.out.println(qListId);
				
				quiz.setQuestionListId(questionListId);
				quiz.setName(name);
				quiz.setDescription(description);
				quiz.setQuestionList(questionList);
				quiz.setActiveStatus(activeStatus);
				quiz.setOwnerName(ownerName);
				quiz.setImagePath(imagePath);
				quiz.setScoreList(scoreList);
				quiz.setId(id);
				list.add(quiz);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		
		
		
		return list;
	}
	
	public List<Quiz> getQuizByOwner(String owner){
		List<Quiz> list = new ArrayList<Quiz>();
		
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT name,description,questionList,activeStatus,ownerName,imagePath,scoreList FROM quiz where ownerName='" + owner + "'" ;
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				Quiz quiz = new Quiz();
				String name = rs.getString(1);
				String description = rs.getString(2);
				String qList = rs.getString(3);
				List<String> questionList = Arrays.asList(qList.split(";"));
				Boolean activeStatus = rs.getBoolean(4);
				String ownerName = rs.getString(5);
				String imagePath = rs.getString(6);
				String sList = rs.getString(7);
				List<String> scoreList = new ArrayList<String>();
				
				quiz.setName(name);
				quiz.setDescription(description);
				quiz.setQuestionList(questionList);
				quiz.setActiveStatus(activeStatus);
				quiz.setOwnerName(ownerName);
				quiz.setImagePath(imagePath);
				quiz.setScoreList(scoreList);
				list.add(quiz);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		
		return list;
	}
	
	public Quiz getQuizByName(String quizName) {
		List<Quiz> list = this.getAllQuizzes();
		for(Quiz q : list) {
			if(q.getName().equals(quizName)) {
				return q;
			}
		}
	return null;
	}
	
	
	
	public int getQuizzesCount() {
		int result = 0;
		
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT COUNT(*) FROM quiz" ;
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

	
	public List<Quiz> getActiveQuizzes(){
		List<Quiz> returnList = new ArrayList<Quiz>();
		List<Quiz> list = this.getAllQuizzes();
		for(Quiz q : list) {
			if(q.isActiveStatus()) {
				returnList.add(q);
			}
		}
		return list;
	}

	public List<Quiz> getRandomQuizzes(){
		List<Quiz> activeQuizzes = this.getActiveQuizzes();
		List<Quiz> randomQuizzes = new ArrayList<Quiz>();
		Random rand = new Random();
		int size = activeQuizzes.size();
		
		Quiz quiz1 = activeQuizzes.get(rand.nextInt(size));
		Quiz quiz2 = activeQuizzes.get(rand.nextInt(size));
		
		randomQuizzes.add(quiz1);
		randomQuizzes.add(quiz2);
		
		return randomQuizzes;
	}
	
	public void deleteQuiz(String quizName) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("delete from quiz where name=?");
			preparedStatement.setString(1,quizName);
			preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	
	public void updateQuiz(String oldQuizName, String quizName, String quizDesc, String imagePath, boolean activeStatus) {
		try {
			String sql1 = "update quiz set name=?, description=?,activeStatus=?, imagePath=? where name=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.setString(1, quizName);
			preparedStatement.setString(2, quizDesc);
			preparedStatement.setBoolean(3, activeStatus);
			preparedStatement.setString(4, imagePath);
			preparedStatement.setString(5, oldQuizName);
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	}
	
	
	public Quiz getQuizById(Integer id) {
		List<Quiz> list = this.getAllQuizzes();
		for(Quiz q : list) {
			if(q.getId().equals(id)) {
				return q;
			}
		}
	return null;
	}


}