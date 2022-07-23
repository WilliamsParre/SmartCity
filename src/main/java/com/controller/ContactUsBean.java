package com.controller;

import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;

import com.model.ContactUsRemote;

@ManagedBean(name = "contactus", eager = true)
@TransactionManagement(value=TransactionManagementType.BEAN)
public class ContactUsBean {
	private String first_name;
	private String last_name;
	private String email;
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
	}
	
}
