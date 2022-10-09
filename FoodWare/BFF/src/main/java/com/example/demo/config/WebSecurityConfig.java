package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) {
    try {
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
          cors().disable()
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/order/**").permitAll();
    } catch (Exception e) {
      log.warn("There was a problem while intercepting the request " + e.getMessage());
    }
  }

}
