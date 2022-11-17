package domain;

public class Score {

    private String username; 
    private int totalPoints; 
    private String userEmail; 
    private String quizName; 
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
    
    
}