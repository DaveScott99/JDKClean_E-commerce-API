package com.jdkclean.jdkcommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/products/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "OPERATOR")
				.requestMatchers(HttpMethod.POST, "/users").permitAll()
				.requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/categories").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/categories").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/carts").permitAll()
				.requestMatchers(HttpMethod.POST, "/carts").hasAnyRole("OPERATOR", "ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/carts").hasAnyRole("OPERATOR", "ADMIN")
				.requestMatchers(HttpMethod.GET, "/h2-console").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf().disable();
				
		return http.build();
	}
	
}
