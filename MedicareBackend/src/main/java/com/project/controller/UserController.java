package com.project.controller;

import java.util.List;

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
import com.project.entity.Cart;
import com.project.entity.User;
import com.project.exception.EntityNotFoundException;
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

	@PostMapping("login")
	public ResponseEntity<?> authentication(@RequestBody User user) {
		try {
			User dbuser = userService.getUser(user.getEmail(),user.getPassword());
			return ResponseEntity.ok(dbuser);
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("dashboard")
	public ResponseEntity<?> dashboard(@RequestParam String email){
		try {
			User user = userService.getUser(email);
			return ResponseEntity.ok(user);
		} catch (UserNotFoundException e) {		
			return ResponseEntity.notFound().build();
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

	@PostMapping("addToCart")
	public ResponseEntity<?> addtoCart(@RequestParam String email, @RequestParam int medicine_id,@RequestParam int quantity) {
		try {
			userService.addMedicineToCart(email, medicine_id,quantity);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("removeFromCart")
	public boolean removeFromCart(@RequestParam String email, @RequestParam int medicineId) {
		try {
			userService.removeMedicineToCart(email,medicineId);
			return true;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("purchaceMedicine")
	public ResponseEntity<?> purchaceMedicine(@RequestParam String email, @RequestParam int medicine_id,@RequestParam int quantity,@RequestParam int amount) {
		System.out.println(email+" "+medicine_id+" "+amount+""+quantity);
		try {
			userService.buyMedicine(email, medicine_id,amount,quantity);
			return ResponseEntity.accepted().build();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

//	@PostMapping("purchaceMedicines")
//	public boolean purchaceMedicine(@RequestParam String email, @RequestParam List<Integer> medicine_ids) {
//		 try {
//			userService.buyMedicineToCart(email, medicine_ids);
//			return true;
//		} catch (UserNotFoundException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid credentials")
	@RequestMapping("error")
	public String error() {
		return "Unauthorized Access";
	}
}
