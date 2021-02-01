package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.ProdDetailsNotFound;
import com.example.Exception.ProductAlreadyPresentException;
import com.example.entity.Products;

@Service
public interface ProductService {
	
	List<Products> saveProductService(List<Products> prod) throws ProductAlreadyPresentException;
	
	List<Products> showAllProduct();
	
	Products getByProductId(int prodId) throws ProdDetailsNotFound;
	
	Products updateProductInfo(int prodId , Products prod);
	

}
