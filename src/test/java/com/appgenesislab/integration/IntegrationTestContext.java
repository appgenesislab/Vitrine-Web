package com.appgenesislab.integration;

import com.appgenesislab.mongo.MultiTenantMongoDbFactory;
import com.mongodb.Mongo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@TestConfiguration
public class IntegrationTestContext
{

     @Bean
     public Mongo mongo(Environment environment)
     {
          String dbName = environment.getProperty("mongo.db");
          String host = environment.getProperty("mongo.host");
          Mongo mongo = new Mongo(host);
          return mongo;
     }

     @Bean
     public MongoTemplate mongoTemplate(final Mongo mongo) throws Exception
     {
          return new MongoTemplate(mongoDbFactory(mongo));
     }

     @Bean
     public MultiTenantMongoDbFactory mongoDbFactory(final Mongo mongo) throws Exception
     {
          return new MultiTenantMongoDbFactory(mongo, "tenant1");
     }


}
