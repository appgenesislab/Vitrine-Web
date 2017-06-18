package com.appgenesislab.multitenancy.solution.configuration;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoMultitenantDBSource
{

     private static final Logger logger = LoggerFactory.getLogger(MongoMultitenantDBSource.class);

     @Autowired
     private MultitenantSolutionProperties properties;

     @Bean
     public MongoDbFactory mongoDbFactory() throws UnknownHostException
     {
          logger.info("Creating mongoDbFactory for multitenancy default solution");

          return new SimpleMongoDbFactory(
              new MongoClientURI(properties.getDatasource().getUri(), mongoClientOption()));
     }

     @Bean
     public MongoTemplate mongoTemplate() throws UnknownHostException
     {

          return new MongoTemplate(mongoDbFactory());
     }

     @Bean
     public MongoClientOptions.Builder mongoClientOption()
     {

          MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();

          optionsBuilder.socketTimeout(60000);
          optionsBuilder.maxConnectionIdleTime(60000);
          optionsBuilder.socketKeepAlive(true);
          optionsBuilder.connectTimeout(60000);
          optionsBuilder.maxConnectionLifeTime(125000);
          optionsBuilder.serverSelectionTimeout(125000);
          optionsBuilder.sslEnabled(true);
          return optionsBuilder;
     }
}
