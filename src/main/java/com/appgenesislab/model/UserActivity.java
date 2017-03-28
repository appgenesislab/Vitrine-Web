package com.appgenesislab.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "userActivity")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "physicalVisits")
@Builder
@Getter
@Setter
@ToString
public class UserActivity
{

     @Id
     private ObjectId id;

     private ObjectId userId;

     private String userName;

     private ProductEvent productEvent;

     private List<PhysicalVisit> physicalVisits;

     private Boolean active;

}
