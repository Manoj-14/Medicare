package com.project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Embeddable
public class Cart {
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	List<Medicine> medicines = new ArrayList<>();

	public Cart() {

	}

	public Cart(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public void addMedicine(Medicine medicine) {
		this.getMedicines().add(medicine);
	}

}
