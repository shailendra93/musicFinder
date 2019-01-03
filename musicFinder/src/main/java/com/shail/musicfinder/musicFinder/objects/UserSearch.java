package com.shail.musicfinder.musicFinder.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value="UserSearch")
public class UserSearch implements Serializable{
	
	@Id
	private String userId;
	private LinkedHashMap<String, List<Track>> searchCache;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LinkedHashMap<String, List<Track>> getSearchCache() {
		return searchCache;
	}
	public void setSearchCache(LinkedHashMap<String, List<Track>> searchCache) {
		this.searchCache = searchCache;
	}
	
	
	

}
