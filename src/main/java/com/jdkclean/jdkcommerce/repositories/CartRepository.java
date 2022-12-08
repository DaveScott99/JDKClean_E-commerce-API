package com.jdkclean.jdkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdkclean.jdkcommerce.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
}
