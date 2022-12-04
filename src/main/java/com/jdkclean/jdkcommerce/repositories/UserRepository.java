package com.jdkclean.jdkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdkclean.jdkcommerce.entities.Category;
import com.jdkclean.jdkcommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
