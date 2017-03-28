package com.appgenesislab.model;

/**
 * Created by mariopaulo on 2017-03-19.
 */
public enum EventType
{


     VISIT("VISIT"), RATING("RATING"), MARK_FAVORITE("MARK_FAVORITE");

     private String type;

     private EventType(String type){

          this.type=type;

     }

     public String getType()
     {
          return type;
     }

     public void setType(String type)
     {
          this.type = type;
     }
}
