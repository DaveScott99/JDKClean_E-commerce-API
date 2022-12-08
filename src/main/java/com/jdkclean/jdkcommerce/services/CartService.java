package com.jdkclean.jdkcommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdkclean.jdkcommerce.dto.CartDTO;
import com.jdkclean.jdkcommerce.dto.ItemDTO;
import com.jdkclean.jdkcommerce.entities.Cart;
import com.jdkclean.jdkcommerce.entities.Item;
import com.jdkclean.jdkcommerce.entities.Product;
import com.jdkclean.jdkcommerce.enums.FormPayment;
import com.jdkclean.jdkcommerce.repositories.CartRepository;
import com.jdkclean.jdkcommerce.repositories.ProductRepository;
import com.jdkclean.jdkcommerce.services.exceptions.ControllerNotFoundException;

@Service
public class CartService {

	@Autowired
	private CartRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public CartDTO findById(Long id) {
		Optional<Cart> obj = repository.findById(id);
		Cart entity = obj.orElseThrow(() -> new ControllerNotFoundException("Carrinho não encontrado!"));
		return new CartDTO(entity);
	}

	@Transactional
	public Item addItem(ItemDTO itemDto) {
		Cart cart = repository.getReferenceById(itemDto.getCartId());

		if (cart.isClose()) {
			throw new RuntimeException("Esta sacola está fechada.");
		}

		Product product = productRepository.findById(itemDto.getProductId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

		Item itemForInsertToCart = new Item(itemDto.getQuantity(), product, cart);

		Set<Item> cartItens = cart.getItems();

		cartItens.add(itemForInsertToCart);
		
		List<Double> itemValues = new ArrayList<>();
		for (Item cartItem : cartItens) {
			Double totalValueItem = cartItem.getProduct().getPrice() * cartItem.getQuantity();
			itemValues.add(totalValueItem);
		}

		Double totalValueCart = itemValues.stream().mapToDouble(totalValueItem -> totalValueItem).sum();
		cart.setTotalValue(totalValueCart);

		repository.save(cart);
		return itemForInsertToCart;
	}

	@Transactional
	public Cart closeCart(Long id, int formPayment) {
		try {
			Cart cart = repository.getReferenceById(id);
			if (cart.getItems().isEmpty()) {
				throw new RuntimeException("Inclua itens na sacola!");
			}
			FormPayment payment = formPayment == 0 ? FormPayment.DINHEIRO : FormPayment.MAQUININHA;
			cart.setFormPayment(payment);
			cart.setClose(true);
			return repository.save(cart);
		} catch (ControllerNotFoundException e) {
			throw new ControllerNotFoundException("Id do carrinho não encontrado " + id);
		}
	}

}
