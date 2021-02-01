package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exception.ProdDetailsNotFound;
import com.example.Exception.UserDetailsException;
import com.example.entity.Billing;
import com.example.service.BillingService;

@RestController
@Validated
public class BillingController {
	
	@Autowired
	BillingService billService;
	
	@PostMapping("/saveBill")
	public ResponseEntity<Object> saveBillController(@RequestBody Billing bill) throws ProdDetailsNotFound, UserDetailsException{
		System.out.println(bill.toString());
		System.out.println("Products " +bill.getOrderProducts());
		return new ResponseEntity<Object>(billService.saveBill(bill), HttpStatus.CREATED ); 
		
	}
	
	@GetMapping("/getBillByUser")
	public ResponseEntity<Object> getBillByUserController() throws ProdDetailsNotFound, UserDetailsException{
		return new ResponseEntity<Object>("Get Bills", HttpStatus.CREATED ); 
		
	}
	

}
