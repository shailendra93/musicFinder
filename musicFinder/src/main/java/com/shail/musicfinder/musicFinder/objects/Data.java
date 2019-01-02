package com.shail.musicfinder.musicFinder.objects;

import java.io.Serializable;

public class Data implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tracks tracks;

	public Tracks getTracks() {
		return tracks;
	}

	public void setTracks(Tracks tracks) {
		this.tracks = tracks;
	}
	
	
}
