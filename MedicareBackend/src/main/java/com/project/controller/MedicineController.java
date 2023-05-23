package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Medicine;
import com.project.exception.EntityNotCreatedException;
import com.project.exception.EntityNotFoundException;
import com.project.service.MedicineService;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
	
	@Autowired
	MedicineService medicineService;
	
	@PostMapping("/create")
	public boolean create(@RequestBody Medicine medicine) {
		System.out.println(medicine);
		try {
			medicineService.create(medicine);
			return true;
		} catch (EntityNotCreatedException e) {
			return false;
		}
	}
	
	@RequestMapping(path="active/{id}",method = RequestMethod.PUT)
	public boolean enableOrDisable(@PathVariable("id") int id) {
		try {
			medicineService.enableMedicine(id);
			return true ;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return false ;
		}
	}
	
	@RequestMapping(path="delete/{id}",method = RequestMethod.DELETE)
	public boolean delete(@PathVariable("id") int id) {
		try {
			medicineService.removeMedicine(id);
			return true ;
		} catch (EntityNotFoundException e) {
			return false ;
		}
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> getMedicines(@PathVariable("id") int id,@ModelAttribute Medicine medicine){
		try {
			medicineService.update(medicine);
			return ResponseEntity.accepted().build();
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("findAll")
	public ResponseEntity<?> getMedicines(){
		try {
			List<Medicine> medicines = medicineService.getMedicines();
			return ResponseEntity.ok(medicines);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("find/{id}")
	public ResponseEntity<?> getMedicine(@PathVariable("id") int id){
		try {
			Medicine medicine = medicineService.getMedicine(id);
			return ResponseEntity.ok(medicine);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Update Error")
	public void updateError() {
		
	}
	
}
