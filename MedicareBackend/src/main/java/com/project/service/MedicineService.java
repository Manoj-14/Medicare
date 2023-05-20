package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Medicine;
import com.project.exception.EntityNotCreatedException;
import com.project.exception.EntityNotFoundException;
import com.project.repository.MedicineRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicineService {
	
	@Autowired
	MedicineRepository medicineRepo ;
	
	@Transactional
	public void create(Medicine medicine) throws EntityNotCreatedException {
		try {
			medicineRepo.save(medicine);
		}catch (Exception e) {
			throw new EntityNotCreatedException();
		}
	}
	
	@Transactional
	public void update(Medicine medicine) throws EntityNotFoundException {
		try {
			medicineRepo.save(medicine);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void enableMedicine(int id) throws EntityNotFoundException {
		if(!medicineRepo.existsById(id)) {
			throw new EntityNotFoundException();
		}
		else {
			Medicine medicine = medicineRepo.findById(id);
			medicine.setActive((medicine.isActive())? false:true);
			medicineRepo.save(medicine);
		}
	}
	
	@Transactional
	public void removeMedicine(int id) throws EntityNotFoundException {
		if(!medicineRepo.existsById(id)) {
			throw new EntityNotFoundException();
		}
		else {
			Medicine medicine = medicineRepo.findById(id);
			medicineRepo.delete(medicine);			
		}
	}
	
	public Medicine getMedicine(int id) throws EntityNotFoundException {
		if(!medicineRepo.existsById(id)) {
			throw new EntityNotFoundException();
		}
		else {
			return medicineRepo.findById(id);	
		}
	}
	
	public List<Medicine> getMedicines(int id) throws EntityNotFoundException {
		if(!medicineRepo.existsById(id)) {
			throw new EntityNotFoundException();
		}
		else {
			return (List<Medicine>) medicineRepo.findAll();	
		}
	}
}
