package com.socialmedia.socialApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.socialApp.Model.SocialUser;

public interface userRepo extends JpaRepository<SocialUser, Long>{

}
