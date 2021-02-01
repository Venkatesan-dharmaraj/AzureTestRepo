package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Users;

public interface LoginDetailsRepository extends JpaRepository<Users, String> {

	  Users findByUsername(String username);
	 

}
