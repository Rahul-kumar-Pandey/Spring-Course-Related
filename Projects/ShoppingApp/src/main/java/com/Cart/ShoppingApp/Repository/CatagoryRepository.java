package com.Cart.ShoppingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cart.ShoppingApp.model.Catagory;



public interface CatagoryRepository extends JpaRepository<Catagory,Long>{

	Catagory findByName(String name);

}
