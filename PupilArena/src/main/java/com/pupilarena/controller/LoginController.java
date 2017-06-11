package com.pupilarena.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWTSigner;
import com.pupilarena.model.Response;
import com.pupilarena.model.Users;
import com.pupilarena.service.IAuthService;

@Controller
@ControllerAdvice
@RequestMapping("/loginService")
public class LoginController {
	
	@Autowired
	private IAuthService authService;
	
	
	@RequestMapping(
			value="/auth", 
			method = RequestMethod.POST)
	public @ResponseBody Response authenticateLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		//String response;
		Users userFound = this.authService.verifyCredentials(username, password);
		System.out.println("Method called!!"+username+" -- "+password+" -- "+userFound);
		Response response = new Response();
		if (userFound!=null) {
			//Generate JWT
			System.out.println("admin login role is "+userFound.getRole());
			final long iat = System.currentTimeMillis() / 1000l; // issued at claim 
			final long exp = iat + 18000L; // expires claim. In this case the token expires in 300 minutes

			final JWTSigner signer = new JWTSigner(HomeController.SECRET);
			final HashMap<String, Object> claims = new HashMap<String, Object>();
			claims.put("iss", HomeController.ISSUER);
			claims.put("exp", exp);
			claims.put("iat", iat);
			claims.put("user", userFound);
			//claims.put("role", userFound.getRole());
			final String jwt = signer.sign(claims);
			System.out.println(jwt);
			response.setStatus(Response.SUCCESS);
			response.setMessage("Login Successful!!");
			response.setData(jwt);
		} else {
			response.setStatus(Response.FAILURE);
			response.setMessage("Login Failed!! Invalid Credentials!");
			response.setData("");
		}
		return response;
	}

}
