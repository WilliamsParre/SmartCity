package com.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.TouristSpot;

@ManagedBean(eager = true)
@TransactionManagement(TransactionManagementType.BEAN)
public class TouristSpotBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String place;
	private String location;
	private String start_time;
	private String end_time;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public String addTouristSpot() {
		TouristSpot t=new TouristSpot();
		t.setName(this.name);
		t.setPlace(this.place);
		t.setLocation(this.location);
		t.setStart_time(this.start_time);
		t.setEnd_time(this.end_time);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		return "addTouristSpot.jsf?faces-redirect=true";
	}

	public List<TouristSpot> getTouristSpots() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em=emf.createEntityManager();
		Query query = em.createQuery("SELECT spots FROM TouristSpot spots");
		@SuppressWarnings("unchecked")
		List<TouristSpot> touristSpots= query.getResultList();
		em.close();
		emf.close();
		return touristSpots;
	}
	
	public String deleteTouristSpotsById(int id) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("DELETE FROM TouristSpot t where t.id = :pk");
		query.setParameter("pk", id);
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "manage.jsf?faces-redirect=true";
	}

}
