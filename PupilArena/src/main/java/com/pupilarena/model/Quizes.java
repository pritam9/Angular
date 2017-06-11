package com.pupilarena.model;

public class Quizes {
	private int quizId,duration;
	private String quizName,quizInfo,quizRules;
	private boolean locked;
	
	public Quizes(){
		
	}
	
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public String getQuizInfo() {
		return quizInfo;
	}
	public void setQuizInfo(String quizInfo) {
		this.quizInfo = quizInfo;
	}
	public String getQuizRules() {
		return quizRules;
	}
	public void setQuizRules(String quizRules) {
		this.quizRules = quizRules;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	

}
