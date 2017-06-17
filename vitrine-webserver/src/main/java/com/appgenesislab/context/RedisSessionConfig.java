package com.appgenesislab.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class RedisSessionConfig implements BeanClassLoaderAware
{
     private ClassLoader loader;

     @Bean
     public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
          return new GenericJackson2JsonRedisSerializer(objectMapper());
     }

     @Bean
     public LettuceConnectionFactory connectionFactory(Environment environment) {
          LettuceConnectionFactory lettuceConnectionFactory= new LettuceConnectionFactory();
          lettuceConnectionFactory.setHostName(environment.getProperty("spring.redis.host"));
          lettuceConnectionFactory.setPort(Integer.valueOf(environment.getProperty("spring.redis.port")));
          lettuceConnectionFactory.setPassword(environment.getProperty("spring.redis.password"));

          return lettuceConnectionFactory;
     }

     /**
      * Customized {@link ObjectMapper} to add mix-in for class that doesn't have default
      * constructors
      *
      * @return the {@link ObjectMapper} to use
      */
     ObjectMapper objectMapper() {
          ObjectMapper mapper = new ObjectMapper();
          mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
          return mapper;
     }

     /*
      * (non-Javadoc)
      *
      * @see
      * org.springframework.beans.factory.BeanClassLoaderAware#setBeanClassLoader(java.lang
      * .ClassLoader)
      */
     public void setBeanClassLoader(ClassLoader classLoader) {
          this.loader = classLoader;
     }
}
