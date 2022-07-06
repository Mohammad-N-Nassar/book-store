package com.example.book_store.unit.dao.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class FakeMongo extends AbstractMongoClientConfiguration {

	@Override
	protected String getDatabaseName() {
		return "mockDB";
	}
//
//	@Bean
//	public MongoClient mongo() {
//		Fongo fongo = new Fongo("mockDB");
//		return fongo.getMongo();
//	}

}