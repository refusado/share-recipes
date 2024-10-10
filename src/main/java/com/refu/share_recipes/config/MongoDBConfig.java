package com.refu.share_recipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {
  public MongoDatabaseFactory mongoConfigure() {
    return new SimpleMongoClientDatabaseFactory("mongodb://root:secret@localhost:27017/recipes_db?authSource=admin");
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoConfigure());
  }
}