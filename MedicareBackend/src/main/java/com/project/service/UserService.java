package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Cart;
import com.project.entity.Medicine;
import com.project.entity.Purchase;
import com.project.entity.User;
import com.project.exception.EntityNotFoundException;
import com.project.exception.UserNotFoundException;
import com.project.repository.CartRepository;
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
	
	@Autowired
	private CartRepository cartRepo;

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
	
	public User getUser(String email) throws UserNotFoundException {

		if (!existsEmail(email)) {
			throw new UserNotFoundException();
		} else {
			return userRepo.findByEmail(email);
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
	public boolean addMedicineToCart(String email , int medicine_id,int quantity) throws UserNotFoundException {
		if(!userRepo.existsByEmail(email)){
			throw new UserNotFoundException();
		}else {
			User user = userRepo.findByEmail(email);
			if(userRepo.existsByEmailAndCart_Medicines_Id(email,medicine_id)) {
				Cart cartofUser =userRepo.findCartByEmailAndMedicineId(email, medicine_id);
				cartofUser.setQuantity(cartofUser.getQuantity()+1);
			}
			else {				
				try {
					Medicine medicine = medicineService.getMedicine(medicine_id);
					Cart cart = new Cart(medicine, quantity);
					cartRepo.save(cart);
					user.getCart().add(cart);
				} catch (EntityNotFoundException e) {
					return false;
				}
			}
			userRepo.save(user);
			return true;
		}
	}
	
	@Transactional
	public boolean removeMedicineToCart(String email,int medicine_id) throws UserNotFoundException, EntityNotFoundException {
		if(!userRepo.existsByEmail(email)){
			throw new UserNotFoundException();
		}else if(!userRepo.existsByEmailAndCart_Medicines_Id(email,medicine_id)) {
			throw new EntityNotFoundException();
		}
		else {
			Cart cartofUser = userRepo.findCartByEmailAndMedicineId(email, medicine_id);
			if(cartofUser.getQuantity() > 1) {
				cartofUser.setQuantity(cartofUser.getQuantity()-1);
			}
			else {
				User user = userRepo.findByEmail(email);
				user.getCart().remove(cartofUser);
				userRepo.save(user);
			}
		}
		return false;
	}
	
	@Transactional
	public boolean buyMedicine(String email , int medicine_id , double amount,int quantity) throws UserNotFoundException {
		if(!userRepo.existsByEmail(email)){
			throw new UserNotFoundException();
		}else {
			User user = userRepo.findByEmail(email);
			try {
				System.out.println("Amount:"+amount);
				System.out.println("Quantity:"+quantity);
				Medicine medicine = medicineService.getMedicine(medicine_id);
				Purchase purchase = new Purchase(user, medicine,quantity, amount);
				purchaseRepo.save(purchase);
				user.getPurchases().add(purchase);
			} catch (EntityNotFoundException e) {
				return false;
			}
			userRepo.save(user);
			return true;
		}
	}
	
//	@Transactional
//	public boolean buyMedicineFromCart(String email , List<Integer> medicine_ids) throws UserNotFoundException {
//		if(!userRepo.existsByEmail(email)){
//			throw new UserNotFoundException();
//		}else {
//			User user = userRepo.findByEmail(email);
//			try {
//				Medicine medicine;
//				Purchase purchase;
//				for(int medicine_id : medicine_ids) {					
//					medicine = medicineService.getMedicine(medicine_id);
//					purchase = new Purchase(user, medicine);
//					purchaseRepo.save(purchase);
//					user.getPurchases().add(medicine);
//				}
//			} catch (EntityNotFoundException e) {
//				return false;
//			}
//			return true;
//		}
//	}
}
