package com.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import com.dao.LoginDAO;
import com.utils.SessionUtils;


@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	@Size(min=1, message="Password cannot be blank")
	private String pwd;
	private String msg;
	@Size(min=1, message="User name cannot be blank")
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		boolean valid = LoginDAO.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("user", user);
			return "/user/home.jsf?faces-redirect=true";
		} else {
//			this.msg = "User name or password doesn't exist chechk your credentials and try again";
			return "/login.jsf?faces-redirect=true";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/login.jsf?faces-redirect=true";
	}
}
