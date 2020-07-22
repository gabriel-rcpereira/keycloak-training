package com.example.keycloak.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .csrf()
                        .disable()
                    .authorizeRequests()
                        .antMatchers(GET, "/api/**")
                        .authenticated()
                .and()
                    .oauth2ResourceServer()
                        .jwt();
    }
}
