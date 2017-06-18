package com.appgenesislab.assumption;

import com.appgenesislab.parameter.GenericSearchParameter;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "aAssumption")
public class DataSearchAssumption
{
     private GenericSearchParameter genericSearchParameter;

     private List expectedResult;
}
