package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="products")
public class Products implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productID;
	
	@NotNull(message="Please enter product name")
	@Size(max = 20 , min = 3,message = "Please enter proper product name" )
	String productName;
	
	String description;
	
    private BigDecimal price;
	
	String size;
	
	
	public Products( int productID,
			 String productName, String description,
			 BigDecimal price,
			 String size, Billing bills) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.size = size;
	}
	
	public Products() {
		super();
	}

	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Products [productID=" + productID + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", size=" + size + "]";
	}
	

}
