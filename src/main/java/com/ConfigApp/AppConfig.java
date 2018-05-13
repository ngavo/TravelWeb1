package com.ConfigApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories(basePackages="com.reponsitory")
public class AppConfig extends AbstractMongoConfiguration {

	@Autowired
	private ApplicationContext appContext;
	
	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient(new MongoClientURI("mongodb://ngavtt:221415552@ds259499.mlab.com:59499/mydatabase"));
		/*return new MongoClient("localhost:27017");*/
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "mydatabase";
	}
	
	@Override
    protected String getMappingBasePackage() {
        return "com.entity";
    }
	
	@Bean
	public MongoTemplate mongoTemplate()
	{
		return new MongoTemplate(mongoClient(),getDatabaseName());
	}
	
	@Bean
	public GridFsTemplate gridRsTemplate() throws Exception{
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

}
