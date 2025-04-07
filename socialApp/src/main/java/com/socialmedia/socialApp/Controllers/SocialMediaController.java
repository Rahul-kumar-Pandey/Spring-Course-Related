package com.socialmedia.socialApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.socialApp.Model.Laptop;
import com.socialmedia.socialApp.Model.SocialUser;
import com.socialmedia.socialApp.Model.Student;
import com.socialmedia.socialApp.repos.LaptopRepo;
import com.socialmedia.socialApp.repos.StudentRepo;
import com.socialmedia.socialApp.repos.userRepo;

@RestController
public class SocialMediaController {
	
	@Autowired
	public userRepo userRepository;
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private LaptopRepo laptopRepo;
	
	//get all users
	
	@GetMapping("/social/users")
	public List<SocialUser> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/social/users")
	public SocialUser saveSocialUsers(@RequestBody SocialUser socialUser){
		return userRepository.save(socialUser);
	}
	//save user
	@GetMapping("/test")
	public void saveStudentInfo() {
		Student student1=new Student(); 
		student1.setStudentId(1L);
		student1.setName("Ramu");
		
		Student student2=new Student(); 
		student2.setStudentId(2L);
		student2.setName("Shamu");
		
		Laptop laptop1=new Laptop();
		
		laptop1.setLaptopId(100L);
		laptop1.setModel("HP");
		laptop1.setStudent(student1);
		student1.setLaptop(laptop1);
		
		
		Laptop laptop2=new Laptop();
		
		laptop2.setLaptopId(101L);
		laptop2.setModel("Dell");
		laptop2.setStudent(student2);
		student2.setLaptop(laptop2);
		studentRepo.save(student2);
		studentRepo.save(student1);
		//laptopRepo.save(laptop);
	}
}
