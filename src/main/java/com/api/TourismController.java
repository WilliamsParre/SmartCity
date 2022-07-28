package com.api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.TourismDao;
import com.entity.TouristSpot;

import java.util.List;

@Path("/tourism/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TourismController {
	
	@EJB(lookup = "java:global/SmartCity-Project/TouristSpotsAccessService!com.dao.TourismDao")
	private TourismDao t;
	
	public TourismDao getT() {
		return t;
	}

	public void setT(TourismDao t) {
		this.t = t;
	}

	@GET
	public List<TouristSpot> getTouristSpot() {
		return t.getTouristSpots();
	}
	
	@POST
	public TouristSpot addTouristSpot(TouristSpot touristSpot) {
		return	t.insertTouristSpot(touristSpot);
	}
	
	@Path("{id}")
	@PUT
	public TouristSpot saveOrUpdateTouristSpot(TouristSpot touristSpot,@PathParam("id") int id) {
		return t.updateTouristSpot(touristSpot, id);
	}
	
	@Path("{id}")
	@DELETE
	public int deleteTouristSpot(@PathParam("id") int id) {
		return t.deleteTouristSpotById(id);
	}
	
}
