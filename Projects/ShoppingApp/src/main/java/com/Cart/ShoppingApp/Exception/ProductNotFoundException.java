package com.Cart.ShoppingApp.Exception;


public class ProductNotFoundException extends RuntimeException{
	public ProductNotFoundException(String message) {
		super(message);
	}
}
