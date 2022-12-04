package com.jdkclean.jdkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdkclean.jdkcommerce.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
