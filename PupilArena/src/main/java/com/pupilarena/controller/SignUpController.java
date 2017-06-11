package com.pupilarena.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.pupilarena.model.Groups;
import com.pupilarena.model.Quizes;
import com.pupilarena.model.Response;
import com.pupilarena.model.Users;
import com.pupilarena.service.IAuthService;

@Controller
@ControllerAdvice
@RequestMapping("/userService")
public class SignUpController {
	@Autowired
	private IAuthService authService;
	
	
	@RequestMapping(
			value="/addUser", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response authenticateLogin(@RequestBody Users user) {
		//String response;
		Users userAdded = this.authService.saveUser(user);
		//System.out.println("Method called!!"+username+" -- "+password+" -- "+userFound);
		Response response = new Response();
		if (userAdded!=null) {
			//Generate JWT
			System.out.println("admin login role is "+userAdded.getRole());
			final long iat = System.currentTimeMillis() / 1000l; // issued at claim 
			final long exp = iat + 18000L; // expires claim. In this case the token expires in 300 minutes

			final JWTSigner signer = new JWTSigner(HomeController.SECRET);
			final HashMap<String, Object> claims = new HashMap<String, Object>();
			claims.put("iss", HomeController.ISSUER);
			claims.put("exp", exp);
			claims.put("iat", iat);
			claims.put("user", userAdded);
			//claims.put("role", userAdded.getRole());
			final String jwt = signer.sign(claims);
			System.out.println(jwt);
			response.setStatus(Response.SUCCESS);
			response.setMessage("Registration Successful!!");
			response.setData(jwt);
		} else {
			response.setStatus(Response.FAILURE);
			response.setMessage("Registration Failed!! Existing Email Id!");
			response.setData("");
		}
		return response;
	}
	
	@RequestMapping(
			value="/getUserDetails", 
			method = RequestMethod.GET)
	public @ResponseBody Response getUserDetails(@RequestHeader(value="Authorization") String token) {
		//String response;
		Response response = new Response();
		System.out.println("Get User Details!!");
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Get User Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				Users user= authService.getUser((String)userMap.get("email"));
				/*Users user= new Users();
				user.setEmail((String)userMap.get("email"));
				user.setFirstname((String)userMap.get("firstname"));
				user.setMiddlename((String)userMap.get("middlename"));
				user.setLastname((String)userMap.get("lastname"));
				user.setRole((String)userMap.get("role"));
				user.setSchool_name((String)userMap.get("school_name"));
				user.setSex((String)userMap.get("sex"));
				user.setGpa((Double)userMap.get("gpa"));*/
				if(user!=null){
				response.setData(user);
				response.setMessage("Successfully retrieved details for the user!");
				response.setStatus(Response.SUCCESS);
				}else{
					response.setData("Could not retrieve information.");
					response.setMessage("Error Retriving Data!");
					response.setStatus(Response.FAILURE);
				}
				return response;
				
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
	
	//updateUserDetails
	@RequestMapping(
			value="/updateUserDetails", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateUserDetails(@RequestBody Users user, @RequestHeader(value="Authorization") String token) {
		//String response;
		Response response = new Response();
		System.out.println("Update User Details!!");
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Get User Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				if(authService.updateUser(user)!=null){
				response.setData(user);
				response.setMessage("Successfully updated details for the user!");
				response.setStatus(Response.SUCCESS);
				return response;
				}else{
					response.setData("Profile Could not be updated at this time.");
					response.setStatus(Response.FAILURE);
					response.setMessage("Error updating profile information. Try again later.");
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
	
	//getQuizDetails
	@RequestMapping(
			value="/getQuizDetails", 
			method = RequestMethod.GET)
	public @ResponseBody Response getQuizDetails(@RequestHeader(value="Authorization") String token) {
		//String response;
		Response response = new Response();
		System.out.println("Update User Details!!");
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Get Quiz Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				List<Quizes> quizes = authService.getQuizes();
				if(quizes!=null && quizes.size()>0){
					Map<String, Object> myMap = new HashMap<String, Object>();
					myMap.put("quizes", quizes);
					LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
					List<Groups> groups = authService.getMyTeams((String)userMap.get("email"));
					int groupId=-1;
					if(groups!=null && groups.size()>0)
						groupId=groups.get(0).getGroupId();
					myMap.put("groupId", groupId);
					myMap.put("role", (String)userMap.get("role"));
				response.setData(myMap);
				response.setMessage("Successfully retrieved details for the quizes!");
				response.setStatus(Response.SUCCESS);
				return response;
				}else{
					response.setData("Quizes Could not be retreived at this time.");
					response.setStatus(Response.FAILURE);
					response.setMessage("Error retriving quiz information. Try again later.");
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
	
	@RequestMapping(
			value="/getTeamDetails", 
			method = RequestMethod.GET)
	public @ResponseBody Response getTeamDetails(@RequestHeader(value="Authorization") String token) {
		//String response;
		Response response = new Response();
		System.out.println("Get Team Details!!");
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Get Team Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				List<Groups> quizes = authService.getTeams();
				
				if(quizes!=null && quizes.size()>0){
					System.out.println("Size of Group List is - "+quizes.size());
				response.setData(quizes);
				response.setMessage("Successfully retrieved details for the quizes!");
				response.setStatus(Response.SUCCESS);
				return response;
				}else{
					response.setData("Quizes Could not be retreived at this time.");
					response.setStatus(Response.FAILURE);
					response.setMessage("Error retriving quiz information. Try again later.");
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
	
	
	// Used by Leader to get Team details
	
	@RequestMapping(
			value="/getMyTeamDetails", 
			method = RequestMethod.GET)
	public @ResponseBody Response getMyTeamDetails(@RequestHeader(value="Authorization") String token) {
		//String response;
		Response response = new Response();
		System.out.println("Get My Team Details!!");
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Get My Team Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				//Users user= authService.getUser();
				List<Groups> quizes = authService.getMyTeams((String)userMap.get("email"));
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put("isMember", false);
				myMap.put("groups", new ArrayList<Groups>());
				myMap.put("myGroupId", -1);
				myMap.put("memberList", new ArrayList<Users>());
				if(quizes!=null && quizes.size()>0){
					System.out.println("Size of Group List is - "+quizes.size());
					List<Users> membersAll = authService.getAllMembers();
					myMap.put("isMember", true);
					myMap.put("groups", quizes);
					myMap.put("memberList", membersAll);
					myMap.put("myGroupId", quizes.get(0).getGroupId());
				response.setData(myMap);
				response.setMessage("Successfully retrieved details for the quizes!");
				response.setStatus(Response.SUCCESS);
				return response;
				}else{
					response.setData(myMap);
					response.setStatus(Response.FAILURE);
					response.setMessage("Error retriving quiz information. Try again later.");
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
	
	
	@RequestMapping(
			value="/joinTeam/{groupId}", 
			method = RequestMethod.POST)
	public @ResponseBody Response joinTable(@RequestHeader(value="Authorization") String token, @PathVariable("groupId") int groupId) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Get Team Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				authService.getUser((String)userMap.get("email"));
				List<Groups> quizes = authService.getTeams();
				
				if(authService.joinGroup(((String)userMap.get("email")),groupId)){
					//System.out.println("Size of Group List is - "+quizes.size());
				response.setData("Joining of the team successfull!!");
				response.setMessage("Successfully joined the Team!");
				response.setStatus(Response.SUCCESS);
				return response;
				}else{
					response.setData("Group cannot be joined at this time.");
					response.setStatus(Response.FAILURE);
					response.setMessage("You are already a part of one team. Cannot join multiple teams at this point.");
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
	
	//createTeam
	@RequestMapping(
			value="/createTeam", 
			method = RequestMethod.POST)
	public @ResponseBody Response createTeam(@RequestHeader(value="Authorization") String token, @RequestParam("groupName") String groupName, @RequestParam("capacity") int capacity) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Get Team Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				authService.getUser((String)userMap.get("email"));
				List<Groups> quizes = authService.getTeams();
				
				if(authService.createGroup(((String)userMap.get("email")),groupName,capacity)){
					//System.out.println("Size of Group List is - "+quizes.size());
				response.setData("Group created Successfullyl!!");
				response.setMessage("Group created Successfully!");
				response.setStatus(Response.SUCCESS);
				return response;
				}else{
					response.setData("Group cannot be created at this time.");
					response.setStatus(Response.FAILURE);
					response.setMessage("You are already a part of one team. Cannot join multiple teams at this point.");
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
	
	@RequestMapping(
			value="/addMember", 
			method = RequestMethod.POST)
	public @ResponseBody Response addMember(@RequestHeader(value="Authorization") String token, @RequestParam("groupId") int groupId,@RequestParam("studentId") String studentId) {
		Response response = new Response();
		if(token!=null && token.startsWith("jwt ")){
			String jwt_token=token.substring(4);
			System.out.println("Add member Details!! "+jwt_token);
			final JWTVerifier verifier = new JWTVerifier(HomeController.SECRET);

			Map<String, Object> claims;
			try {
				claims = verifier.verify(jwt_token);
				LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>)claims.get("user");
				String role = (String)userMap.get("role");
				if(role.equals("Leader")){
					if(authService.addMember(groupId,studentId)){
						//System.out.println("Size of Group List is - "+quizes.size());
						response.setData("Member added to team successfull!!");
						response.setMessage("Successfully joined the Team!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						response.setData("Group cannot be joined at this time.");
						response.setStatus(Response.FAILURE);
						response.setMessage("You are already a part of one team. Cannot join multiple teams at this point.");
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
	
	// Remove member from a group
	@RequestMapping(
			value="/removeMember", 
			method = RequestMethod.POST)
	public @ResponseBody Response removeMember(@RequestHeader(value="Authorization") String token, @RequestParam("groupId") int groupId,@RequestParam("studentId") String studentId) {
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
					if(authService.removeMember(groupId,studentId)){
						//System.out.println("Size of Group List is - "+quizes.size());
						System.out.println("....Successs Remove ...");
						response.setData("Member added to team successfull!!");
						response.setMessage("Successfully removed from the Team!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						response.setData("Group cannot be joined at this time.");
						response.setStatus(Response.FAILURE);
						response.setMessage("Operation Failed. Cannot remove user from group.");
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
			value="/deleteGroup", 
			method = RequestMethod.POST)
	public @ResponseBody Response deleteGroup(@RequestHeader(value="Authorization") String token, @RequestParam("groupId") int groupId) {
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
					if(authService.deleteGroup(groupId)){
						//System.out.println("Size of Group List is - "+quizes.size());
						System.out.println("....Successs Remove ...");
						response.setData("Member added to team successfull!!");
						response.setMessage("Successfully Delete group and removed all members as well!");
						response.setStatus(Response.SUCCESS);
						return response;
					}else{
						response.setData("Group cannot be deleted at this time.");
						response.setStatus(Response.FAILURE);
						response.setMessage("Operation Failed. Cannot delete group.");
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
	
	

	
}
