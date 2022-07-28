package com.controller;

import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.model.ContactUsRemote;

@ManagedBean(name = "contactus", eager = true)
@TransactionManagement(value=TransactionManagementType.BEAN)
public class ContactUsBean {
	@Size(min=4, message = "First name must have at least 4 characters")
	private String first_name;
	@Size(min= 2, message = "Last name must have at least 2 characters")
	private String last_name;
	@Size(min=1, message = "Email cannot be blank")
	@Email(message="Please provide a valid email address")
	private String email;
	@Size(min=1, message = "Message cannot be blank")
	private String message;
	private String res;
	
	@EJB(lookup = "java:global/SmartCity-Project/ContactUsImp")
	private ContactUsRemote cr;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRes() {
		return res;
	}
	
	public void setRes(String res) {
		this.res = res;
	}

	public ContactUsRemote getCr() {
		return cr;
	}

	public void setCr(ContactUsRemote cr) {
		this.cr = cr;
	}
	
	public void callRegister() {
		this.res=cr.register(first_name, last_name, email, message);
//		return "/contactus.jsf?faces-redirect=true";
	}
	
}
