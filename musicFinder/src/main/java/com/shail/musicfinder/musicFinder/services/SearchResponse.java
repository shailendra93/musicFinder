package com.shail.musicfinder.musicFinder.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shail.musicfinder.musicFinder.objects.Track;


@Service
@CacheConfig(cacheNames={"usersearch"})
public class SearchResponse {

	private static final ExecutorService threadpool = Executors.newCachedThreadPool();
	@Value("${lastfm.apikey}")
	private String apiKey;
	
	@Cacheable
	public Future<List<Track>> findTrackDetails(String search) throws IOException
	{
		return threadpool.submit(new Callable<List<Track>>() {
		
			@Override
			public List<Track> call() throws Exception {
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
		
		
		});
		
	}
	
	
	
	
	

}
