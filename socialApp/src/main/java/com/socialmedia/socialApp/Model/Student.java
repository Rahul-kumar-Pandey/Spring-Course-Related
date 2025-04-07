package com.socialmedia.socialApp.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Id
	private Long studentId;
	
	private String name;
	private String about;
	
	@OneToOne(mappedBy = "student", cascade=CascadeType.ALL)
	private Laptop laptop;
	
	
	@OneToMany(mappedBy = "student", cascade=CascadeType.ALL)
	private List<StuAddress>addressList=new ArrayList<>();
}
