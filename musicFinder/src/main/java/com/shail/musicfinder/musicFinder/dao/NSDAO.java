package com.shail.musicfinder.musicFinder.dao;

import java.util.List;

import com.shail.musicfinder.musicFinder.objects.User;



public class NSDAO {

	 MorphiaMongoImpl morphiamongo;
	 
	 public NSDAO()
	 {
		 morphiamongo = new MorphiaMongoImpl();
    	 morphiamongo.init();
	 }
	 
	 public void save(User user)
	 {
		 morphiamongo.save(user);
		 
	 }
	 public void deleteUser(User user)
	 {
		
		 System.out.println("dao id:"+user.getUserId());
		 morphiamongo.delete(user);
	 }
	 public void updateUser(User user)
	 {
		 morphiamongo.update(user, User.class);
	 }
	 public User getUserById(String id)
	 {

		 System.out.println("Calling morphia method:");
	    	return (User)morphiamongo.getByField("UserId",id,User.class);
		 
	 }

	public List<User> getAllDevice(Class<User> class1, int parseInt, int parseInt2) {
		// TODO Auto-generated method stub
		return morphiamongo.getAll(class1);
	}

	
	 
}
