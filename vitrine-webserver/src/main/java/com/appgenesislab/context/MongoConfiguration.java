package com.appgenesislab.context;

import com.appgenesislab.mongo.MultiTenantMongoDbFactory;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration
{
     private static final Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

     @Bean
     public Mongo mongo(Environment environment, MongoClientOptions.Builder optionsBuilder)
     {
          logger.info("Creating customized Mongo Bean");
          String uri = environment.getProperty("mongo.uri");
          MongoClient mongoClient = new MongoClient(new MongoClientURI(uri,optionsBuilder));

          return mongoClient;
     }

     @Bean
     public MongoTemplate mongoTemplate(final Mongo mongo) throws Exception
     {
          logger.info("Creating MultiTenant mongoTemplate bean");
          return new MongoTemplate(mongoDbFactory(mongo));
     }

     @Bean
     public MultiTenantMongoDbFactory mongoDbFactory(final Mongo mongo) throws Exception
     {
          logger.info("Creating MultiTenantMongoDbFactory bean");
          MultiTenantMongoDbFactory mongoDbFactory = new MultiTenantMongoDbFactory(mongo,
              "tenant1");
          mongoDbFactory.setDatabaseName("tenant2");
          return mongoDbFactory;
     }

     @Bean
     public MongoClientOptions.Builder mongoClientOption()
     {

          logger.info("Creating  MultiTenant mongoClientOption bean");
          MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();

          optionsBuilder.socketTimeout(60000);
          optionsBuilder.maxConnectionIdleTime(60000);
          optionsBuilder.socketKeepAlive(true);
          optionsBuilder.connectTimeout(60000);
          optionsBuilder.maxConnectionLifeTime(120000);
          optionsBuilder.serverSelectionTimeout(120000);
          optionsBuilder.sslEnabled(true);
          return optionsBuilder;
     }

}
