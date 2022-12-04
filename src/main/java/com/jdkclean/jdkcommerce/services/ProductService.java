package com.jdkclean.jdkcommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdkclean.jdkcommerce.dto.ProductDTO;
import com.jdkclean.jdkcommerce.entities.Product;
import com.jdkclean.jdkcommerce.repositories.ProductRepository;
import com.jdkclean.jdkcommerce.services.exceptions.ControllerNotFoundException;
import com.jdkclean.jdkcommerce.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ControllerNotFoundException("Categoria não encontrada"));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		//entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			//entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ProductDTO(entity);
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
	
}
