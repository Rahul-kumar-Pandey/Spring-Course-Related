package com.socialmedia.socialApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuAddress {
	@Id
	private int addId;
	
	private String city;
	
	private String state;
	
	@ManyToOne
	private Student student;
}
