package domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String name; 
    private String quizName;
    private int points;
    private int time;
    private List<String> answerList = new ArrayList<String>();
    private int id;
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public List<String> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ime pitanja: " + name;
	}
	
    
    
}