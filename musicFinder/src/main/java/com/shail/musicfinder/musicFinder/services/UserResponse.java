package com.shail.musicfinder.musicFinder.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



import org.springframework.stereotype.Service;
import com.shail.musicfinder.musicFinder.dao.DBManager;
import com.shail.musicfinder.musicFinder.objects.User;


@Service
public class UserResponse {

	private static final ExecutorService threadpool = Executors.newCachedThreadPool();
	
	
	public Future<User> getUser(String id)
	{
		
		return (Future<User>) threadpool.submit(new Callable<User>() {
			@Override
			public User call() throws Exception {
				
				User user = (User) DBManager.getInstance().getByField("userId",id,User.class);
				System.out.println(user.getName());
				return user;
			}
		});
	}
	
	public Future<String> addUser(User user)
	{
		return threadpool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Date date = new Date();		
				Calendar cal = Calendar.getInstance();
			    cal.setTimeInMillis(date.getTime());
			    String createdTime = cal.getTime().toString();
			    String updatedTime = createdTime;
				user.setUserId(UUID.randomUUID().toString());
				
				DBManager.getInstance().save(user);				
				return user.getUserId();
			}
		});
		
	}
	public Future<String> deleteUser(String userId)
	{
		System.out.println("in serive method:");
		return threadpool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {

				User user = (User) DBManager.getInstance().getByField("userId", userId, User.class);				
				DBManager.getInstance().delete(user);
				//updateUser(user);
				return userId;
			}
		});
		
	}
	
	
	public Future<String> updateUser(User user)
	{
		return threadpool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				DBManager.getInstance().save(user);
				return user.getUserId();
			}
		});
		
		
	}

	public Future<List<User>> getAllUser(String limitString, String offsetString) {
		return threadpool.submit(new Callable<List<User>>() {			
			@Override
			public List<User> call() throws Exception {
				return DBManager.getInstance().getAll(User.class, Integer.parseInt(limitString),
						Integer.parseInt(offsetString));
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
}
