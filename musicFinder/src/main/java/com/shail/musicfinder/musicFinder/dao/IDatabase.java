/**
 * 
 */
package com.shail.musicfinder.musicFinder.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mongodb.morphia.Datastore;



public interface IDatabase {

	/**
	 * 
	 */
	void init();

	/**
	 * @param toBeSaved
	 * @return
	 */
	String save(Object toBeSaved);

	/**
	 * @param fieldName
	 * @param fieldValue
	 * @param classname
	 * @return
	 */
	Object getByField(String fieldName, String fieldValue, Class classname);

//	Object getById(String id, Class classname);

	/**
	 * @param obj
	 */
	void delete(Object obj);

	/**
	 * @param entity
	 * @param classname
	 * @return
	 */
	Boolean update(Object entity, Class classname);

	/**
	 * @param class1
	 * @return
	 */
	List getAll(Class class1);

	List getAll(Class class1, int limit, int offset);

	/**
	 * @param classname
	 * @param values
	 * @return
	 */
	List getByMultipleFields(Class classname, HashMap<String, Object> values);

	/**
	 * @param class1
	 * @return
	 */
	long countAll(Class class1);

	/**
	 * @param class1
	 * @return
	 */
	void deleteAll(Class class1);

	/**
	 * @param list
	 */
	void saveAll(List list);

	/**
	 * @param clazz
	 * @param fieldName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List getAllByDateRange(Class clazz, String fieldName, Date startDate, Date endDate);

	List getAllByDateRange(Class clazz, String fieldName, Long startDate, Long endDate);

	/**
	 * @param classname
	 * @param values
	 * @param orderBy
	 * @param limit
	 * @param offset
	 * @return
	 */
	List getByMultipleFields(Class classname, HashMap<String, Object> values, String orderBy, int limit, int offset);

	Datastore getDS();

	

}
