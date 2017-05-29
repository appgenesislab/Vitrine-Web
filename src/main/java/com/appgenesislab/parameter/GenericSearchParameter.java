package com.appgenesislab.parameter;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * Created by mariopaulo on 2017-05-21.
 */
@Data
@Builder(builderMethodName = "aParameter")
public class GenericSearchParameter
{

     ObjectId objectId;

     String name;

}
