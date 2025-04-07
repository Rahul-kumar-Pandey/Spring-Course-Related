package com.socialmedia.socialApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.socialmedia.socialApp.Model.SocialGroup;
import com.socialmedia.socialApp.Model.SocialProfile;
import com.socialmedia.socialApp.Model.SocialUser;
import com.socialmedia.socialApp.Model.UserPost;
import com.socialmedia.socialApp.repos.PostRepository;
import com.socialmedia.socialApp.repos.profileRepo;
import com.socialmedia.socialApp.repos.socialGroupRepo;
import com.socialmedia.socialApp.repos.userRepo;

@Configuration
public class DataInitializer {
	   private final userRepo userRepository;
	    private final socialGroupRepo groupRepository;
	    private final profileRepo socialProfileRepository;
	    private final PostRepository postRepository;

	    public DataInitializer(userRepo userRepository, socialGroupRepo groupRepository, profileRepo socialProfileRepository, PostRepository postRepository) {
	        this.userRepository = userRepository;
	        this.groupRepository = groupRepository;
	        this.socialProfileRepository = socialProfileRepository;
	        this.postRepository = postRepository;
	    }

	    @Bean
	    public CommandLineRunner initializeData() {
	        return args -> {
	            // Create some users
	            SocialUser user1 = new SocialUser();
	            SocialUser user2 = new SocialUser();
	            SocialUser user3 = new SocialUser();

	            // Save users to the database
	            userRepository.save(user1);
	            userRepository.save(user2);
	            userRepository.save(user3);

	            // Create some groups
	            SocialGroup group1 = new SocialGroup();
	            SocialGroup group2 = new SocialGroup();

	            // Add users to groups
	            group1.getUsers().add(user1);
	            group1.getUsers().add(user2);

	            group2.getUsers().add(user2);
	            group2.getUsers().add(user3);

	            // Save groups to the database
	            groupRepository.save(group1);
	            groupRepository.save(group2);

	            // Associate users with groups
	            user1.getGroups().add(group1);
	            user2.getGroups().add(group1);
	            user2.getGroups().add(group2);
	            user3.getGroups().add(group2);

	            // Save users back to database to update associations
	            userRepository.save(user1);
	            userRepository.save(user2);
	            userRepository.save(user3);


	            // Create some posts
	            UserPost post1 = new UserPost();
	            UserPost post2 = new UserPost();
	            UserPost post3 = new UserPost();

	            // Associate posts with users
	            post1.setSocialUser(user1);
	            post2.setSocialUser(user2);
	            post3.setSocialUser(user3);

	            // Save posts to the database (assuming you have a PostRepository)
	             postRepository.save(post1);
	             postRepository.save(post2);
	             postRepository.save(post3);

	            // Create some social profiles
	            SocialProfile profile1 = new SocialProfile();
	            SocialProfile profile2 = new SocialProfile();
	            SocialProfile profile3 = new SocialProfile();

	            // Associate profiles with users
	            profile1.setSocialUser(user1);
	            profile2.setSocialUser(user2);
	            profile3.setSocialUser(user3);

	            // Save profiles to the database (assuming you have a SocialProfileRepository)
	            socialProfileRepository.save(profile1);
	            socialProfileRepository.save(profile2);
	            socialProfileRepository.save(profile3);
	        };
	    }
}	
