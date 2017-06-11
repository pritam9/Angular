package com.pupilarena.model;

public class Users {
	private String firstname,middlename,lastname,email,password,school_name,sex,role;
	private double gpa;
	
	public Users(){
		
	}
	
	public Users(String firstname, String middlename, String lastname, String email, String password, String school_name, String sex,
			String role, double gpa) {
		super();
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.school_name = school_name;
		this.sex = sex;
		this.role = role;
		this.gpa = gpa;
	}
	
	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
