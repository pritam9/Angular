package com.pupilarena.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



import com.pupilarena.dao.IAuthDao;
import com.pupilarena.model.Groups;
import com.pupilarena.model.Questions;
import com.pupilarena.model.Quizes;
import com.pupilarena.model.ScoreBoard;
import com.pupilarena.model.Scores;
import com.pupilarena.model.Users;

public class AuthDao implements IAuthDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Users verifyCredentials(String username, String password) {
		// TODO Auto-generated method stub
		String query = "select firstname, middlename, lastname, email_id, school_name,sex,role, gpa from users where email_id = ? and password = ?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			Users user = null;
			if (resultSet.next())
			{	
				user=new Users();
				user.setFirstname(resultSet.getString(1));
				user.setMiddlename(resultSet.getString(2));
				user.setLastname(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setSchool_name(resultSet.getString(5));
				user.setSex(resultSet.getString(6));
				user.setRole(resultSet.getString(7));
				user.setGpa(resultSet.getDouble(8));

			}
			return user;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Users saveUser(Users user) {
		String query = "insert into users(firstname, middlename, lastname, email_id, password, school_name, sex, role, gpa) values(?,?,?,?,?,?,?,?,?)";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, user.getFirstname());
			pstmt.setString(2, user.getMiddlename());
			pstmt.setString(3, user.getLastname());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword());
			pstmt.setString(6, user.getSchool_name());
			pstmt.setString(7, user.getSex());
			pstmt.setString(8, user.getRole());
			pstmt.setDouble(9, 0.0);
			pstmt.executeUpdate();
			return user;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Users updateUser(Users user) {
		String query = "update users set firstname=?, middlename=?, lastname=?, school_name=?, sex=?, role=?, gpa=? where email_id=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, user.getFirstname());
			pstmt.setString(2, user.getMiddlename());
			pstmt.setString(3, user.getLastname());
			pstmt.setString(4, user.getSchool_name());
			pstmt.setString(5, user.getSex());
			pstmt.setString(6, user.getRole());
			pstmt.setDouble(7, user.getGpa());
			pstmt.setString(8, user.getEmail());
			pstmt.executeUpdate();
			return user;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Users getUser(String email_id) {
		String query = "select firstname, middlename, lastname, email_id, school_name,sex,role, gpa from users where email_id = ?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, email_id);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			Users user = null;
			if (resultSet.next())
			{	
				user=new Users();
				user.setFirstname(resultSet.getString(1));
				user.setMiddlename(resultSet.getString(2));
				user.setLastname(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setSchool_name(resultSet.getString(5));
				user.setSex(resultSet.getString(6));
				user.setRole(resultSet.getString(7));
				user.setGpa(resultSet.getDouble(8));

			}
			return user;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Quizes> getQuizes() {
		String query = "select * from quiztable";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			//pstmt.setString(1, email_id);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			List<Quizes> quizes = new ArrayList<Quizes>();
			while (resultSet.next())
			{	
				Quizes quiz=new Quizes();
				quiz.setQuizId(resultSet.getInt(1));
				quiz.setQuizName(resultSet.getString(2));
				quiz.setQuizInfo(resultSet.getString(3));
				quiz.setQuizRules(resultSet.getString(4));
				quiz.setDuration(resultSet.getInt(5));
				quiz.setLocked(resultSet.getBoolean(6));
				quizes.add(quiz);

			}
			return quizes;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Groups> getTeams() {
		String query = "select G.groupID,G.groupName,G.capacity,G.studentID,U.firstname,U.middlename,U.lastname,U.role,U.sex,U.school_name,U.gpa from groups G JOIN users U ON G.studentID = U.email_id group by G.groupId,G.GroupName,G.studentID,G.capacity";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			//pstmt.setString(1, email_id);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			List<Groups> qruops = new ArrayList<Groups>();
			int prev_grp_id = -1;
			Groups grp=null;
			List<Users> members = null;
			while (resultSet.next())
			{	
				int cur_group_id=resultSet.getInt(1);
				System.out.println("-- "+cur_group_id+" prev - "+prev_grp_id);
				if(prev_grp_id==cur_group_id){
					Users member = new Users();
					member.setEmail(resultSet.getString(4));
					member.setFirstname(resultSet.getString(5));
					member.setLastname(resultSet.getString(7));
					member.setMiddlename(resultSet.getString(6));
					member.setRole(resultSet.getString(8));
					member.setSex(resultSet.getString(9));
					member.setSchool_name(resultSet.getString(10));
					member.setGpa(resultSet.getDouble(11));
					members.add(member);

				}else{
					if(grp!=null){
						grp.setMembers(members);
						qruops.add(grp);
					}
					grp=new Groups();
					grp.setGroupId(cur_group_id);
					grp.setGroupName(resultSet.getString(2));
					grp.setCapacity(resultSet.getInt(3));
					members=new ArrayList<Users>();
					Users member = new Users();
					member.setEmail(resultSet.getString(4));
					member.setFirstname(resultSet.getString(5));
					member.setLastname(resultSet.getString(7));
					member.setMiddlename(resultSet.getString(6));
					member.setRole(resultSet.getString(8));
					member.setSex(resultSet.getString(9));
					member.setSchool_name(resultSet.getString(10));
					member.setGpa(resultSet.getDouble(11));
					members.add(member);	
					prev_grp_id=cur_group_id;
				}
				//qruops.add(grp);

			}
			grp.setMembers(members);
			qruops.add(grp);
			return qruops;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean joinGroup(String emailId, int groupId) {
		boolean isSubscribed = checkIfSubscribed(emailId);
		if(!isSubscribed){
			Groups grp = getGroupDetails(groupId);
			String query = "insert into groups(groupID, studentID, groupName, capacity) values(?,?,?,?)";
			try{
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setInt(1, groupId);
				pstmt.setString(2, emailId);
				pstmt.setString(3, grp.getGroupName());
				pstmt.setInt(4, grp.getCapacity());
				pstmt.executeUpdate();
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	private Groups getGroupDetails(int groupId) {
		String query = "select * from groups where groupID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, groupId);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			List<Quizes> quizes = new ArrayList<Quizes>();
			if (resultSet.next())
			{	
				Groups grp=new Groups();
				grp.setGroupId(resultSet.getInt(1));
				grp.setGroupName(resultSet.getString("groupName"));
				grp.setCapacity(resultSet.getInt(4));
				return grp;

			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private boolean checkIfSubscribed(String emailId) {
		String query = "select * from groups where studentID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, emailId);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			//List<Quizes> quizes = new ArrayList<Quizes>();
			if (resultSet.next())
				return true;

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public List<Groups> getMyTeams(String emailId) {
		int groupId = getMyGroupId(emailId);
		if(groupId!=-1){
			List<Groups> groupDetails = new ArrayList<Groups>();
			String query = "select G.groupID,G.groupName,G.capacity,G.studentID,U.firstname,U.middlename,U.lastname,U.role,U.sex,U.school_name,U.gpa from groups G JOIN users U ON G.studentID = U.email_id AND G.groupID= ? group by G.groupId,G.GroupName,G.studentID,G.capacity";
			try{
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setInt(1, groupId);
				//pstmt.setString(2, password);
				ResultSet resultSet = pstmt.executeQuery();
				List<Groups> qruops = new ArrayList<Groups>();
				int prev_grp_id = -1;
				Groups grp=null;
				List<Users> members = null;
				while (resultSet.next())
				{	
					int cur_group_id=resultSet.getInt(1);
					System.out.println("-- "+cur_group_id+" prev - "+prev_grp_id);
					if(prev_grp_id==cur_group_id){
						Users member = new Users();
						member.setEmail(resultSet.getString(4));
						member.setFirstname(resultSet.getString(5));
						member.setLastname(resultSet.getString(7));
						member.setMiddlename(resultSet.getString(6));
						member.setRole(resultSet.getString(8));
						member.setSex(resultSet.getString(9));
						member.setSchool_name(resultSet.getString(10));
						member.setGpa(resultSet.getDouble(11));
						members.add(member);

					}else{
						if(grp!=null){
							grp.setMembers(members);
							qruops.add(grp);
						}
						grp=new Groups();
						grp.setGroupId(cur_group_id);
						grp.setGroupName(resultSet.getString(2));
						grp.setCapacity(resultSet.getInt(3));
						members=new ArrayList<Users>();
						Users member = new Users();
						member.setEmail(resultSet.getString(4));
						member.setFirstname(resultSet.getString(5));
						member.setLastname(resultSet.getString(7));
						member.setMiddlename(resultSet.getString(6));
						member.setRole(resultSet.getString(8));
						member.setSex(resultSet.getString(9));
						member.setSchool_name(resultSet.getString(10));
						member.setGpa(resultSet.getDouble(11));
						members.add(member);	
						prev_grp_id=cur_group_id;
					}
					//qruops.add(grp);

				}
				grp.setMembers(members);
				qruops.add(grp);
				return qruops;
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}

		}
		return null;
	}

	private int getMyGroupId(String emailId) {
		String query = "select * from groups where studentID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, emailId);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			//List<Quizes> quizes = new ArrayList<Quizes>();
			if (resultSet.next())
				return resultSet.getInt(1);

		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	@Override
	public List<Users> getAllMembers() {
		String query = "select firstname, middlename, lastname, email_id, school_name,sex,role, gpa from users where role = ?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, "Member");
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			List<Users> users= new ArrayList<Users>();
			while (resultSet.next())
			{	
				Users user=new Users();
				user.setFirstname(resultSet.getString(1));
				user.setMiddlename(resultSet.getString(2));
				user.setLastname(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setSchool_name(resultSet.getString(5));
				user.setSex(resultSet.getString(6));
				user.setRole(resultSet.getString(7));
				user.setGpa(resultSet.getDouble(8));
				users.add(user);
			}
			return users;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean createGroup(String studentId, String groupName, int capacity) {
		String query = "insert into groups(groupName, studentID, capacity) values(?,?,?)";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, groupName);
			pstmt.setString(2, studentId);
			pstmt.setInt(3, capacity);
			pstmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addMember(int groupId, String studentId) {
		if(!checkIfSubscribed(studentId)){
			Groups group = getGroupDetails(groupId);
			String query = "insert into groups(groupID,groupName, studentID, capacity) values(?,?,?,?)";
			try{
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setInt(1, groupId);
				pstmt.setString(2, group.getGroupName());
				pstmt.setString(3, studentId);
				pstmt.setInt(4, group.getCapacity());
				pstmt.executeUpdate();
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean removeMember(int groupId, String studentId) {
		String query = "delete from groups where groupID=? and studentID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, groupId);
			pstmt.setString(2, studentId);
			//pstmt.setString(3, studentId);
			//pstmt.setInt(4, group.getCapacity());
			pstmt.executeUpdate();
			System.out.println("Deleted");
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		//return false;
	}

	@Override
	public boolean deleteGroup(int groupId) {
		String query = "delete from groups where groupID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, groupId);
			//pstmt.setString(2, studentId);
			//pstmt.setString(3, studentId);
			//pstmt.setInt(4, group.getCapacity());
			pstmt.executeUpdate();
			//System.out.println("Deleted");
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean registerForQuiz(int quizId, int groupId) {
		if(!checkIfParticipated(quizId,groupId)){
			//Groups group = getGroupDetails(groupId);
			String query = "insert into quizparticipation(quizId,groupID,quizScore,quizTotal) values(?,?,?,?)";
			try{
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setInt(2, groupId);
				pstmt.setInt(1, quizId);
				pstmt.setInt(3, -1);
				pstmt.setInt(4, -1);
				pstmt.executeUpdate();
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	private boolean checkIfParticipated(int quizId, int groupId) {
		String query = "select * from quizparticipation where quizId=? and groupID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, quizId);
			pstmt.setInt(2, groupId);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			//List<Quizes> quizes = new ArrayList<Quizes>();
			if (resultSet.next())
				return true;

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public List<Questions> getQuizQuestions(int quizId) {
		String query = "select A.QuestionId, B.questionText,B.optionA,B.optionB,B.optionC,B.optionD,B.optionE,B.imgUrl from quiz_question_mapping A JOIN questions B ON A.questionId = B.questionId AND A.quizId = ?;";
		List<Questions> questions= new ArrayList<Questions>();
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, quizId);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next())
			{	
				Questions question=new Questions();
				question.setQuestionId(resultSet.getInt(1));
				question.setQuestionText(resultSet.getString(2));
				question.setOptionA(resultSet.getString(3));
				question.setOptionB(resultSet.getString(4));
				question.setOptionC(resultSet.getString(5));
				question.setOptionD(resultSet.getString(6));
				question.setOptionE(resultSet.getString(7));
				question.setImgUrl(resultSet.getString(8));
				questions.add(question);
			}
			return questions;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean submitAnswer(int quizId, int questionId, String answer, String emailId) {
		int groupId=getMyGroupId(emailId);
		String correctAnswer = getCorrectAnswer(questionId);
		String query = "insert into quiz_answers(QuizId,QuestionId,GroupID,StudentsAnswer,isCorrect) values(?,?,?,?,?)";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(2, questionId);
			pstmt.setInt(1, quizId);
			pstmt.setInt(3, groupId);
			pstmt.setString(4, answer);
			pstmt.setBoolean(5, answer.equalsIgnoreCase(correctAnswer));
			pstmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}

	private String getCorrectAnswer(int questionId) {
		String query = "select * from questions where questionId=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, questionId);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			//List<Quizes> quizes = new ArrayList<Quizes>();
			if (resultSet.next())
				return resultSet.getString("answer");

		}catch(SQLException e){
			e.printStackTrace();
			return "NA";
		}
		return "NA";
	}

	@Override
	public int getCorrectAnswersCount(String emailId, int quizId) {
		int groupId=getMyGroupId(emailId);
		int score=0;
		String query = "select count(*) from quiz_answers where quizId=? and groupID=? and isCorrect=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, quizId);
			pstmt.setInt(2, groupId);
			pstmt.setBoolean(3, true);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			//List<Quizes> quizes = new ArrayList<Quizes>();
			if (resultSet.next())
				score = resultSet.getInt(1);

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		updateGroupScore(quizId,groupId,score);
		return score;
	}

	private void updateGroupScore(int quizId, int groupId, int score) {
		String query = "update quizparticipation set quizScore=? where quizId=? and groupID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, score);
			pstmt.setInt(2, quizId);
			pstmt.setInt(3, groupId);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public int getQuestionsCount(String emailId, int quizId) {
		int groupId=getMyGroupId(emailId);
		int qCount= getQuizQuestions(quizId).size();
		updateTotalScore(quizId,groupId,qCount);
		
		return qCount;
	}

	private void updateTotalScore(int quizId, int groupId,int qCount) {
		String query = "update quizparticipation set quizTotal=? where quizId=? and groupID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, qCount);
			pstmt.setInt(2, quizId);
			pstmt.setInt(3, groupId);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean updateLockedStatus(int quizId, boolean isLocked) {
		String query = "update quiztable set isLocked=? where quizId=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setBoolean(1, isLocked);
			pstmt.setInt(2, quizId);			
			pstmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ScoreBoard getFullScorecard(int quizId) {
		//select A.QuizId, A.QuizName, B.groupId,C.groupName,B.quizScore,B.quizTotal from quiztable A JOIN quizparticipation B ON A.quizId = B.quizId JOIN (select distinct(groupID) as gId, groupName from groups ) C ON B.groupID=C.gId where A.quizId = ?;
		String query = "select A.QuizId, A.QuizName, B.groupId,C.groupName,B.quizScore,B.quizTotal from quiztable A JOIN quizparticipation B ON A.quizId = B.quizId JOIN (select distinct(groupID) as gId, groupName from groups ) C ON B.groupID=C.gId where A.quizId = ?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, quizId);
			ResultSet resultSet = pstmt.executeQuery();
			int start = -1;
			ScoreBoard scoreboard=null;
			List<Scores> scoreSheet = null;
			while (resultSet.next())
			{	
				if(start==-1){
					scoreboard=new ScoreBoard();
					scoreSheet=new ArrayList<Scores>();
					scoreboard.setQuizId(resultSet.getInt(1));
					scoreboard.setQuizName(resultSet.getString(2));
					start=0;
				}
				Scores score=new Scores();
				score.setGroupId(resultSet.getInt(3));
				score.setGroupName(resultSet.getString(4));
				score.setScore(resultSet.getInt(5));
				score.setTotal(resultSet.getInt(6));
				scoreSheet.add(score);
				scoreboard.setScores(scoreSheet);
			}
			return scoreboard;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkIfEnrolled(int quizId, int groupId) {
		String query = "select * from quizparticipation where quizId=? and groupID=?";
		try{
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, quizId);
			pstmt.setInt(2, groupId);
			//pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			//List<Quizes> quizes = new ArrayList<Quizes>();
			if (resultSet.next())
				return true;

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return false;
	}



}
