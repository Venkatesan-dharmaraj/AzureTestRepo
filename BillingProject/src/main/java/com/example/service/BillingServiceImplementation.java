package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exception.ProdDetailsNotFound;
import com.example.Exception.UserDetailsException;
import com.example.entity.Billing;
import com.example.entity.Products;
import com.example.entity.UserInfo;
import com.example.repository.BillingRepository;

@Service
public class BillingServiceImplementation implements BillingService{
	
	@Autowired
	BillingRepository billingRepo;
	
	@Autowired
	UserInfoService userService;
	
	@Autowired
	ProductService prodService;

	@Override
	public Billing saveBill (Billing bill) throws ProdDetailsNotFound, UserDetailsException {
		UserInfo userInfo = new UserInfo();
		List<Products> prod = new ArrayList<Products>();
		List<Integer> prodId = new ArrayList<Integer>();
		List<UserInfo> registeredUsers = new ArrayList<UserInfo>();
		
		IntStream.range(0, bill.getOrderProducts().size())
        .forEach(index -> prodId.add(bill.getOrderProducts().get(index).getProductID()));
		
		System.out.println("prodId " +prodId.toString());
		for(int pId : prodId) {
			
			System.out.println("pId " +pId);
			Products products = new Products();
			products=prodService.getByProductId(pId);
			prod.add(products);
		}
		
		System.out.println("prod " +prod.toString());
		
		bill.setOrderProducts(prod);
		registeredUsers = userService.getAllUser();
		
		for(UserInfo oneByOne : registeredUsers) {
			if(oneByOne.getPhNo()!= bill.getUserphno().getPhNo()) {
				userInfo.setName(bill.getUserphno().getName());
				userInfo.setPhNo(bill.getUserphno().getPhNo());
				userInfo.setCountry(bill.getUserphno().getCountry());
				userInfo.setCity(bill.getUserphno().getCity());
				userService.saveUserDetails(userInfo);
			}
		}
		return billingRepo.save(bill);
	}


	@Override
	public Billing getBill(int billno) {
		// TODO Auto-generated method stub
		return null;
	}

}
