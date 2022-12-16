package com.jdkclean.jdkcommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdkclean.jdkcommerce.dto.Login;
import com.jdkclean.jdkcommerce.dto.Session;
import com.jdkclean.jdkcommerce.services.LoginService;

@RestController
public class LoginController {
	
	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<Session> login(@RequestBody Login login) {
		return ResponseEntity.ok().body(loginService.login(login));
	}
	
}
