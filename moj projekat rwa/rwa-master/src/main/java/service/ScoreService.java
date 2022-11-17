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
import domain.Score;

public class ScoreService {
	
	Connection connection;
	
	public ScoreService() {
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rwa", "root", "root");
		}
		catch (SQLException e) {
			 System.out.println("Error while connecting to the database");
		}
	}
	
	public void insertScore(Score score) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("insert into score values(?,?,?,?)");
			preparedStatement.setString(1,score.getUsername());
			preparedStatement.setInt(2,score.getTotalPoints());
			preparedStatement.setString(3,score.getUserEmail());
			preparedStatement.setString(4,score.getQuizName());
			
			preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
				 System.out.println("Error while connecting to the database while inserting");
			System.out.println(e.getMessage());
			}
	}
	
	public List<Score> getAllScoresOfUser(String userName){
					List<Score> list = new ArrayList<Score>();
					try {
					String sql1 = "SELECT username,totalPoints,userEmail,quizName FROM score s INNER JOIN quiz q ON q.name = s.quizName INNER JOIN user u ON u.name = q.ownerName WHERE u.name = ? " ;
					PreparedStatement preparedStatement=connection.prepareStatement(sql1);
					preparedStatement.setString(1,userName);
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						Score score = new Score();
						String username = rs.getString(1);
						Integer totalPoints = rs.getInt(2);
						String userEmail = rs.getString(3);
						String quizName = rs.getString(4);
						
						score.setUsername(username);
						score.setTotalPoints(totalPoints);
						score.setUserEmail(userEmail);
						score.setQuizName(quizName);
						list.add(score);
					}
				}
				catch (SQLException e) {
					System.out.println(e.getMessage());
					}
					
					return list;
	}
	
	public Score getBestPlayerFromDatabase() {
		Score score = null;
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT username,totalPoints,userEmail,quizName FROM score ORDER BY totalPoints DESC " ;
			ResultSet rs = statement.executeQuery(sql1);
			if(rs.next()) {
				score = new Score();
				String username = rs.getString(1);
				Integer totalPoints = rs.getInt(2);
				String userEmail = rs.getString(3);
				String quizName = rs.getString(4);
				
				score.setUsername(username);
				score.setTotalPoints(totalPoints);
				score.setUserEmail(userEmail);
				score.setQuizName(quizName);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		return score;
	}
	
	


}