package com.Cart.ShoppingApp.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatagoryDto {
	private Long catId;
	
	@NotBlank
	private String name;
}
