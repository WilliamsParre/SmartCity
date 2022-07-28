package com.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import com.db.DataBase;
import com.entity.Users;
import com.utils.SessionUtils;


@ManagedBean(name="admin_login")
@SessionScoped
public class AdminLogin implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	@Size(min=1, message="Password cannot be blank")
	private String pwd;
	private String msg;
	@Size(min=1, message="Username cannot be blank")
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
		Users dbUser = null;
		boolean valid=false;
		try {
			ps = con.prepareStatement("select first_name,last_name,username,email  from admins where username = ? and password = ?");
			ps.setString(1, this.user);
			ps.setString(2, this.pwd);

			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				dbUser=new Users();
				dbUser.setFirst_name(rs.getString(1));
				dbUser.setLast_name(rs.getString(2));
				dbUser.setUsername(rs.getString(3));
				dbUser.setEmail(rs.getString(4));
				
				valid=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("user", dbUser);
			return "/admin/dashboard.jsf?faces-redirect=true";
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
