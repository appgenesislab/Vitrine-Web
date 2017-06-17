package com.appgenesislab.context;

import com.appgenesislab.mongo.MultiTenantMongoDbFactory;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootConfiguration
public class MongoConfiguration
{

     @Bean
     public Mongo mongo(Environment environment, MongoClientOptions.Builder optionsBuilder)
     {
          String node1 = environment.getProperty("mongo.replicaset.node1.host");
          String node2 = environment.getProperty("mongo.replicaset.node2.host");
          String node3 = environment.getProperty("mongo.replicaset.node3.host");

          String password = environment.getProperty("mongo.password");

          MongoCredential credentials = MongoCredential
              .createCredential("admin", "admin", password.toCharArray());

          MongoClient mongoClient = new MongoClient(
              Arrays.asList(new ServerAddress(node1), new ServerAddress(node2),
                  new ServerAddress(node3)), Arrays.asList(credentials, credentials, credentials),
              optionsBuilder.build());

          return mongoClient;
     }

     @Bean
     public MongoTemplate mongoTemplate(final Mongo mongo) throws Exception
     {
          return new MongoTemplate(mongoDbFactory(mongo));
     }

     @Bean
     public MultiTenantMongoDbFactory mongoDbFactory(final Mongo mongo) throws Exception
     {
          MultiTenantMongoDbFactory mongoDbFactory = new MultiTenantMongoDbFactory(mongo,
              "tenant1");
          mongoDbFactory.setDatabaseName("tenant2");
          return mongoDbFactory;
     }

     @Bean
     public MongoClientOptions.Builder mongoClientOption()
     {

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
