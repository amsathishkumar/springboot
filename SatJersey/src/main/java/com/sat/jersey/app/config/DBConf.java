package com.sat.jersey.app.config;

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.mongobee.Mongobee;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("com.sat.jersey.db.pri")
@Import(value = MongoAutoConfiguration.class)
public class DBConf extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "sat";
	}

	@Override
	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
		return new Mongo();
	}
	
	  @Bean
	  public Mongobee mongobee(MongoClient mongoClient, MongoTemplate mongoTemplate,
	      MongoProperties mongoProperties) {
	    Mongobee mongobee = new Mongobee(mongoClient);
	    mongobee.setDbName(mongoProperties.getDatabase());
	    mongobee.setMongoTemplate(mongoTemplate);
	    // package to scan for migrations
	    mongobee.setChangeLogsScanPackage("com.sat.jersey.app.config.db");
	    mongobee.setEnabled(true);
	    return mongobee;
	  }

}
