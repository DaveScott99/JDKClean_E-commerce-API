package com.jdkclean.jdkcommerce.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jdkclean.jdkcommerce.entities.UserEntity;

import jakarta.validation.constraints.NotBlank;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String firstName;
	private String lastName;

	private String username;
	
	List<RoleDTO> roles = new ArrayList<>();
	
	Set<CartDTO> carts = new HashSet<>();
	
	public UserDTO() {
	}

	public UserDTO(Long id, String firstName, String lastName, String username) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
	
	public UserDTO(UserEntity entity) {
		id = entity.getId();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		username = entity.getUsername();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
		entity.getCarts().forEach(cart -> this.carts.add(new CartDTO(cart)));
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

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public Set<CartDTO> getCarts() {
		return carts;
	}

}
