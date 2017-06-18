package com.appgenesislab.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.appgenesislab.model.Catalog;
import com.appgenesislab.model.Product;
import com.appgenesislab.repository.CatalogRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestContextManager;

@RunWith(Theories.class)
@DataMongoTest
public class CatalogRepositoryITest
{

     @Autowired
     private MongoTemplate mongoTemplate;

     @Autowired
     private CatalogRepository catalogRepository;


     @Before
     public void setUp() throws Exception
     {
          new TestContextManager(getClass()).prepareTestInstance(this);

          catalogRepository.deleteAll();
     }

     @DataPoints
     public static Integer[] expectedProductQuatities()
     {
          return new Integer[]{2, 3, 4};
     }

     @Theory
     public void shouldInsertCatalogsWithEmbeddedDocuments(final Integer quantityProducts)
     {
          Catalog catalog = givenCatalog(quantityProducts);

          catalog = catalogRepository.save(catalog);

          assertThat(catalog.getProducts().size()).isEqualTo(quantityProducts);
     }

     @Test
     public void shouldFindAllCatalogsByName()
     {
          catalogRepository.save(givenCatalog(3));

          Example<Catalog> example = Example
              .of(Catalog.builder().name("catalog1").active(true).build());

          assertThat(catalogRepository.findAll(example)).isNotEmpty();
     }

     @Test
     public void shouldProjectAllProductsFromACatalog()
     {
          catalogRepository.save(givenCatalog(3));

          assertThat(catalogRepository.findByProductName("Shirt 2")).isNotEmpty();
     }

     public Catalog givenCatalog(final Integer quantityProducts)
     {
          List<Product> products = new ArrayList<>();

          for (int i = 0; i < quantityProducts; i++)
          {
               products.add(
                   Product.builder().id(new ObjectId()).name("Shirt " + i)
                       .description("Black Shirt" + 1).active(true)
                       .build());
          }

          return Catalog.builder().name("catalog1").createdDate(new Date())
              .description("Summer catalog").products(products).active(true).build();
     }


}
