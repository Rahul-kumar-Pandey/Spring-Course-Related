package com.socialmedia.socialApp.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private SocialProfile socialProfile;
	
	
	@OneToMany(mappedBy = "socialUser")
	private List<UserPost>posts=new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="user_group_table",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="group_id"))
	private Set<SocialGroup>groups=new HashSet<>();
	
	@Override
    public int hashCode(){
        return Objects.hash(Id);
    }
}
