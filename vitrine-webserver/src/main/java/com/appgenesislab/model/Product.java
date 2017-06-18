package com.appgenesislab.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mariopaulo on 2017-03-12.
 */
@Document(collection = "catalog")
@Builder(builderMethodName = "builder")
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Product implements Serializable
{

     private static final long serialVersionUID = -2582990924493050537L;

     @Id
     private ObjectId id;

     private String name;

     private String description;

     private Date createdDate;

     private Boolean active;


}
