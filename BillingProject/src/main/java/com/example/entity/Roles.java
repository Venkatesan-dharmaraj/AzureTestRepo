package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Roles implements Serializable {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Roles(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", name=" + name + "]";
	}
    
}