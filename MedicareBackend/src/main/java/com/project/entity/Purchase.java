package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@ManyToOne
	User user;
	
	@ManyToOne
	Medicine medicine;
	
	public Purchase() {
		
	}

	public Purchase(User user, Medicine medicine) {
		super();
		this.user = user;
		this.medicine = medicine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	
	
	
}
