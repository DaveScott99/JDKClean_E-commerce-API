package com.jdkclean.jdkcommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jdkclean.jdkcommerce.dto.CartDTO;
import com.jdkclean.jdkcommerce.dto.ItemDTO;
import com.jdkclean.jdkcommerce.entities.Cart;
import com.jdkclean.jdkcommerce.entities.Item;
import com.jdkclean.jdkcommerce.services.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

	@Autowired
	private CartService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<CartDTO> findById(@PathVariable Long id) {
		CartDTO cart = service.findById(id);
		return ResponseEntity.ok().body(cart);
	}
	
	@PostMapping
	public ResponseEntity<Item> addItem(@RequestBody ItemDTO itemDto) {
		Item item = service.addItem(itemDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(uri).body(item);
	}
	
	@PatchMapping("/closeCart/{id}")
	public ResponseEntity<Cart> closeCart(@PathVariable("id") Long id, @RequestParam("formPayment") Integer formPayment) {
		Cart cart = service.closeCart(id, formPayment);
		return ResponseEntity.ok().body(cart);
	}
	
}
