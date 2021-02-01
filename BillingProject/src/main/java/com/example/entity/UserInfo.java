package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
@Table(name="userInfo")
public class UserInfo implements Serializable  {
	
	@NotNull(message="Please enter name of the customer")
	String name;
	@Id
	@Size(min=10,max=10)
	long phNo;
	
	@NotNull(message="Please enter city of the customer")
	String city;
	
	@NotNull(message="Please enter state of the customer")
	String state;
	
	@NotNull(message="Please enter country of the customer")
	String country;
	
	@OneToOne(fetch =FetchType.LAZY )   
	@JoinColumn(name = "UserLogin", referencedColumnName = "user_id")
	Users user;

	
	
	public UserInfo() {
		super();
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public long getPhNo() {
		return phNo;
	}



	public void setPhNo(long phNo) {
		this.phNo = phNo;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", phNo=" + phNo + ", city=" + city + ", state=" + state + ", country="
				+ country + ", user=" + user + "]";
	}
	
	
	

}
