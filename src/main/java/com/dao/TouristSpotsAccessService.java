package com.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.TouristSpot;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TouristSpotsAccessService implements TourismDao {

	@Override
	public List<TouristSpot> getTouristSpots() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("SELECT spot from TouristSpot spot", TouristSpot.class);
		@SuppressWarnings("unchecked")
		List<TouristSpot> list = (List<TouristSpot>) q.getResultList();
		em.getTransaction().commit();
		return list;
	}

	@Override
	public int insertTouristSpot(int id, String name, String place, String location, String start_time, String end_time) {
		TouristSpot ts=new TouristSpot();
		ts.setId(id);
		ts.setName(name);
		ts.setPlace(place);
		ts.setLocation(location);
		ts.setStart_time(start_time);
		ts.setEnd_time(end_time);
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(ts);
		em.getTransaction().commit();
		return 1;
	}

	

}
