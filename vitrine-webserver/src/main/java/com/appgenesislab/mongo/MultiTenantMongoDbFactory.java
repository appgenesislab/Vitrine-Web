package com.appgenesislab.mongo;

import com.mongodb.DB;
import com.mongodb.Mongo;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.util.Assert;

public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory
{

     private final String defaultName;
     private static final Logger logger = LoggerFactory.getLogger(MultiTenantMongoDbFactory.class);

     private MongoTemplate mongoTemplate;

     private String databaseName;

     private static final HashMap<String, Object> databaseIndexMap = new HashMap<String, Object>();


     public MultiTenantMongoDbFactory(final Mongo mongo, final String defaultDatabaseName)
     {
          super(mongo, defaultDatabaseName);
          logger.debug("Instantiating " + MultiTenantMongoDbFactory.class.getName()
              + " with default database name: " + defaultDatabaseName);
          this.defaultName = defaultDatabaseName;
     }

     public void setMongoTemplate(final MongoTemplate mongoTemplate)
     {
          Assert.isNull(this.mongoTemplate, "You can set MongoTemplate just once");
          this.mongoTemplate = mongoTemplate;
     }

     public void setDatabaseName(final String databaseName)
     {
          logger.debug("Switching to database: " + databaseName);
          this.databaseName = databaseName;
     }

     public void clearDatabaseName()
     {
          if (logger.isDebugEnabled())
          {
               logger.debug("Removing database [" + databaseName + "]");
          }
          databaseName = "";
     }

     @Override
     public DB getDb()
     {
          final String dbToUse = (databaseName != null ? databaseName : this.defaultName);
          logger.debug("Acquiring database: " + dbToUse);
          //createIndexIfNecessaryFor(dbToUse);
          return super.getDb(dbToUse);
     }

     private void createIndexIfNecessaryFor(final String database)
     {
          if (this.mongoTemplate == null)
          {
               logger.error("MongoTemplate is null, will not create any index.");
               return;
          }
          //        sync and init once
          boolean needsToBeCreated = false;
          synchronized (MultiTenantMongoDbFactory.class)
          {
               final Object syncObj = databaseIndexMap.get(database);
               if (syncObj == null)
               {
                    databaseIndexMap.put(database, new Object());
                    needsToBeCreated = true;
               }
          }
          //        make sure only one thread enters with needsToBeCreated = true
          synchronized (databaseIndexMap.get(database))
          {
               if (needsToBeCreated)
               {
                    logger.debug("Creating indices for database name=[" + database + "]");
                    logger.debug("Done with creating indices for database name=[" + database + "]");
               }
          }
     }

}
