package com.Cart.ShoppingApp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	private String street;
	private String building;
	private String city;
	
	@NotBlank
	@Size(min=6,message="Pincode must be atleast 6 char")
	private String pincode;
	//many to many(inverse Side)
	@ManyToMany(mappedBy = "addresses")
	private List<User>users=new ArrayList<>();
	
}
