package com.shail.musicfinder.musicFinder.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.shail.musicfinder.musicFinder.exceptions.InvalidInputException;
import com.shail.musicfinder.musicFinder.objects.Track;
import com.shail.musicfinder.musicFinder.objects.User;
import com.shail.musicfinder.musicFinder.services.ResponseGenerationService;
import com.shail.musicfinder.musicFinder.services.SearchResponse;
import com.shail.musicfinder.musicFinder.services.UserResponse;

@RestController
public class UserSearchController {

	@Autowired
	private ResponseGenerationService responseGenService;
	@Autowired
	private SearchResponse searchService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public DeferredResult<ResponseEntity<?>> addUser(@RequestBody String search) throws IOException {
		System.out.println("searching  music");
		DeferredResult<ResponseEntity<?>> deferedResult = responseGenService.generateDefferedResponse();

		if (search == null) {
			responseGenService.sendFalseResponse(deferedResult, new InvalidInputException("Input user is Null"));
		} else {

			if (search == null || search.trim().equalsIgnoreCase("")) {
				responseGenService.sendFalseResponse(deferedResult,
						new InvalidInputException("search string  is Null"));

			} else {
				// save rule
				Future<List<Track>> searchResult =  searchService.findTrackDetails("Tum Hi Ho Bandhu");
				responseGenService.sendTrueResponse(deferedResult,  searchResult);
			}
		}

		return deferedResult;
	}
	
	
}
