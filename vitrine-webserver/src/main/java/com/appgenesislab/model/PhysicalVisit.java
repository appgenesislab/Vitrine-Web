package com.appgenesislab.model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 * Created by mariopaulo on 2017-03-19.
 */
public class PhysicalVisit
{
     private ObjectId id;

     private GeoJsonPoint location;

     private Date data;
}
