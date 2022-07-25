package com.dao;

import java.util.List;

import javax.ejb.Remote;

import com.entity.TouristSpot;

@Remote
public interface TourismDao {
	
	List<TouristSpot> getTouristSpots();
	
	int insertTouristSpot(int id, String name, String place, String location,String start_time, String end_time);
	
}
