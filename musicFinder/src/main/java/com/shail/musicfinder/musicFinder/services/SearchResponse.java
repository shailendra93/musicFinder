package com.shail.musicfinder.musicFinder.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNode.JsonNode;
import com.shail.musicfinder.musicFinder.objects.Track;
import com.shail.musicfinder.musicFinder.objects.Tracks;





@Service
public class SearchResponse {

	@Value("${lastfm.apikey}")
	private String apiKey= "3bfebc9ac2e9950b11b93427506fb470";
	
	@Cacheable("usersearch")
	public  List<Track> findTrackDetails(String search) throws IOException
	{
		System.out.println("inside track " +apiKey +" "+search);
		RestTemplate restTemplate  = new RestTemplate();
		
		String lastfmTrack = "http://ws.audioscrobbler.com/2.0/?method=track.search&track="+search+"&api_key="+apiKey+"&format=json";
	
		ResponseEntity<String> response = restTemplate.getForEntity(lastfmTrack , String.class);
		ObjectMapper mapper = new ObjectMapper();
		com.fasterxml.jackson.databind.JsonNode root = mapper.readTree(response.getBody());
		com.fasterxml.jackson.databind.JsonNode name = root.get("results").get("trackmatches").get("track");
		
		System.out.println(name);
		List<Track> tracks = new ArrayList<>();
	
		Iterator<com.fasterxml.jackson.databind.JsonNode> namenode = name.elements();
		while(namenode.hasNext())
		{
			Track trak = new Track();
			com.fasterxml.jackson.databind.JsonNode track = namenode.next();
			trak.setName(track.get("name").toString());
			trak.setArtist(track.get("artist").toString());
			trak.setUrl(track.get("url").toString());
			trak.setStreamable(track.get("streamable").toString());
			trak.setListeners(track.get("listeners").toString());
			trak.setMbid(track.get("mbid").toString());
			tracks.add(trak);
			
		}
		
	     
		 return tracks;
		
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub

		
		SearchResponse sr = new SearchResponse();
		List<Track> tk = sr.findTrackDetails("tum na ho");
		for(Track tr: tk)
		{
			System.out.println(tr);
		}
		 tk = sr.findTrackDetails("tum hi ho");
		for(Track tr: tk)
		{
			System.out.println(tr);
		}
		 tk = sr.findTrackDetails("tum hi ho");
			for(Track tr: tk)
			{
				System.out.println(tr);
			}
		
	}

}
