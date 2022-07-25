package com.controller;

import com.model.SignUpRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@ManagedBean(name="signup", eager = true)
public class SignUpController {
	@Size(min=4, message = "First name must have at least 4 characters")
	private String first_name;
	@Size(min= 2, message = "Last name must have at least 2 characters")
	private String last_name;
	@Size(min=4, message = "Username must have at least 4 characters")
	private String username;
	@Size(min=1, message = "Email cannot be blank")
	@Email(message="Please provide a valid email address")
	private String email;
	private String password1;
	private String password2;
	private String msg;
	
	@EJB(lookup = "java:global/SmartCity-Project/SignUp")
	private SignUpRemote sur;
		
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword1() {
		return password1;
	}
	
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	public String getPassword2() {
		return password2;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String register() {
		if(!((this.password1).equals(this.password2)))
			return "failure.jfs";
		try {
			sur.register(first_name, last_name, username, email, password1);
		} catch(Exception e) {
			System.out.println(e);
			return "failure.jfs";
		}
		return "/signup.jsf";
	}
	
	public String addUser() {
		if(!((this.password1).equals(this.password2)))
			return "failure.jfs";
		try {
			sur.register(first_name, last_name, username, email, password1);
		} catch(Exception e) {
			System.out.println(e);
			return "failure.jfs";
		}
		return "/admin/addUser.jsf";
	}
	
}
