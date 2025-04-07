package com.Cart.ShoppingApp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Long productId;
	private String productName;
	private String productDesc;
	private Integer quantity;
	private double price;
	private double specialPrice;
	private String productImage;
}
