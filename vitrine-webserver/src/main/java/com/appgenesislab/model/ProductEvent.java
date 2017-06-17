package com.appgenesislab.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@Builder(builderMethodName = "builder")
public class ProductEvent
{

     private ObjectId id;

     private ObjectId catalogId;

     private ObjectId productId;

     private Date time;

     private EventType eventType;

}
