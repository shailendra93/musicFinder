package com.shail.musicfinder.musicFinder.objects;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value="User")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Name;
	@Id
	String userId;
	String address;
	String location;
	String occupation;
	
	public User()
	{
		
	}
	
	public User(String name, String userId, String address, String location, String occupation) {
		super();
		Name = name;
		this.userId = userId;
		this.address = address;
		this.location = location;
		this.occupation = occupation;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "User [Name=" + Name + ", userId=" + userId + ", address=" + address + ", location=" + location
				+ ", occupation=" + occupation + "]";
	}
	
	
	
}
