package com.dao;

import java.util.List;

import javax.ejb.Remote;

import com.entity.TouristSpot;

@Remote
public interface TourismDao {
	
	List<TouristSpot> getTouristSpots();
	
	TouristSpot insertTouristSpot(TouristSpot touristSpot);
	
	TouristSpot updateTouristSpot(TouristSpot touristSpot, int id);
	
	int deleteTouristSpotById(int id);
	
}
