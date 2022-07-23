package com.dao;

import java.util.List;

import javax.ejb.Remote;

import com.entity.TouristSpot;

@Remote
public interface TourismDao {
	
	List<TouristSpot> getTouristSpots();
	
}
