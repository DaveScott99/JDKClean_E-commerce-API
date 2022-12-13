package com.jdkclean.jdkcommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdkclean.jdkcommerce.dto.RoleDTO;
import com.jdkclean.jdkcommerce.dto.UserDTO;
import com.jdkclean.jdkcommerce.dto.UserInsertDTO;
import com.jdkclean.jdkcommerce.dto.UserUpdateDTO;
import com.jdkclean.jdkcommerce.entities.Role;
import com.jdkclean.jdkcommerce.entities.User;
import com.jdkclean.jdkcommerce.repositories.RoleRepository;
import com.jdkclean.jdkcommerce.repositories.UserRepository;
import com.jdkclean.jdkcommerce.services.exceptions.ControllerNotFoundException;
import com.jdkclean.jdkcommerce.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CartService cartService;
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ControllerNotFoundException("Usuário não encontrada"));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		cartService.newCart(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserUpdateDTO dto) {
		try {
			User entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);
		}
		catch (EntityNotFoundException e ) {
			throw new ControllerNotFoundException("Id não encontrado " + id);
		}
		
	}

	public void delete(Long id) {

		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ControllerNotFoundException("Id não encontrado " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
		
	}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		
		entity.getRoles().clear();
		
		for(RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.getReferenceById(roleDto.getId());
			entity.getRoles().add(role);
		}
		
	}
	
}
