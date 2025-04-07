package com.socialmedia.socialApp.Model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupId;
	
	
	@ManyToMany
	@JsonIgnore
	private Set<SocialUser>users=new HashSet<>();
	
	@Override
    public int hashCode(){
        return Objects.hash(groupId);
    }
}
