package com.appgenesislab.controller;

import com.appgenesislab.model.Catalog;
import com.appgenesislab.service.ICatalogService;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class CatalogController
{

     private ICatalogService catalogService;

     public CatalogController(ICatalogService catalogService)
     {
          this.catalogService=catalogService;
     }

     @RequestMapping(method = RequestMethod.GET, value = "catalog/{productName}")
     public List<Catalog> listByProductName(@PathVariable String productName)
     {
          return catalogService.findByProductName(productName);
     }
}
