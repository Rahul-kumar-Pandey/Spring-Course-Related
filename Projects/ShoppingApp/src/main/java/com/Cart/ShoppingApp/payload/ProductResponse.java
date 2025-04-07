package com.Cart.ShoppingApp.payload;

import java.util.List;

import com.Cart.ShoppingApp.model.Catagory;
import com.Cart.ShoppingApp.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
 List<ProductDto>contents;
 	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;
}
