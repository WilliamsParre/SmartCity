package com.controller;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.entity.Users;

@ManagedBean
@SessionScoped
@TransactionManagement(TransactionManagementType.BEAN)
public class UpdateUser {
	private int id;
	@Size(min=4, message = "First name must have at least 4 characters")
	private String first_name;
	@Size(min= 2, message = "Last name must have at least 2 characters")
	private String last_name;
	@Size(min=4, message = "Username must have at least 4 characters")
	private String username;
	@Size(min=1, message = "Email cannot be blank")
	@Email(message="Please provide a valid email address")
	private String email;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		System.out.println(id);
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void attrListener(ActionEvent event){
		this.id = (int) event.getComponent().getAttributes().get("pk");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select u from Users u where u.id = :p");
		q.setParameter("p",this.id);
		Users user = (Users) q.getSingleResult();
		this.first_name=user.getFirst_name();
		this.last_name=user.getLast_name();
		this.username=user.getUsername();
		this.email=user.getEmail();
		em.close();
		emf.close();
	}
	
	public String updateUser() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNativeQuery("UPDATE users set first_name= :f_name, last_name= :l_name, username= :u_name, email= :e WHERE id= :pk");
		query.setParameter("f_name", this.first_name );
		query.setParameter("l_name", this.last_name);
		query.setParameter("u_name", this.username);
		query.setParameter("e", this.email);
		query.setParameter("pk", this.id);
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "/admin/users.jsf";
	}

}
