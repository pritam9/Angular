package com.pupilarena.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.pupilarena.model.Response;
import com.pupilarena.model.Users;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	protected static final String ISSUER = "https://pupilarena.com/";
	protected static final String SECRET = "{{a secret used for signing}}";
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("title", "Pupil Arena : Login" );
		return "home";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute("title", "Pupil Arena : Sign Up" );
		return "signUp";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET )
	public String userHome(Model model, HttpServletRequest request) {
		String token = request.getParameter("token");
		String role = verifyToken(token);
		if(role!=null){
			if(role.equals("Admin")){
				model.addAttribute("title", "Pupil Arena : Home Page" );
				return "adminHome";
			}else{
				model.addAttribute("title", "Pupil Arena : Home Page" );
				return "userHome";
			}
		}else{
			model.addAttribute("title", "Pupil Arena : Login Page" );
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String showErrorPage(Model model) {
		model.addAttribute("title", "Pupil Arena : Error Page" );
		return "error";
	}
	
	@RequestMapping(value = "/scorecard", method = RequestMethod.GET )
	public String scorecard(Model model, HttpServletRequest request) {
		String token = request.getParameter("token");
		String role = verifyToken(token);
		if(role!=null){
			if(role.equals("Admin")){
				model.addAttribute("title", "Pupil Arena : Home Page" );
				return "adminScorecard";
			}
		}
		model.addAttribute("title", "Pupil Arena : Error Page" );
		return "error";
		
		
	}
	
	private String verifyToken(String token) {
		final JWTVerifier verifier = new JWTVerifier(SECRET);

		Map<String, Object> claims;
		try {
			claims = verifier.verify(token);
			LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
			return (String)userMap.get("role");
			
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

	@RequestMapping(value = "/quizes", method = RequestMethod.GET)
	public String userQuizes(Model model) {
		model.addAttribute("title", "Pupil Arena : Quizes" );
		return "quizes";
	}
	@RequestMapping(value = "/admin_quizes", method = RequestMethod.GET)
	public String adminQuizes(Model model) {
		model.addAttribute("title", "Pupil Arena : Quizes" );
		return "adminQuiz";
	}
	
	@RequestMapping(value = "/join_team", method = RequestMethod.GET)
	public String joinTeam(Model model) {
		model.addAttribute("title", "Pupil Arena : Join Team" );
		return "jointeam";
	}
	
	@RequestMapping(value = "/create_team", method = RequestMethod.GET)
	public String createTeam(Model model) {
		model.addAttribute("title", "Pupil Arena : Create Team" );
		return "createteam";
	}
	
	//ongoingQuiz
	@RequestMapping(value = "/ongoingQuiz", method = RequestMethod.GET)
	public String startQuizz(Model model) {
		model.addAttribute("title", "Pupil Arena : Quiz" );
		return "ongoingQuiz";
	}
	
	
}
