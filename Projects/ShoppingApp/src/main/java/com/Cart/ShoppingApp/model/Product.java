package com.Cart.ShoppingApp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private String productDesc;
	private Integer quantity;
	private double price;
	private double specialPrice;
	private String productImage;
	@ManyToOne
	@JoinColumn(name="catId")
	private Catagory catagory;
	
	@ManyToOne
	@JoinColumn(name="seller_id")
	private User user;
}
