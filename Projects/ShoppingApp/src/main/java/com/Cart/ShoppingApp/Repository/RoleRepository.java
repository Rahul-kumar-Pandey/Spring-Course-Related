package com.Cart.ShoppingApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cart.ShoppingApp.model.AppRole;
import com.Cart.ShoppingApp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleName(AppRole roleUser);

}
