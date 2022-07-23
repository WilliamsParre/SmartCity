package com.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.db.DataBase;
import com.utils.SessionUtils;


@ManagedBean(name="admin_login")
@SessionScoped
public class AdminLogin implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
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
		Connection con = DataBase.getDBConnection();
		PreparedStatement ps;
		boolean valid=false;
		try {
			ps = con.prepareStatement("select username, password from admins where username = ? and password = ?");
			ps.setString(1, this.user);
			ps.setString(2, this.pwd);

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				valid=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", this.user);
			return "/admin/admin.jsf?faces-redirect=true";
		} else {
			return "/admin_login.jsf?faces-redirect=true";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/admin_login.jsf?faces-redirect=true";
	}
}
