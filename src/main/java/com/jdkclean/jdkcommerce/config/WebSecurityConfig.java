package com.jdkclean.jdkcommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdkclean.jdkcommerce.config.security.JWTFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.headers().frameOptions().disable();

		http.cors().and().csrf().disable()
				.addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, "/login").permitAll()
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
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers(SWAGGER_WHITELIST).permitAll()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	private static final String[] SWAGGER_WHITELIST = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**" };

}
