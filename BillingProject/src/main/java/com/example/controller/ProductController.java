package com.example.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Exception.ProdDetailsNotFound;
import com.example.Exception.ProductAlreadyPresentException;
import com.example.entity.Products;
import com.example.service.ProductService;

@RestController
@Validated
@RequestMapping("/api/")
public class ProductController {
	
	@Autowired
	ProductService prodServ;
	
	RestTemplate template = new RestTemplate();
	
	@RequestMapping(value = "/check" ,method =RequestMethod.GET,consumes = "application/json")
	public ResponseEntity<String> check() {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); HttpEntity
		 * <String> entity = new HttpEntity<String>(headers); ResponseEntity<String>
		 * response = template.getForEntity("http://localhost:8099/client",
		 * String.class);
		 */
		return new ResponseEntity<String>("hi client", HttpStatus.CREATED);
		
	}
	
	@PostMapping( value = "/saveProduct" ,consumes = {MediaType.APPLICATION_JSON_VALUE}) 
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<Products> saveProductController(@Valid @RequestBody List<Products> prod) throws ProductAlreadyPresentException {
		System.out.println("Save");
		return prodServ.saveProductService(prod);
		
	}
	
	@RequestMapping(value = "/showAllProducts" ,method =RequestMethod.GET)
	public ResponseEntity<Object> showAllProduct() {
		System.out.println("Show All");
		return new ResponseEntity<Object>(prodServ.showAllProduct(), HttpStatus.OK); 
	}
	
	@PutMapping("/updateProducts/{id}")
	public ResponseEntity<Object> updateProductInfo(@PathVariable int id , @RequestBody Products prod) {
		System.out.println("Update");
		return new ResponseEntity<Object>(prodServ.updateProductInfo(id,prod), HttpStatus.OK); 
	}
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<Object> getProductInfo(@PathVariable int id ) throws ProdDetailsNotFound {
		System.out.println("get Product By Id "+id);
		return new ResponseEntity<Object>(prodServ.getByProductId(id), HttpStatus.OK); 
	}
	
	
}
