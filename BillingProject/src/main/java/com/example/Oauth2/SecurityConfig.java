package com.example.Oauth2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetails;
	
	@Autowired
    DataSource dataSource;
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	 
	 
	 
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setPasswordEncoder( bCryptPasswordEncoder() );
	    provider.setUserDetailsService(userDetails);
	    return provider;
	  }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    } 
	
	
	/*
	 * @Autowired // here is configuration related to spring boot basic public void
	 * configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 * auth.inMemoryAuthentication() // static users
	 * .withUser("User").password(bCryptPasswordEncoder().encode("User")).
	 * roles("USER") .and()
	 * .withUser("Admin").password(bCryptPasswordEncoder().encode("Admin[")).
	 * roles("ADMIN"); }
	 */
	 
	  @Autowired
	    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetails)
	                .passwordEncoder(bCryptPasswordEncoder());
	    }
	  
	  @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/*",
	                                   "/configuration/ui",
	                                   "/swagger-resources/**",
	                                   "/configuration/security",
	                                   "/swagger-ui.html");
	    }
	  
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .csrf().disable()
	        .anonymous().disable()
	        .authorizeRequests()
	        .antMatchers("/oauth/token").permitAll();
	    }
	  
}
	
	
