package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Admin;
import com.project.exception.UserNotFoundException;
import com.project.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("signup")
	public String signUp(@ModelAttribute Admin admin) {
		return adminService.save(admin);
	}
	
	
	@RequestMapping("login")
	public Object authentication(@RequestParam String email,@RequestParam String password) {
		try {
			return adminService.getAdmin(email, password);
		} catch (UserNotFoundException e) {
			return new String("User Not Found");
		}
	}
	
	@PutMapping("changepassword")
	public String changePassword(@RequestParam String email ,@RequestParam String password,@RequestParam String newPassword ) {
		try {
			adminService.changePassword(email, password, newPassword);
			return "Password changed" ;
		} catch (UserNotFoundException e) {
			return "Please Check Email and Password";
		}
	}
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid credentials")
	@RequestMapping("error")
	public String error() {
		return "Unauthorized Access" ;
	}
}
