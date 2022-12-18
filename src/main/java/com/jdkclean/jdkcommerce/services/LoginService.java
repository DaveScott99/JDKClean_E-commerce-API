/*
package com.jdkclean.jdkcommerce.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdkclean.jdkcommerce.config.security.JWTGenerator;
import com.jdkclean.jdkcommerce.config.security.JWTObject;
import com.jdkclean.jdkcommerce.config.security.SecurityConfig;
import com.jdkclean.jdkcommerce.dto.LoginDTO;
import com.jdkclean.jdkcommerce.dto.Session;
import com.jdkclean.jdkcommerce.dto.UserLoginDTO;
import com.jdkclean.jdkcommerce.entities.Role;
import com.jdkclean.jdkcommerce.entities.UserEntity;
import com.jdkclean.jdkcommerce.repositories.UserRepository;
import com.jdkclean.jdkcommerce.services.exceptions.LoginException;

public class LoginService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	public Session login(LoginDTO login) {
		
		UserEntity user = repository.findByEmail(login.getUsername());
		
		if (user != null) {
			boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
			
			if (!passwordOk) {
				throw new LoginException("Senha inválida");
			}
			
			UserLoginDTO loginUser = new UserLoginDTO(user);
			
			Session session = new Session();
			session.setUser(loginUser);

			JWTObject jwtObject = new JWTObject();
			jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
			jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));	
			
			for (Role role : user.getRoles()) {
				jwtObject.getRoles().add(role.getAuthority().toString());
			}

			session.setToken(JWTGenerator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
			return session;
		} 
		else {
			throw new LoginException("Email ou senha incorretos");
		}
	}

}
*/