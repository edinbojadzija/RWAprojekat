package domain;


import java.util.ArrayList;
import java.util.List;

public class User {

	private String name; 
	private String password;
	private int role; 
	private List<String> quizList = new ArrayList<String>(); 
	
	public User()
	{
		
	}
	
	public User(String us,String pw,int ps)
	{
		this.name = us;
		this.password = pw;
		this.role = ps;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public List<String> getQuizList() {
		return quizList;
	}
	public void setQuizList(List<String> quizList) {
		this.quizList = quizList;
	}
	
	public String getRoles()
	{
		if(role == 1) return "Admin";
		else if (role == 2) return "Editor";
		else return "Quest";
	}
	
	public String getQuizListString() {
		String result = "";
		for(int i = 0; i < this.getQuizList().size(); ++i) {
			result += this.getQuizList().get(i);
			if(i != this.getQuizList().size() - 1) {
				result += ";";
			}
		}
		return result;
	}
	
	
}

