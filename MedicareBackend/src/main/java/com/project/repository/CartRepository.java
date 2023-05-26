package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {

}
