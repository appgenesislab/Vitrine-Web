package com.appgenesislab.controller;

import com.appgenesislab.model.Product;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class ProductController
{

     @RequestMapping(method = RequestMethod.GET, value = "/products/list")
     public List<Product> list()
     {
          return Stream.of(new Product(new ObjectId(), "T-Shirt", "Blue t-shirt", new Date(), true))
              .collect(
                  Collectors.toList());
     }
}
