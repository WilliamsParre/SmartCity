package com.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.TouristSpot;


@Stateless
@TransactionManagement
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

}
