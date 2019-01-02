package com.shail.musicfinder.musicFinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.shail.musicfinder.musicFinder.exceptions.InvalidInputException;
import com.shail.musicfinder.musicFinder.objects.User;
import com.shail.musicfinder.musicFinder.services.ResponseGenerationService;
import com.shail.musicfinder.musicFinder.services.UserResponse;
import java.util.concurrent.*;

@RestController
public class UserController {

	@Autowired
	private ResponseGenerationService responseGenService;
	@Autowired
	private UserResponse userService;
	
	//adding new device
	
	//@Loggable(tag = "DeviceEngine - Add Device(/device/api/devices)")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<?>> addUser(@RequestBody User user) {
		System.out.println("adding User");
		DeferredResult<ResponseEntity<?>> deferedResult = responseGenService.generateDefferedResponse();

		if (user == null) {
			responseGenService.sendFalseResponse(deferedResult, new InvalidInputException("Input user is Null"));
		} else {

			if (user.getName() == null || user.getName().trim().equalsIgnoreCase("")) {
				responseGenService.sendFalseResponse(deferedResult,
						new InvalidInputException("Name for  User is Null"));

			} else {
				// save rule
				Future<String> addUserFuture =  userService.addUser(user);
				responseGenService.sendTrueResponse(deferedResult,  addUserFuture);
			}
		}

		return deferedResult;
	}
	
	//list of all devices
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public DeferredResult<ResponseEntity<?>> getAllUsers(
			@RequestParam(value = "limit", defaultValue = "50") String limitString,
			@RequestParam(value = "offset", defaultValue = "0") String offsetString) {

		DeferredResult<ResponseEntity<?>> deferedResult = responseGenService.generateDefferedResponse();
		try {
			Future<List<User>> devicesListFuture = (Future<List<User>>) userService.getAllUser(limitString, offsetString);
			responseGenService.sendTrueResponse(deferedResult, (java.util.concurrent.Future<?>) devicesListFuture);
		} catch (Exception e) {
			e.printStackTrace();
			responseGenService.sendFalseResponse(deferedResult, e);
		}
		return deferedResult;
	}
	
	//@RequestMapping(value = "/devices/{deviceid}", method = RequestMethod.DELETE)
	@DeleteMapping("/user/{userid}")
	public DeferredResult<ResponseEntity<?>> deleteUser(@PathVariable("userid") String userId) {


		DeferredResult<ResponseEntity<?>> deferedResult = responseGenService.generateDefferedResponse();
		try {
			Future<String> deleteUserFuture = userService.deleteUser(userId);
			responseGenService.sendTrueResponse(deferedResult, deleteUserFuture);
		} catch (Exception e) {
			e.printStackTrace();
			responseGenService.sendFalseResponse(deferedResult, e);
		}
		return deferedResult;
	}
	
	
//	@Loggable(tag = "RuleEngine - Update Rule(/pe/api/rules)")
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.PUT)
	public DeferredResult<ResponseEntity<?>> updateUser(@PathVariable("userid") String userId, @RequestBody User user) {
		
		DeferredResult<ResponseEntity<?>> deferedResult = responseGenService.generateDefferedResponse();

		if (user == null) {
			responseGenService.sendFalseResponse(deferedResult, new InvalidInputException("Input Device is Null"));
		} else {
			user.setUserId(userId);
			if (user.getUserId() == null || user.getUserId().trim().equalsIgnoreCase("")) {
				responseGenService.sendFalseResponse(deferedResult, new InvalidInputException("Id for  Device is Null"));
			} else {
				// update rule
				System.out.println("calling update method");
				System.out.println(user.getUserId()+" "+user.getName());
			
				Future<String> updateUserFuture = userService.updateUser(user);
				responseGenService.sendTrueResponse(deferedResult, updateUserFuture);
			}
		}

		return deferedResult;
	}
	
	@GetMapping("/user/{userid}")
	public DeferredResult<ResponseEntity<?>> getDevice(@PathVariable("userid") String userid) {


		DeferredResult<ResponseEntity<?>> deferedResult = responseGenService.generateDefferedResponse();
		try {
			System.out.println("calling get service method");			
			Future<User> getUserFuture =  userService .getUser(userid);
			System.out.println("sending deff result");
			responseGenService.sendTrueResponse(deferedResult, getUserFuture);
		} catch (Exception e) {
			e.printStackTrace();
			responseGenService.sendFalseResponse(deferedResult, e);
		}
		return deferedResult;
	}
	
	
}
