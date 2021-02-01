package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exception.UserDetailsException;
import com.example.entity.UserInfo;
import com.example.entity.Users;
import com.example.repository.UserRepository;

@Service
public class UserInfoServiceImplementation implements UserInfoService{
		@Autowired
	UserRepository userRepo;

	@Override
	public UserInfo saveUserDetails(UserInfo user) throws UserDetailsException {
		// TODO Auto-generated method stub
		UserInfo userDetails = new UserInfo();
		userDetails=userAlreadyExists(user);
		System.out.println("userDetails " +userDetails.toString());
		if(userDetails == null) {
			return userRepo.save(user);
		}
		
		return userDetails;
	}
	
	public UserInfo userAlreadyExists(UserInfo user) throws UserDetailsException {
		System.out.println(userRepo.findById(user.getPhNo()));
		return userRepo.findById(user.getPhNo()).orElse(null);
		
	}

	@Override
	public List<UserInfo> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

}
