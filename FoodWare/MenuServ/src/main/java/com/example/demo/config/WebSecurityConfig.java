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
        .antMatchers("/product/**").permitAll()
        .antMatchers("/category/**").permitAll()
        .antMatchers("/group").permitAll().and().httpBasic();
  }

}