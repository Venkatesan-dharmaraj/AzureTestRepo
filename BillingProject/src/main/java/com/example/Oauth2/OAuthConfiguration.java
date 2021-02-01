package com.example.Oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
    private  AuthenticationManager authenticationManager;
	@Autowired
    private  BCryptPasswordEncoder passwordEncoder;
	@Autowired
    private  UserDetailsService userService;
	
	@Autowired
	private TokenStore tokenStore;
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	 clients
         .inMemory()
         .withClient("my-trusted-client")
         .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
         .scopes("read","write","trust")
         .resourceIds("oauth2-resource")
         .accessTokenValiditySeconds(500)
         .refreshTokenValiditySeconds(1000)
         .secret(passwordEncoder.encode("secret"));
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .userDetailsService(userService)
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore);
    }

    
    
    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }
    
 
   @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
    
    


}
