package com.controller;

import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.Users;

@ManagedBean(name = "users", eager = true)
@ViewScoped
@TransactionManagement(TransactionManagementType.BEAN)
public class UsersBean {

	public String deleteUser(int pk) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("delete from Users u where u.id = :p");
		query.setParameter("p", pk);
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "/admin/users.jsf?faces-redirect=true";
	}
	
	public Users getUserById(String usr) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select u from Users u where u.username = :p");
		query.setParameter("p", usr);
		Users user = (Users) query.getResultList();
		em.close();
		emf.close();
		return user;
	}
	
	public List<Users> getUsersData() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select users from Users users");
		
		@SuppressWarnings("unchecked")
		List<Users> list= query.getResultList();
		em.close();
		emf.close();
		return list;
	}

}
