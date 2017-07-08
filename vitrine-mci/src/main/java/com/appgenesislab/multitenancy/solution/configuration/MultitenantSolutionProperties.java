package com.appgenesislab.multitenancy.solution.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(
    prefix = MultitenantSolutionProperties.PREFIX
)
public class MultitenantSolutionProperties
{
     public static final String PREFIX = "vitrine.multitenancy";
     public static final String PROP_NAME = "solution";
     public static final String PROP_MATCHING_VALUE = "default";

     @Data
     public static class Datasource
     {
          private String uri;
     }

     private String solution;

     private Datasource datasource;
}
