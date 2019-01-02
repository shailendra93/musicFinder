package com.shail.musicfinder.musicFinder.objects;

import java.io.Serializable;
import java.util.List;

public class Tracks implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Track> track;

	public List<Track> getTrack() {
		return track;
	}

	public void setTrack(List<Track> track) {
		this.track = track;
	}
	

}
