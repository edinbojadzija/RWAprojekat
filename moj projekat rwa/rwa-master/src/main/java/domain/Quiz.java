package domain;

import java.util.List;

public class Quiz {

	private Integer id; 
	private String name; 
	private String description;
	private List<String> questionList;
	private boolean activeStatus;
	private String ownerName;
	private String imagePath;
	private List<String> scoreList;
	private List<String> questionListId;
	
	
	
	public List<String> getQuestionListId() {
		return questionListId;
	}
	public void setQuestionListId(List<String> questionListId) {
		this.questionListId = questionListId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getQuestionList() {
		return questionList;
	}
	
	
	public void setQuestionList(List<String> questionList) {
		this.questionList = questionList;
	}
	public boolean isActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public List<String> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<String> scoreList) {
		this.scoreList = scoreList;
	}
	
	public boolean getActiveStatus()
	{
		return this.activeStatus;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
		}
	
	
	
	
}