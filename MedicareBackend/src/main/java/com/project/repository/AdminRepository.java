package com.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
	Admin findByEmail(String email);
	boolean existsByEmail(String email);
	Admin findByEmailAndPassword(String email, String password);
	boolean existsByEmailAndPassword(String email,String password);
	
}