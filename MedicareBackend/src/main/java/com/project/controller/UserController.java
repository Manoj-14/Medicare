package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.User;
import com.project.exception.UserNotFoundException;
import com.project.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("signup")
	public String signUp(@ModelAttribute User user) {
		return userService.save(user);
	}

	@RequestMapping("login")
	public Object authentication(@RequestParam String email, @RequestParam String password) {
		try {
			return userService.getUser(email, password);
		} catch (UserNotFoundException e) {
			return new String("User Not Found");
		}
	}

	@PutMapping("changepassword")
	public String changePassword(@RequestParam String email, @RequestParam String password,
			@RequestParam String newPassword) {
		try {
			userService.changePassword(email, password, newPassword);
			return "Password changed";
		} catch (UserNotFoundException e) {
			return "Please Check Email and Password";
		}
	}

	@PutMapping("addToCart")
	public boolean addtoCart(@RequestParam String email, @RequestParam int medicine_id) {
		try {
			userService.addMedicineToCart(email, medicine_id);
			return true;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@PutMapping("removeFromCart")
	public boolean removeFromCart(@RequestParam String email, @RequestParam int medicine_id) {
		try {
			userService.removeMedicineToCart(email, medicine_id);
			return true;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("purchaceMedicine")
	public boolean purchaceMedicine(@RequestParam String email, @RequestParam int medicine_id) {
		try {
			userService.buyMedicineToCart(email, medicine_id);
			return true;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@PostMapping("purchaceMedicines")
	public boolean purchaceMedicine(@RequestParam String email, @RequestParam List<Integer> medicine_ids) {
		 try {
			userService.buyMedicineToCart(email, medicine_ids);
			return true;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid credentials")
	@RequestMapping("error")
	public String error() {
		return "Unauthorized Access";
	}
}
