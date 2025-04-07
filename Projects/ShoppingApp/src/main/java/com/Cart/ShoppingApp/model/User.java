package com.Cart.ShoppingApp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@NotBlank
	private String userName;

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role>roles=new HashSet<>();
	
	@OneToMany(mappedBy = "user",cascade= {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
	private Set<Product>products;
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name="user_address",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="addressId"))
	private List<Address>addresses=new ArrayList<>();
	
	public User(@NotBlank String userName, @NotBlank @Email String email, @NotBlank String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
}
