package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.UserDetailsException;
import com.example.entity.UserInfo;
import com.example.entity.Users;
@Service
public interface UserInfoService {
	
	UserInfo saveUserDetails(UserInfo user) throws UserDetailsException;
	
	List<UserInfo> getAllUser();

}
