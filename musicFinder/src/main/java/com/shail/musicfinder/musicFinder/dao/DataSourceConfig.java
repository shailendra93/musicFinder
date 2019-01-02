package com.shail.musicfinder.musicFinder.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSourceConfig {
	  // Inject an instance of Spring-Boot MongoProperties
	  @Autowired
	//    private MongoClientFactoryBean mongo;
	  private MongoProperties mongoProperties;

	  private Morphia morphia() {
	    final Morphia morphia = new Morphia();
	    // tell Morphia where to find your classes
	    // can be called multiple times with different packages or classes
	    morphia.mapPackage("com.juliuskrah.morphia.entity");

	    return morphia;
	  }

	/*  @Bean
	  public Datastore datastore(MongoClient mongoClient) {
	    // create the Datastore connecting to the default port on the local host
	    final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
	    datastore.ensureIndexes();

	    return datastore;
	  }*/
	}
