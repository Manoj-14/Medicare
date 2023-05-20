package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Admin;
import com.project.exception.UserNotFoundException;
import com.project.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;

	@Transactional
	public String save(Admin admin) {
		if (adminRepo.existsByEmail(admin.getEmail())) {
			return admin.getEmail() + " Already Exists";
		} else {
			adminRepo.save(admin);
			return admin.getEmail() + " Created Successfully";
		}
	}

	
	public boolean existsEmail(String email) {
		return adminRepo.existsByEmail(email);
	}

	public boolean authenticate(String email, String password) {
		return adminRepo.existsByEmailAndPassword(email, password);
	}

	public Admin getAdmin(String email, String password) throws UserNotFoundException {

		if (!authenticate(email, password)) {
			throw new UserNotFoundException();
		} else {
			return adminRepo.findByEmailAndPassword(email, password);
		}
	}

	@Transactional
	public boolean changePassword(String email, String password, String newPassword) throws UserNotFoundException {
		if (!authenticate(email, password)) {
			throw new UserNotFoundException();
		} else {
			Admin admin = adminRepo.findByEmail(email);
			admin.setPassword(newPassword);
			adminRepo.save(admin);
			return true;
		}
	}
}
