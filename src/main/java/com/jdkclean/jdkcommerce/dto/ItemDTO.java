package com.jdkclean.jdkcommerce.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long productId;
	private Integer quantity;
	private Long cartId;
	
	public ItemDTO() {
	}

	public ItemDTO(Long productId, Integer quantity, Long cartId) {
		this.productId = productId;
		this.quantity = quantity;
		this.cartId = cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

}
