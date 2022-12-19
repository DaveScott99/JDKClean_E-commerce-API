package com.jdkclean.jdkcommerce.dto;

import com.jdkclean.jdkcommerce.entities.User;

public class UserLoginDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	
	public UserLoginDTO() {
	}
	
	public UserLoginDTO(Long id, String firstName, String lastName, String username) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
	
	public UserLoginDTO(User entity) {
		id = entity.getId();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		username = entity.getUsername();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
