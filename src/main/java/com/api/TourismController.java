package com.api;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.TourismDao;
import com.entity.TouristSpot;

import java.util.List;

@Path("/tourism")
public class TourismController {
	
	@EJB(lookup = "java:global/SmartCity-Project/TouristSpotsAccessService!com.dao.TourismDao")
	private TourismDao t;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TouristSpot> getTouristSpot() {
		return t.getTouristSpots();
	}
	
}
