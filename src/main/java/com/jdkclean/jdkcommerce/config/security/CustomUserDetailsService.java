package com.jdkclean.jdkcommerce.config.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdkclean.jdkcommerce.entities.Role;
import com.jdkclean.jdkcommerce.entities.UserEntity;
import com.jdkclean.jdkcommerce.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepoRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepoRepository) {
		this.userRepoRepository = userRepoRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepoRepository.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(), mapRolesToAutorities(user.getRoles()));
	}

	private Collection<GrantedAuthority> mapRolesToAutorities(List<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
