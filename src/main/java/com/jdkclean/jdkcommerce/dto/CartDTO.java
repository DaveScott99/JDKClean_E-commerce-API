package com.jdkclean.jdkcommerce.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.jdkclean.jdkcommerce.entities.Cart;
import com.jdkclean.jdkcommerce.entities.Item;
import com.jdkclean.jdkcommerce.enums.FormPayment;

public class CartDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double totalValue;
	private boolean close;
	
	private Set<Item> items =  new HashSet<>();
	
	private FormPayment formPayment;
	
	public CartDTO() { 
	}

	public CartDTO(Long id, Double totalValue, boolean close, FormPayment formPayment) {
		this.id = id;
		this.totalValue = totalValue;
		this.close = close;
		this.formPayment = formPayment;
	}
	
	public CartDTO(Cart entity) {
		id = entity.getId();
		totalValue = entity.getTotalValue();
		close = entity.isClose();
		formPayment = entity.getFormPayment();
	}
	
	public CartDTO(Cart entity, Set<Item> items) {
		this(entity);
		items.addAll(items);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public FormPayment getFormPayment() {
		return formPayment;
	}

	public void setFormPayment(FormPayment formPayment) {
		this.formPayment = formPayment;
	}

	public Set<Item> getItems() {
		return items;
	}
	
}
