package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="billing")
public class Billing implements Serializable {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	int billno;
	
	@OneToOne(fetch =FetchType.LAZY )   
	@JoinColumn(name = "phone", referencedColumnName = "phNo")
	UserInfo userphno;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "orderProducts", joinColumns = { @JoinColumn(name = "billno") }, inverseJoinColumns = { @JoinColumn(name = "productID") })
	private List<Products> orderProducts = new ArrayList<Products>();
	
	String paymentType;
	
	@Temporal(TemporalType.DATE)
	Date billingDate;
	
	
	public Billing(int billno, UserInfo userphno, Set<Products> productsOrdered, String paymentType) {
		super();
		this.billno = billno;
		this.userphno = userphno;
		this.paymentType = paymentType;
	}
	

	public Billing() {
		super();
	}


	public int getBillno() {
		return billno;
	}

	public void setBillno(int billno) {
		this.billno = billno;
	}

	public UserInfo getUserphno() {
		return userphno;
	}

	public void setUserphno(UserInfo userphno) {
		this.userphno = userphno;
	}


	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	


	public List<Products> getOrderProducts() {
		return orderProducts;
	}


	public void setOrderProducts(List<Products> orderProducts) {
		this.orderProducts = orderProducts;
	}


	@Override
	public String toString() {
		return "Billing [billno=" + billno + ", userphno=" + userphno + ", orderProducts=" + orderProducts
				+ ", paymentType=" + paymentType + "]";
	}


	
	
	
	

}
