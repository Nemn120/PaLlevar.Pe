package com.paLlevar.app.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


// ejecucion de la creacion del token,
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

	// traer variables del properties.
	
	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Value("${security.jwt.grant-type}")
	private String grantType;

	@Value("${security.jwt.scope-read}")
	private String scopeRead;

	@Value("${security.jwt.scope-write}")
	private String scopeWrite;

	@Value("security.jwt.key")
	private String jwtKey;

	@Autowired
	private AuthenticationManager authenticationManager;	
	
	@Autowired
	private PasswordEncoder bcrypt;	
	//v .resourceIds(resourceIds)

	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
	
	@Override // crea el token, codifica el secret 
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer.inMemory().withClient(clientId)
				.secret(bcrypt.encode(clientSecret)).authorizedGrantTypes(grantType , "refresh_token")
		// ambitos de lectura y escritura
				.scopes(scopeRead, scopeWrite).authorizedGrantTypes("password", "refresh_token")
				// tiempo del token
				.accessTokenValiditySeconds(100000)
		// si usas token de refresco.,
		.refreshTokenValiditySeconds(100000);
	}

	@Override 		// genera la cadena token con todos los datos configurados.	
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,accessTokenConverter()));
		endpoints
				.tokenStore(tokenStore())
				.accessTokenConverter(accessTokenConverter())
				.tokenEnhancer(enhancerChain)
				.authenticationManager(authenticationManager);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);	// utiliza token de refresco
		defaultTokenServices.setReuseRefreshToken(false);	// genera mas token refresco
		return defaultTokenServices;
	}

}
