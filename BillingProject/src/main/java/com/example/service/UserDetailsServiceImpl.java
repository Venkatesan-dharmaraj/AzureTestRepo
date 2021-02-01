package com.example.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.MyUserDetails;
import com.example.entity.Roles;
import com.example.entity.Users;
import com.example.repository.LoginDetailsRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	LoginDetailsRepository repo;
	
	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		@Override
		    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		     System.out.println("username "+username);   
		     Users user = Optional.ofNullable(repo.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User was not found"));
			 System.out.println("user "+user.toString()); 
			 
			 
		     List<GrantedAuthority> authorityList = user.getRoles().stream()
		                    .map(role -> new SimpleGrantedAuthority(role.getName()))
		                    .collect(Collectors.toList());
		        	
		     System.out.println("authorityList " +authorityList.toString());
		     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorityList);

		    }
		 
		 
		 
		
}