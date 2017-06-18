package com.appgenesislab.unit;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import com.appgenesislab.assumption.DataSearchAssumption;
import com.appgenesislab.model.Catalog;
import com.appgenesislab.parameter.GenericSearchParameter;
import com.appgenesislab.repository.CatalogRepository;
import com.appgenesislab.service.CatalogService;
import com.appgenesislab.service.ICatalogService;
import java.util.Arrays;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mock;


@RunWith(Theories.class)
public class CatalogServiceTest
{

     @Mock
     private CatalogRepository catalogRepository;

     private ICatalogService catalogService;

     @DataPoints
     public static DataSearchAssumption[] getParameterAndResult()
     {
          DataSearchAssumption[] map =
              {
                  given_productA_with_resulting_summer_catalog(),
                  given_productB_resulting_spring_catalogs(),
                  given_productC_resulting_no_catalogs()
              };

          return map;
     }

     @Before
     public void setUp() throws Exception
     {
          initMocks(this);
          catalogService = new CatalogService(catalogRepository);
     }

     @Test(expected = NullPointerException.class)
     public void should_not_accept_null_product_name()
     {
          String productName = null;

          catalogService
              .findByProductName(productName);
     }

     @Test
     public void should_call_repository_for_catalogs()
     {
          String productName = "productD";

          catalogService
              .findByProductName(productName);

          verify(catalogRepository)
              .findByProductName(productName);
     }

     @Theory
     public void should_return_list_with_resulting_catalogs(
         DataSearchAssumption dataSearchAssumption)
     {
          doReturn(dataSearchAssumption.getExpectedResult()).when(catalogRepository)
              .findByProductName(dataSearchAssumption.getGenericSearchParameter().getName());

          Assertions.assertThat(
              catalogService
                  .findByProductName(dataSearchAssumption.getGenericSearchParameter().getName())
          ).isEqualTo(dataSearchAssumption.getExpectedResult());
     }

     @Theory
     public void should_return_list_with_resulting_catalogs_with_right_size(
         DataSearchAssumption dataSearchAssumption)
     {
          doReturn(dataSearchAssumption.getExpectedResult()).when(catalogRepository)
              .findByProductName(dataSearchAssumption.getGenericSearchParameter().getName());

          Assertions.assertThat(
              catalogService
                  .findByProductName(dataSearchAssumption.getGenericSearchParameter().getName())
                  .size()).isEqualTo(dataSearchAssumption.getExpectedResult().size());
     }

     @After
     public void tearDown()
     {

     }

     private static DataSearchAssumption given_productA_with_resulting_summer_catalog()
     {
          return DataSearchAssumption.aAssumption().genericSearchParameter(
              GenericSearchParameter.aParameter().name("productA").build()).expectedResult(
              Arrays.asList(
                  Catalog.builder().createdDate(new Date()).description("Summer").id("first")
                      .active(true).build())).build();
     }

     private static DataSearchAssumption given_productB_resulting_spring_catalogs()
     {
          return DataSearchAssumption.aAssumption().genericSearchParameter(
              GenericSearchParameter.aParameter().name("productB").build()).expectedResult(
              Arrays.asList(
                  Catalog.builder().createdDate(new Date()).description("Spring").id("first")
                      .active(true).build(),
                  Catalog.builder().createdDate(new Date()).description("Spring").id("second")
                      .active(true).build())).build();
     }

     private static DataSearchAssumption given_productC_resulting_no_catalogs()
     {
          return DataSearchAssumption.aAssumption().genericSearchParameter(
              GenericSearchParameter.aParameter().name("productC").build()).expectedResult(
              Arrays.asList()).build();
     }
}

