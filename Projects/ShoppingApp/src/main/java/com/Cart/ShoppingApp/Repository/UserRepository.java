package com.Cart.ShoppingApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cart.ShoppingApp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User>findByUserName(String userName);

	boolean existsByUserName(String username);

	boolean existsByEmail(String email);
}
