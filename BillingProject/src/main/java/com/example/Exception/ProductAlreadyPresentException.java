package com.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyPresentException extends RuntimeException  {
	
	public ProductAlreadyPresentException(String msg){
		super(msg);
	}

}
