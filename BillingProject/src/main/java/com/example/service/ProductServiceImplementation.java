package com.example.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.example.Exception.ProdDetailsNotFound;
import com.example.Exception.ProductAlreadyPresentException;
import com.example.entity.Products;
import com.example.repository.ProductsRepository;

@Service
public class ProductServiceImplementation implements ProductService{
	
	@Autowired
	ProductsRepository prodRepo;
	
	
	List<Products> getAllProducts = new ArrayList<>();
	List<Products> productsSaved = new ArrayList<>();

	@Override
	public List<Products> saveProductService(List<Products> prod) throws ProductAlreadyPresentException {
		
		getAllProducts = showAllProduct();
		
		if(!getAllProducts.isEmpty()) {
			for(int i=0;i<prod.size();i++) {
				if(getAllProducts.get(i).getProductName().equalsIgnoreCase(prod.get(i).getProductName())) {
					throw new ProductAlreadyPresentException("Product is already present , Please enter new product name");
				}
			}
		}
		return prodRepo.saveAll(prod) ;
	}

	@Override
	@Retryable(value = { SQLException.class }, maxAttempts = 3)
	public List<Products> showAllProduct() {
		return prodRepo.findAll();
	}
	
	@Recover
    public String recover(SQLException t){
        return "Issue with database";
    }

	@Override
	public Products getByProductId(int prodId) throws ProdDetailsNotFound {
		return prodRepo.findById(prodId).orElseThrow(() -> new ProdDetailsNotFound("Please provide correct product Id"));
	}

	@Override
	public Products updateProductInfo(int prodId,Products newProductDetails) {
		// TODO Auto-generated method stub
		Products prod = prodRepo.findById(prodId).get();
		if(prod != null) {
			prod.setProductID(prodId);
			prod.setProductName(newProductDetails.getProductName() ==null ? prod.getProductName() : newProductDetails.getProductName());
			prod.setDescription(newProductDetails.getDescription() == null ? prod.getDescription() : newProductDetails.getDescription());
			prod.setPrice(newProductDetails.getPrice() ==null ? prod.getPrice() : newProductDetails.getPrice());
			prod.setSize(newProductDetails.getSize() == null ?prod.getSize():newProductDetails.getSize());
			prodRepo.save(prod);
		}
		
		return prod;
		
	}
}
