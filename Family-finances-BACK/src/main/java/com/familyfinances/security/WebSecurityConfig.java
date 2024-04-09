package com.familyfinances.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.familyfinances")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/login").anonymous();
        http.antMatcher("/registration").anonymous();
        http.antMatcher("/room").anonymous();
        http.antMatcher("/finance").anonymous();
        http.authorizeRequests().antMatchers("/rooms").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/users").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/room/budget/update").hasRole("ADMIN");
//        http.authorizeRequests().anyRequest().authenticated();
        http.antMatcher("/").anonymous();
    }
}
