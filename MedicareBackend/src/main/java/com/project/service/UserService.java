package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Medicine;
import com.project.entity.Purchase;
import com.project.entity.User;
import com.project.exception.EntityNotFoundException;
import com.project.exception.UserNotFoundException;
import com.project.repository.PurchaseRepository;
import com.project.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private PurchaseRepository purchaseRepo;

	@Transactional
	public String save(User user) {
		if (userRepo.existsByEmail(user.getEmail())) {
			return user.getEmail() + " Already Exists";
		} else {
			userRepo.save(user);
			return user.getEmail() + " Created Successfully";
		}
	}

	
	public boolean existsEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	public boolean authenticate(String email, String password) {
		return userRepo.existsByEmailAndPassword(email, password);
	}

	public User getUser(String email, String password) throws UserNotFoundException {

		if (!authenticate(email, password)) {
			throw new UserNotFoundException();
		} else {
			return userRepo.findByEmailAndPassword(email, password);
		}
	}

	@Transactional
	public boolean changePassword(String email, String password, String newPassword) throws UserNotFoundException {
		if (!authenticate(email, password)) {
			throw new UserNotFoundException();
		} else {
			User user = userRepo.findByEmail(email);
			user.setPassword(newPassword);
			userRepo.save(user);
			return true;
		}
	}
	
	@Transactional
	public boolean addMedicineToCart(String email , int medicine_id) throws UserNotFoundException {
		if(!userRepo.existsByEmail(email)){
			throw new UserNotFoundException();
		}else {
			User user = userRepo.findByEmail(email);
			try {
				Medicine medicine = medicineService.getMedicine(medicine_id);
				user.getCart().getMedicines().add(medicine);
			} catch (EntityNotFoundException e) {
				return false;
			}
			userRepo.save(user);
			return true;
		}
	}
	
	@Transactional
	public boolean removeMedicineToCart(String email , int medicine_id) throws UserNotFoundException {
		if(!userRepo.existsByEmail(email)){
			throw new UserNotFoundException();
		}else {
			User user = userRepo.findByEmail(email);
			try {
				Medicine medicine = medicineService.getMedicine(medicine_id);
				user.getCart().getMedicines().remove(medicine);
			} catch (EntityNotFoundException e) {
				return false;
			}
			userRepo.save(user);
			return true;
		}
	}
	
	@Transactional
	public boolean buyMedicineToCart(String email , int medicine_id) throws UserNotFoundException {
		if(!userRepo.existsByEmail(email)){
			throw new UserNotFoundException();
		}else {
			User user = userRepo.findByEmail(email);
			try {
				Medicine medicine = medicineService.getMedicine(medicine_id);
				Purchase purchase = new Purchase(user, medicine);
				purchaseRepo.save(purchase);
			} catch (EntityNotFoundException e) {
				return false;
			}
			return true;
		}
	}
	
	@Transactional
	public boolean buyMedicineToCart(String email , List<Integer> medicine_ids) throws UserNotFoundException {
		if(!userRepo.existsByEmail(email)){
			throw new UserNotFoundException();
		}else {
			User user = userRepo.findByEmail(email);
			try {
				Medicine medicine;
				Purchase purchase;
				for(int medicine_id : medicine_ids) {					
					medicine = medicineService.getMedicine(medicine_id);
					purchase = new Purchase(user, medicine);
					purchaseRepo.save(purchase);
				}
			} catch (EntityNotFoundException e) {
				return false;
			}
			return true;
		}
	}
}
