package com.paLlevar.app.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer //para que la configure spring
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
    private ResourceServerTokenServices tokenServices;
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    @Override // configura y protege las url
    public void configure(HttpSecurity http) throws Exception {
                http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
               	.and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/v2/api-docs/**" ).permitAll()
                .antMatchers("/product/**" ).permitAll()                
                .antMatchers("/categoryproduct/**" ).permitAll()
                .antMatchers("/branchoffice/**" ).permitAll()
                .antMatchers("/company/**" ).permitAll()
                .antMatchers("/menuDay/**" ).permitAll()
                .antMatchers("/order/**" ).permitAll()
                .antMatchers("/orderDetail/**" ).permitAll()
                .antMatchers("/menuDayProduct/**" ).permitAll();
    }
}
