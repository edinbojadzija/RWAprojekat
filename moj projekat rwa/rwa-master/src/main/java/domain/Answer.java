package domain;

public class Answer {

    private String name; 
    private String questionName; 
    private boolean correctStatus;
    private int questionId;
    
    
    
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public boolean isCorrectStatus() {
		return correctStatus;
	}
	public void setCorrectStatus(boolean correctStatus) {
		this.correctStatus = correctStatus;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ime kviza: " + name + "\t Ime odgvora:" + questionName;
	}
    
    
}