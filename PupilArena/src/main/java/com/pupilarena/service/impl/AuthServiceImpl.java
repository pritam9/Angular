package com.pupilarena.service.impl;

import java.util.List;

import com.pupilarena.dao.IAuthDao;
import com.pupilarena.model.Groups;
import com.pupilarena.model.Questions;
import com.pupilarena.model.Quizes;
import com.pupilarena.model.ScoreBoard;
import com.pupilarena.model.Users;
import com.pupilarena.service.IAuthService;

public class AuthServiceImpl implements IAuthService {

	private IAuthDao authDao;

	public IAuthDao getAuthDao() {
		return authDao;
	}

	public void setAuthDao(IAuthDao authDao) {
		this.authDao = authDao;
	}

	@Override
	public Users verifyCredentials(String username, String password) {
		return authDao.verifyCredentials(username,password);
	}

	@Override
	public Users saveUser(Users user) {
		return authDao.saveUser(user);
	}

	@Override
	public Users updateUser(Users user) {
		return authDao.updateUser(user);
	}

	@Override
	public Users getUser(String email_id) {
		return authDao.getUser(email_id);
	}

	@Override
	public List<Quizes> getQuizes() {
		return authDao.getQuizes();
	}

	@Override
	public List<Groups> getTeams() {
		return authDao.getTeams();
	}

	@Override
	public boolean joinGroup(String emailId,int groupId) {
		return authDao.joinGroup(emailId,groupId);
	}

	@Override
	public List<Groups> getMyTeams(String emailId) {
		return authDao.getMyTeams(emailId);
	}

	@Override
	public List<Users> getAllMembers() {
		return authDao.getAllMembers();
	}

	@Override
	public boolean createGroup(String studentId, String groupName, int capacity) {
		return authDao.createGroup(studentId,groupName,capacity);
	}

	@Override
	public boolean addMember(int groupId, String studentId) {
		return authDao.addMember(groupId,studentId);
	}

	@Override
	public boolean removeMember(int groupId, String studentId) {
		return authDao.removeMember(groupId,studentId);
	}

	@Override
	public boolean deleteGroup(int groupId) {
		return authDao.deleteGroup(groupId);
	}

	@Override
	public boolean registerForQuiz(int quizId, int groupId) {
		return authDao.registerForQuiz(quizId,groupId);
	}

	@Override
	public List<Questions> getQuizQuestions(int quizId) {
		return authDao.getQuizQuestions(quizId);
	}

	@Override
	public boolean submitAnswer(int quizId, int questionId, String answer, String emailId) {
		return authDao.submitAnswer(quizId,questionId,answer,emailId);
	}

	@Override
	public int getCorrectAnswersCount(String emailId, int quizId) {
		return authDao.getCorrectAnswersCount(emailId,quizId);
	}

	@Override
	public int getQuestionsCount(String emailId, int quizId) {
		return authDao.getQuestionsCount(emailId,quizId);
	}

	@Override
	public boolean updateLockedStatus(int quizId, boolean isLocked) {
		return authDao.updateLockedStatus(quizId,isLocked);
	}

	@Override
	public ScoreBoard getFullScorecard(int quizId) {
		return authDao.getFullScorecard(quizId);
	}

	@Override
	public boolean checkIfEnrolled(int quizId, int groupId) {
		return authDao.checkIfEnrolled(quizId,groupId);
	}
	
	
	
}
