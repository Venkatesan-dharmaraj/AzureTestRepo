package com.example.service;

import org.springframework.stereotype.Service;

import com.example.Exception.ProdDetailsNotFound;
import com.example.Exception.UserDetailsException;
import com.example.entity.Billing;
@Service
public interface BillingService {
	
	Billing saveBill (Billing bill) throws ProdDetailsNotFound, UserDetailsException;
	
	Billing getBill(int billno);

}
