package com.example.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
        cors().disable()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/payment/**").permitAll()
        .antMatchers("/status/**").permitAll()
        .antMatchers("/shoppingCart/**").permitAll()
        .antMatchers("/extraOrderItem/**").permitAll()
        .antMatchers("/subOrder/**").permitAll()
        .antMatchers("/shoppingItem/**").permitAll().and().httpBasic();
  }

}