package com.appgenesislab.multitenancy.solution.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(MultitenantSolutionProperties.class)
@ConditionalOnProperty(prefix = MultitenantSolutionProperties.PREFIX, name = MultitenantSolutionProperties.PROP_NAME, havingValue = MultitenantSolutionProperties.PROP_MATCHING_VALUE, matchIfMissing = true)
@Import(MongoMultitenantDBSource.class)
public class MultitenantSolutionContext
{


}
