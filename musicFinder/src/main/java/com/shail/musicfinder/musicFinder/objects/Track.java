package com.shail.musicfinder.musicFinder.objects;

import java.io.Serializable;
import java.util.List;

public class Track implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String artist;
	private String url;
	private String streamable;
	private String listeners;
	private String mbid;
	private List<Image> image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStreamable() {
		return streamable;
	}
	public void setStreamable(String streamable) {
		this.streamable = streamable;
	}
	public String getListeners() {
		return listeners;
	}
	public void setListeners(String listeners) {
		this.listeners = listeners;
	}
	public String getMbid() {
		return mbid;
	}
	public void setMbid(String mbid) {
		this.mbid = mbid;
	}
	public List<Image> getImage() {
		return image;
	}
	public void setImage(List<Image> image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", artist=" + artist + ", url=" + url + ", streamable="
				+ streamable + ", listeners=" + listeners + ", mbid=" + mbid + ", image=" + image + "]";
	}
	
	
}
