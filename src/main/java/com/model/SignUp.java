package com.model;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Users;

@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class SignUp implements SignUpRemote{

	@Override
	public String register(String first_name, String last_name, String username, String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Users user = new Users();
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "login.jsf";
	}
	
	
	
}
