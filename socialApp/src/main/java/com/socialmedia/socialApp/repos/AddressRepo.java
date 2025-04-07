package com.socialmedia.socialApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.socialApp.Model.StuAddress;

public interface AddressRepo extends JpaRepository<StuAddress,Integer>{

}
