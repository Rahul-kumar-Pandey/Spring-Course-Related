package com.socialmedia.socialApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.socialApp.Model.UserPost;

public interface PostRepository extends JpaRepository<UserPost,Long> {

}
