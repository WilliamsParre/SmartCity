package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.mysql.cj.jdbc.Blob;

@Entity
@Table(name = "tourist_spots")
public class TouristSpot implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String place;
	private String loaction;
	private String start_time;
	private String end_time;
//	private Blob image;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public String getLoaction() {
		return loaction;
	}
	
	public void setLoaction(String loaction) {
		this.loaction = loaction;
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
	
//	public Blob getImage() {
//		return image;
//	}
//	
//	public void setImage(Blob image) {
//		this.image = image;
//	}
	
}
