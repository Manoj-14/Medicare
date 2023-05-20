package com.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmail(String email);
	boolean existsByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	boolean existsByEmailAndPassword(String email,String password);
}
