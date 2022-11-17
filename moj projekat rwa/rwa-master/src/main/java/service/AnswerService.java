package service;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Answer;
import domain.Question;
import domain.Quiz;

public class AnswerService {
	
	Connection connection;
	
	public AnswerService() {
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rwa", "root", "root");
		}
		catch (SQLException e) {
			 System.out.println("Error while connecting to the database");
		}
	}
	
	public void insertAnswer(Answer answer) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("insert into answer values(?,?,?)");
			preparedStatement.setString(1,answer.getName());
			preparedStatement.setString(2,answer.getQuestionName());
			preparedStatement.setBoolean(3, answer.isCorrectStatus());
			
			preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
				 System.out.println("Error while connecting to the database while inserting");
			System.out.println(e.getMessage());
			}
	}
	
	public int getAnswersCount() {
		int result = 0;
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT COUNT(*) FROM answer" ;
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
	
	public void updateAnswer(String questionName, String answerName, boolean status) {
		try {
			String sql1 = "update answer set correctStatus=? where name=? and questionName=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.setBoolean(1, status);
			preparedStatement.setString(2, answerName);
			preparedStatement.setString(3, questionName);
			
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	}
	
	public void updateAnswerAll(String staroImePitanja,List<String>stariOdgovori,String novoImePitanja,List<Answer> odgovori,int cstatus) {
		try {
			
			String sql1;
			for(int i=0; i < odgovori.size();++i)
			{
				sql1 = "update answer set correctStatus='" + odgovori.get(i).isCorrectStatus() + "',questionName='" + novoImePitanja +",name='" + odgovori.get(i).getName() + "' where name='" + stariOdgovori.get(i) + "' AND questionName='" + staroImePitanja + "'";
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.executeUpdate(); 
			}
		}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	}
	
	public void moveAnswerToQuestion(String answerName,String oldQuestionName, String newQuestionName) {
		try {
			String sql1 = "update answer set questionName=? where name=? and questionName=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.setString(1, newQuestionName);
			preparedStatement.setString(2, answerName);
			preparedStatement.setString(3, oldQuestionName);
			
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	}

	public void deleteAnswerByQuestionName(String questionname) {
		try {
			String sql1 = "delete from answer where questionName='" + questionname + "'";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	}
	
	public List<Answer> getAllAnswers(){
		List<Answer> list = new ArrayList<Answer>();
		
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT questionId,name,questionName,correctStatus FROM answer" ;
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				Answer answer = new Answer();
				Integer questionId = rs.getInt(1);
				String name = rs.getString(2);
				String questionName = rs.getString(3);
				Boolean correctStatus = rs.getBoolean(4);
				
				answer.setQuestionId(questionId);
				answer.setName(name);
				answer.setQuestionName(questionName);
				answer.setCorrectStatus(correctStatus);
				
				list.add(answer);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		
		return list;
	}
	
	public Answer getAnswerByName(String answerName) {
		List<Answer> list = this.getAllAnswers();
		for(Answer a : list) {
			if(a.getName().equals(answerName)) {
				return a;
			}
		}
	return null;
	}

	public Answer getAnswerNameAndCorrectStatus(String quizName,String imepitanja)
	{
		Answer odg = new Answer();
		try {
			
			String sql = "SELECT name,questionName,correctStatus from answer where questionName='" + imepitanja + "' and correctStatus=" + 1 + "";
		
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery(sql);
			
				odg.setName(rs.getString(0));
				odg.setQuestionName(rs.getString(1));
				odg.setCorrectStatus(rs.getBoolean(2));
			}
				
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return odg;
	}



	public void deleteAnswerByQuestionNameMe(List<Question> listaPitanja)
	{
		for(int i=0; i < listaPitanja.size();++i) {
			try {
				String sql1 = "delete from answer where questionName='" + listaPitanja.get(i).getName() + "'";
				PreparedStatement preparedStatement=connection.prepareStatement(sql1);
				preparedStatement.executeUpdate();
				}
		
				catch (SQLException e) {
					System.out.println(e.getMessage());
					}
		}
      }
}