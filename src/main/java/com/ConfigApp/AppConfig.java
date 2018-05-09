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
		return new MongoClient(new MongoClientURI("mongodb://nga:hDByXTewgs4sTVA86rmhgRrcgXvVx6go0g7iuIQbpavZHxyeMgF1c3YuVf38aQykjBysTCsUyvmwG7IM3K8k9A==@nga.documents.azure.com:10255/?ssl=true&replicaSet=globaldb"));
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "mangxahoidulich";
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
