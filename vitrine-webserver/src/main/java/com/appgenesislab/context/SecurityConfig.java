package com.appgenesislab.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by mariopaulo on 2017-05-31.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

     // @formatter:off
     @Override
     protected void configure(HttpSecurity http) throws Exception {
          http
              .authorizeRequests()
              .antMatchers("/resources/**").permitAll()
              .anyRequest().authenticated()
              .and()
              .httpBasic()
              .and()
              .logout().permitAll();
     }
     // @formatter:on

     // @formatter:off
     @Autowired
     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
          auth
              .inMemoryAuthentication()
              .withUser("user").password("password").roles("USER");
     }
     // @formatter:on
}