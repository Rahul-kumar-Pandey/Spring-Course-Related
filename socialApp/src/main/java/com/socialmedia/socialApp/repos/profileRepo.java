package com.socialmedia.socialApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.socialApp.Model.SocialProfile;

public interface profileRepo extends JpaRepository<SocialProfile, Long>{

}
