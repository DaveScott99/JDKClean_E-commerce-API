package com.jdkclean.jdkcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdkclean.jdkcommerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
