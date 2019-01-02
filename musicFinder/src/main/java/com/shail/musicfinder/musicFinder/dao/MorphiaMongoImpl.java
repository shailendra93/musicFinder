package com.shail.musicfinder.musicFinder.dao;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


/**
 * @author bhaswanthg
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MorphiaMongoImpl implements IDatabase {
	/**
	 * @uml.property name="morphia"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	final Morphia morphia = new Morphia();
	/**
	 * @uml.property name="datastore"
	 * @uml.associationEnd
	 */
	private Datastore datastore;

	@Override
	public void init() {
		morphia.mapPackage("com.shail.musicfinder.musicFinder");

		try {
			ServerAddress address = new ServerAddress("127.0.0.1",27017);
			
			MongoClient client = new MongoClient(address,
					Arrays.asList(
							MongoCredential.createCredential("shail","person","12345678".toCharArray())));
			datastore = morphia.createDatastore(client, "person");
			datastore.ensureIndexes();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String save(Object toBeSaved) {
		Key key = datastore.save(toBeSaved);
		System.out.println("saved:"+key.getId().toString());
		return key.getId().toString();
	}

	@Override
	public Object getByField(String fieldName, String fieldValue, Class classname) {
		System.out.println("in morphia implementation");
		System.out.println("field in db:"+fieldName);
		System.out.println("value in db:"+fieldValue);
		QueryResults query = (QueryResults) datastore.createQuery(classname).field(fieldName).equal(fieldValue);
        System.out.println("result:"+query.get());
		return query.get();

	}
	
	@Override
	public void delete(Object obj) {
		//System.out.println("id: "+obj.getDeviceId());
		datastore.delete(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topeng.tsg.dswc.database.coreimpl.IDatabase#update()
	 * 
	 * Issues- All fields should be public- need to fix that.
	 */
	@Override
	public Boolean update(Object entity, Class classname) {
		UpdateOperations ops = datastore.createUpdateOperations(classname);
		Field[] allFields = classname.getDeclaredFields();
		for (Field field : allFields) {
			{
				Object value;
				try {
					value = field.get(entity);
					System.out.println(field.getName()+" "+value+"");
					ops = ops.set(field.getName(), value);
				} catch (IllegalArgumentException | IllegalAccessException e) {					
					e.printStackTrace();
				}

			}
		}
		UpdateResults results = datastore.update(entity, ops);

		return results.getUpdatedExisting();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.topeng.tsg.dswc.database.coreimpl.IDatabase#getAll(java.lang.Class)
	 */
	@Override
	public List getAll(Class class1) {
		final Query query = datastore.createQuery(class1);
		return query.asList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.topeng.tsg.dswc.database.coreimpl.IDatabase#getById(java.lang.String
	 * , java.lang.Class)
	 */
//	@Override
//	public Object getById(String id, Class classname) {
//		return datastore.get(classname, new ObjectId(id));
//	}

	@Override
	public List getByMultipleFields(Class classname, HashMap<String, Object> values) {

		Query q = datastore.createQuery(classname);

		for (String key : values.keySet()) {
			q = q.filter(key + " =", values.get(key));
		}
		// q.order("-")
		return q.asList();
	}

	@Override
	public List getByMultipleFields(Class classname, HashMap<String, Object> values, String orderBy, int limit,
			int offset) {
		Query q = datastore.createQuery(classname);
		for (String key : values.keySet()) {
			q = q.filter(key + " =", values.get(key));
		}
		q = q.order("-" + orderBy);
		q.limit(limit);
		q.offset(offset);
		return q.asList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topeng.tsg.dswc.database.coreimpl.IDatabase#countAll(java.lang.
	 * Class)
	 */
	@Override
	public long countAll(Class class1) {
		Query q = datastore.createQuery(class1);
		return q.countAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topeng.tsg.dswc.database.coreimpl.IDatabase#deleteAll(java.lang
	 * .Class)
	 */
	@Override
	public void deleteAll(Class class1) {
		datastore.delete(datastore.createQuery(class1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.topeng.tsg.dswc.database.coreimpl.IDatabase#saveAll(java.util.List)
	 */
	@Override
	public void saveAll(List list) {
		datastore.save(list);
	}

	@Override
	public List getAllByDateRange(Class clazz, String fieldName, Date startDate, Date endDate) {
		// DBObject
		// query=QueryBuilder.start().in(clazz).put(fieldName).greaterThanEquals(startDate).lessThanEquals(endDate).get();

		Query q = datastore.createQuery(clazz).filter(fieldName + " >= ", startDate).filter(fieldName + " <= ",
				endDate);
		
		return q.asList();

	}

	@Override
	public List getAllByDateRange(Class clazz, String fieldName, Long startDate, Long endDate) {
		// DBObject
		// query=QueryBuilder.start().in(clazz).put(fieldName).greaterThanEquals(startDate).lessThanEquals(endDate).get();

		Query q = datastore.createQuery(clazz).filter(fieldName + " >= ", startDate).filter(fieldName + " <= ",
				endDate);

		
		return q.asList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.topeng.tsg.dswc.database.coreimpl.IDatabase#getAll(java.lang.Class,
	 * int, int)
	 */
	@Override
	public List getAll(Class class1, int limit, int offset) {
		Query q = datastore.createQuery(class1);
		q.limit(limit);
		q.offset(offset);
		return q.asList();

	}

	@Override
	public Datastore getDS() {
		return datastore;
	}

	



}
