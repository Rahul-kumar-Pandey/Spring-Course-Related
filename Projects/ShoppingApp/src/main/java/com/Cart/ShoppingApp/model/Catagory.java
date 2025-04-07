package com.Cart.ShoppingApp.model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;



@Entity
public class Catagory {
	public Catagory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Catagory(Long catId, String name, List<Product> products) {
		super();
		this.catId = catId;
		this.name = name;
		this.products = products;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long catId;
	
	@NotBlank
	private String name;
	
	
	@OneToMany(mappedBy = "catagory")
	private List<Product>products;



	public Catagory(String name, List<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}


	public Long getCatId() {
		return catId;
	}


	public void setCatId(Long catId) {
		this.catId = catId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
