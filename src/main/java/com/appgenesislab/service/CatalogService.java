package com.appgenesislab.service;


import static com.google.common.base.Preconditions.*;

import com.appgenesislab.model.Catalog;
import com.appgenesislab.repository.CatalogRepository;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CatalogService implements  ICatalogService
{

     private CatalogRepository catalogRepository;

     public CatalogService(CatalogRepository catalogRepository)
     {
          this.catalogRepository = catalogRepository;
     }

     @Override
     public List<Catalog> findByProductName(String productName)
     {
          checkNotNull(productName,"productName cannot be null");
          return catalogRepository.findByProductName(productName);
     }
}
