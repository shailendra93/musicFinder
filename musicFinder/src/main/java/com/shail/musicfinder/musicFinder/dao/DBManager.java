/**
 * 
 */
package com.shail.musicfinder.musicFinder.dao;


import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class DBManager {
	private IDatabase dbMapper;

	public DBManager() {
		dbMapper = new MorphiaMongoImpl();
		dbMapper.init();
	}

	public static DBManager getInstance() {
		return managerInstace;
	}

	public static DBManager managerInstace = new DBManager();;

	public void save(Object entity) {
		dbMapper.save(entity);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> class1,int limit,int offset) {
		return dbMapper.getAll(class1);
	}

	public Object getByField(String fieldName,String fieldValue, Class class1) {
		// TODO Auto-generated method stub
		System.out.println("in dbmge getbyfield method");
		return  dbMapper.getByField(fieldName, fieldValue, class1);
	}
	
public void saveAll(List list) {
		
	dbMapper.saveAll(list);
	}

	public void delete(Object object) {
		
		dbMapper.delete(object);
		
	}

	



}