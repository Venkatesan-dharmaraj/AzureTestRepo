package com.example.Oauth2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "oauth2-resource";
    @Autowired
    DataSource dataSource;  
    @Autowired
    TokenStore tokenStore;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
        resources.tokenStore(tokenStore);
    }
 
    public void configure(HttpSecurity http) throws Exception {
        http.
        anonymous().disable()
        .requestMatchers().antMatchers("/api/**")
        .and().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/check").access("hasAnyAuthority('USER','ADMIN')")
        .antMatchers(HttpMethod.POST, "/api/saveProduct").access("hasAuthority('ADMIN')")
        .antMatchers(HttpMethod.GET, "/api/showAllProducts").access("hasAnyAuthority('USER','ADMIN')")
        .antMatchers(HttpMethod.PUT, "/api/updateProducts/**").access("hasAuthority('ADMIN')")
        .antMatchers(HttpMethod.GET, "/api/getProduct/**").access("hasAuthority('ADMIN')")
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
    
   
}