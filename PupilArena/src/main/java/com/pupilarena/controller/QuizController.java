package com.pupilarena.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.pupilarena.model.Questions;
import com.pupilarena.model.Response;
import com.pupilarena.model.ScoreBoard;
import com.pupilarena.service.IAuthService;

@Controller
@ControllerAdvice
@RequestMapping("/quizService")
public class QuizController {
	//quizRegistration
	@Autowired
	private IAuthService authService;

	@RequestMapping(
			value="/quizRegistration", 
			method = RequestMethod.POST)
	public @ResponseBody Response registerForQuiz(@RequestHeader(value="Authorization") String token, @RequestParam("groupId") int groupId, @RequestParam("quizId") int quizId) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Remove Member Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				String role = (String)userMap.get("role");
				System.out.println("....Try...");
				if(role.equals("Leader")){
					if(authService.registerForQuiz(quizId,groupId)){
						//System.out.println("Size of Group List is - "+quizes.size());
						//System.out.println("....Successs Remove ...");
						response.setData("Member added to team successfull!!");
						response.setMessage("Successfully registered for the quiz!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						response.setData("Group cannot be deleted at this time.");
						response.setStatus(Response.FAILURE);
						response.setMessage("Operation Failed. Your group already participated for the quiz.");
						return response;
					}
				}

			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWTVerifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			response.setData("Token Not Set!!");
			response.setMessage("Error Retrieving User/Supporter Details");
			response.setStatus(Response.AUTH_FAILURE);

			return response;
		}
		response.setData("You are not authorized to perform this action");
		response.setMessage("Error! You are not authorized to partcipate");
		response.setStatus(Response.AUTH_FAILURE);

