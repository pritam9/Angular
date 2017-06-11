package com.pupilarena.model;

import java.util.List;

public class ScoreBoard {
	private int quizId;
	private String quizName;
	private List<Scores> scores;
	public ScoreBoard() {
		
	}
	
	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public List<Scores> getScores() {
		return scores;
	}
	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}
	
}
