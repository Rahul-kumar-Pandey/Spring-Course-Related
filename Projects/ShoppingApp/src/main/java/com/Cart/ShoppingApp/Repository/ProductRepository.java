package com.Cart.ShoppingApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cart.ShoppingApp.model.Catagory;
import com.Cart.ShoppingApp.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

	List<Product> findByCatagory(Catagory catagory);

	List<Product> findByProductNameLikeIgnoreCase(String keyword);

	
}