		return response;

	}

	//getQuizQuestions
	@RequestMapping(
			value="/getQuizQuestions/{quizId}", 
			method = RequestMethod.GET)
	public @ResponseBody Response getQuizQuestions(@RequestHeader(value="Authorization") String token, @PathVariable("quizId") int quizId) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Remove Member Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				String role = (String)userMap.get("role");
				List<Questions> questions = authService.getQuizQuestions(quizId);
				if(questions.size()>0){
					//System.out.println("Size of Group List is - "+quizes.size());
					//System.out.println("....Successs Remove ...");
					response.setData(questions);
					response.setMessage("Successfully retrieved questions for the quiz!");
					response.setStatus(Response.SUCCESS);
					return response;
				}

			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWTVerifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			response.setData("Token Not Set!!");
			response.setMessage("Error Retrieving User/Supporter Details");
			response.setStatus(Response.AUTH_FAILURE);

			return response;
		}
		response.setData("You are not authorized to perform this action");
		response.setMessage("Error!");
		response.setStatus(Response.AUTH_FAILURE);

		return response;

	}


	//submitAnswer

	@RequestMapping(
			value="/submitAnswer", 
			method = RequestMethod.POST)
	public @ResponseBody Response submitAnswer(@RequestHeader(value="Authorization") String token, @RequestParam("quizId") int quizId,@RequestParam("questionId") int questionId, @RequestParam("answer") String answer) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Submit answer!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				String role = (String)userMap.get("role");
				String emailId = (String)userMap.get("email");
				System.out.println("....Try..."+role);
				if(role.equals("Leader")){
					if(authService.submitAnswer(quizId,questionId,answer,emailId)){
						//System.out.println("Size of Group List is - "+quizes.size());
						//System.out.println("....Successs Remove ...");
						response.setData("Answer Submitted!!");
						response.setMessage("Successfully submitted answer!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						response.setData("Group cannot be deleted at this time.");
						response.setStatus(Response.FAILURE);
						response.setMessage("Operation Failed. Your group has already answered this question.");
						return response;
					}
				}

			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWTVerifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			response.setData("Token Not Set!!");
			response.setMessage("Error Retrieving User/Supporter Details");
			response.setStatus(Response.AUTH_FAILURE);

			return response;
		}
		response.setData("You are not authorized to perform this action");
		response.setMessage("Error!");
		response.setStatus(Response.AUTH_FAILURE);

		return response;

	}
	@RequestMapping(
			value="/submitLastAnswer", 
			method = RequestMethod.POST)
	public @ResponseBody Response submitLastAnswer(@RequestHeader(value="Authorization") String token, @RequestParam("quizId") int quizId,@RequestParam("questionId") int questionId, @RequestParam("answer") String answer) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Submit answer!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				String role = (String)userMap.get("role");
				String emailId = (String)userMap.get("email");
				System.out.println("....Try..."+role);
				if(role.equals("Leader")){
					if(authService.submitAnswer(quizId,questionId,answer,emailId)){
						int correctAnswersCount = authService.getCorrectAnswersCount(emailId,quizId);
						int questionsCount = authService.getQuestionsCount(emailId,quizId);
						response.setData("Answer Submitted!!");
						response.setMessage("You have got "+correctAnswersCount+" out of "+questionsCount+" !!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						response.setData("Group cannot be deleted at this time.");
						response.setStatus(Response.FAILURE);
						response.setMessage("Operation Failed. Your group has already answered this question.");
						return response;
					}
				}

			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWTVerifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			response.setData("Token Not Set!!");
			response.setMessage("Error Retrieving User/Supporter Details");
			response.setStatus(Response.AUTH_FAILURE);

			return response;
		}
		response.setData("You are not authorized to perform this action");
		response.setMessage("Error!");
		response.setStatus(Response.AUTH_FAILURE);

		return response;

	}

	//lockQuiz for admin
	@RequestMapping(
			value="/lockQuiz", 
			method = RequestMethod.POST)
	public @ResponseBody Response lockQuiz(@RequestHeader(value="Authorization") String token, @RequestParam("lock") boolean isLocked,@RequestParam("quizId") int quizId) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Submit answer!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				String role = (String)userMap.get("role");
				String emailId = (String)userMap.get("email");
				System.out.println("....Try..."+role);
				if(role.equalsIgnoreCase("admin")){
					if(authService.updateLockedStatus(quizId,isLocked)){
						//System.out.println("Size of Group List is - "+quizes.size());
						//System.out.println("....Successs Remove ...");
						response.setData("Status Changed!!");
						response.setMessage("Successfully changed status!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						response.setData("Status cannot be changed at this time.");
						response.setStatus(Response.FAILURE);
						response.setMessage("Operation Failed. Status cannot be changed at this time.");
						return response;
					}
				}

			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWTVerifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			response.setData("Token Not Set!!");
			response.setMessage("Error Retrieving User/Supporter Details");
			response.setStatus(Response.AUTH_FAILURE);

			return response;
		}
		response.setData("You are not authorized to perform this action");
		response.setMessage("Error!");
		response.setStatus(Response.AUTH_FAILURE);

		return response;

	}

	//getScorecard
	@RequestMapping(
			value="/getScorecard/{quizId}", 
			method = RequestMethod.GET)
	public @ResponseBody Response getScorecard(@RequestHeader(value="Authorization") String token, @PathVariable("quizId") int quizId) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Submit answer!! "+jwt_token);
			LinkedHashMap<String, Object> userMap = getDetailsFromToken(jwt_token);
			if(userMap!=null){
				String role = (String)userMap.get("role");
				String emailId = (String)userMap.get("email");
				System.out.println("....Try..."+role);
				if(role.equalsIgnoreCase("admin")){
					ScoreBoard scoreBoard = authService.getFullScorecard(quizId);
					if(scoreBoard!=null){
						response.setData(scoreBoard);
						response.setMessage("Successfully retrieved data!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						return failureResponse();
					}
				}

			}else{
				return getAuthFailureResponse();
			}
		}
		return getAuthFailureResponse();
	}

		private Response failureResponse() {
			Response response = new Response();
			response.setData("Status cannot be changed at this time.");
			response.setStatus(Response.FAILURE);
			response.setMessage("Operation Failed. Status cannot be changed at this time.");
			return response;
	}

		private Response getAuthFailureResponse() {
			Response response = new Response();
			response.setData("You are not authorized to perform this action");
			response.setMessage("Error!");
			response.setStatus(Response.AUTH_FAILURE);
			return response;
		}

		protected static LinkedHashMap<String, Object> getDetailsFromToken(String token){
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(token);
				return (LinkedHashMap<String, Object>)claims.get("user");
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JWTVerifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//checkIfEnrolled
		@RequestMapping(
				value="/checkIfEnrolled", 
				method = RequestMethod.POST)
		public @ResponseBody Response checkIfEnrolled(@RequestHeader(value="Authorization") String token, @RequestParam("quizId") int quizId,@RequestParam("groupId") int groupId) {
			Response response = new Response();
			if(token!=null && token.startsWith("jwt ")){
				String jwt_token=token.substring(4);
				System.out.println("Submit answer!! "+jwt_token);
				LinkedHashMap<String, Object> userMap = getDetailsFromToken(jwt_token);
				if(userMap!=null){
					String role = (String)userMap.get("role");
					String emailId = (String)userMap.get("email");
					System.out.println("....Try..."+role);
					
						
						if(authService.checkIfEnrolled(quizId,groupId)){
							response.setData("Enrolled");
							response.setMessage("Is Enrolled!");
							response.setStatus(Response.SUCCESS);
							return response;
						}else{
							return failureResponse();
						}
					

				}else{
					return getAuthFailureResponse();
				}
			}
			return getAuthFailureResponse();
		}
		

	}
