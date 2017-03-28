package com.appgenesislab.repository;

import com.appgenesislab.model.Catalog;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CatalogRepository extends MongoRepository<Catalog, String> {

     @Query(value="{ 'products.name' : ?0 }", fields="{ 'name' : 1, 'products.name' : 1}")
     List<Catalog> findByProductName(String name);
}
