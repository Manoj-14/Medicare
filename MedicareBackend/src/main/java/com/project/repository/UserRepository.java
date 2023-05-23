package com.project.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Cart;
import com.project.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmail(String email);
	boolean existsByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	boolean existsByEmailAndPassword(String email,String password);
	boolean existsByEmailAndCart_Medicines_Id(String Email,int medicineId);
	
	@Query("SELECT cart FROM User user JOIN user.cart cart JOIN cart.medicines medicine WHERE user.email = :userEmail AND medicine.id = :medicineId")
    Cart findCartByEmailAndMedicineId(String userEmail, int medicineId);
	

	
}
