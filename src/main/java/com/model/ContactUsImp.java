package com.model;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.entity.ContactUs;

@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class ContactUsImp implements ContactUsRemote{

	@Override
	public String register(String first_name, String last_name, String email, String message) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em=emf.createEntityManager();
		ContactUs contactus=new ContactUs();
		contactus.setFirst_name(first_name);
		contactus.setLast_name(last_name);
		contactus.setEmail(email);
		contactus.setMessage(message);
		em.getTransaction().begin();
		em.persist(contactus);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "Message sent successfully!";
	}
	
}
