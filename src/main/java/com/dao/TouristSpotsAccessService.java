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
		Query q = em.createQuery("SELECT spot from TouristSpot spot", TouristSpot.class);
		@SuppressWarnings("unchecked")
		List<TouristSpot> list = (List<TouristSpot>) q.getResultList();
		em.close();
		emf.close();
		return list;
	}

	@Override
	public TouristSpot insertTouristSpot(TouristSpot touristSpot) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(touristSpot);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return touristSpot;
	}
	
	@Override
	public TouristSpot updateTouristSpot(TouristSpot touristSpot, int id) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query q=em.createQuery("UPDATE TouristSpot t SET t.name= :n, t.place= :p, t.location= :l, t.start_time= :s, t.end_time= :e WHERE t.id= :pk");
		q.setParameter("pk", id);
		q.setParameter("n", touristSpot.getName());
		q.setParameter("p", touristSpot.getPlace());
		q.setParameter("l", touristSpot.getLocation());
		q.setParameter("s", touristSpot.getStart_time());
		q.setParameter("e", touristSpot.getEnd_time());
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
		touristSpot.setId(id);
		return touristSpot;
	}


	@Override
	public int deleteTouristSpotById(int id) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query q=em.createQuery("DELETE FROM TouristSpot t where t.id= :pk");
		q.setParameter("pk", id);
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return 1;
	}	

}
