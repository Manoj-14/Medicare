package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Admin;
import com.project.exception.UserNotFoundException;
import com.project.service.AdminService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	
	@Autowired
	AdminService adminService;

	@PostMapping("signup")
	public String signUp(@ModelAttribute Admin admin) {
		return adminService.save(admin);
	}

	@PostMapping("login")
	public ResponseEntity<?> authentication(@RequestBody Admin admin, HttpSession session,HttpServletResponse response) {
		try {
			Admin dbAdmin = adminService.getAdmin(admin.getEmail(), admin.getPassword());
			return ResponseEntity.ok(dbAdmin);
		} catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().body("Login failed");
		}
	}

	@PostMapping("dashboard")
	public ResponseEntity<?> getDashboard(@RequestBody String email,HttpServletRequest request) {
		if (email != null) {
			Admin admin = adminService.getAdminByEmail(email);
			return ResponseEntity.ok(admin);
		} else {
			return ResponseEntity.badRequest().body("Unauthorized");
		}
	}

	@PutMapping("changepassword")
	public String changePassword(@RequestParam String email, @RequestParam String password,
			@RequestParam String newPassword) {
		try {
			adminService.changePassword(email, password, newPassword);
			return "Password changed";
		} catch (UserNotFoundException e) {
			return "Please Check Email and Password";
		}
	}
	
	

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid credentials")
	@RequestMapping("error")
	public String error() {
		return "Unauthorized Access";
	}
}
