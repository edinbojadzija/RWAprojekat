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

import domain.Quiz;
import domain.User;

public class UserService {
	
	public Connection connection;
	
	public UserService() {
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rwa", "root", "root");
		}
		catch (SQLException e) {
			 System.out.println("Error while connecting to the database");
		}
	}
	
	public void insertUser(User user) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("insert into user values(?,?,?,?)");
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setInt(3, user.getRole() );
			String quizList = "";
			for(int i = 0; i < user.getQuizList().size(); ++i) {
				quizList += user.getQuizList().get(i);
				if(i != user.getQuizList().size() - 1) {
					quizList += ";";
				}
			}
			preparedStatement.setString(4, quizList);
			
	        preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
				 System.out.println("Error while connecting to the database while inserting");
			System.out.println(e.getMessage());
			}
	}
	
	public int getUsersCount() {
		int result = 0;
		
		try {
		Statement statement = connection.createStatement();
		String sql1 = "SELECT COUNT(*) FROM user" ;
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
	
	public User getUserByName(String username) {
			List<User> list = this.getAllUsers();
			for(User u : list) {
				if(u.getName().equals(username)) {
					return u;
				}
			}
		return null;
	}
	
	public User getUserByNameAndPw(String username,String password) {
		List<User> list = this.getAllUsers();
		for(User u : list) {
			if(u.getName().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
	return null;
}
	
	public User get(String userName)
	{
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT name,password,role,quizList FROM user where name ='" + userName + "'";
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				User user = new User();
				String name = rs.getString(1);
				String password = rs.getString(2);
				Integer role = rs.getInt(3);
				String quizzes = rs.getString(4);
				
				
				user.setName(name);
				user.setPassword(password);
				user.setRole(role);
				return user;
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllUsers(){
		List<User> list = new ArrayList<User>();
		
		try {
			Statement statement = connection.createStatement();
			String sql1 = "SELECT name,password,role,quizList FROM user" ;
			ResultSet rs = statement.executeQuery(sql1);
			while(rs.next()) {
				User user = new User();
				String name = rs.getString(1);
				String password = rs.getString(2);
				Integer role = rs.getInt(3);
				String quizzes = rs.getString(4);
				List<String> quizList = Arrays.asList(quizzes.split(";"));
				
				user.setName(name);
				user.setPassword(password);
				user.setRole(role);
				user.setQuizList(quizList);
				list.add(user);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		return list;
	}
	
	public void updateUser(String oldUsername, String username, String password, int role) {
		try {
			String sql1 = "update user set name=?, password=?,role=? where name=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setInt(3, role);
			preparedStatement.setString(4, oldUsername);
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}
	}
	
	public void deleteUser(String username) {
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("delete from user where name=?");
			preparedStatement.setString(1,username);
			preparedStatement.executeUpdate();	
			}
			catch (SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	public void refreshQuizList(User user, List<String> quizList) {
		try {
			String sql1 = "update user set quizList=? where name=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			String list = "";
			for(int i = 0; i < quizList.size(); ++i) {
				list += quizList.get(i);
				if(i != quizList.size() - 1) {
					list += ";";
				}
			}
			
			preparedStatement.setString(1, list);
			preparedStatement.setString(2, user.getName());
			preparedStatement.executeUpdate();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				}

	}
	

}