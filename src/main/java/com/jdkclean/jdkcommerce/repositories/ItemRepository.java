package com.jdkclean.jdkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdkclean.jdkcommerce.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
}
