package com.appgenesislab.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mariopaulo on 2017-03-19.
 */
@Document(collection = "store")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"gallery","payments","users","pricing"})
@ToString
public class Store implements Serializable
{

     private static final long serialVersionUID = -3079270948531245316L;

     private String name;

     private Address address;

     private String description;

     private List<ImageStore> gallery;

     private List<Payment> payments;

     private List<Pricing> pricing;

     private List<User> users;
}
