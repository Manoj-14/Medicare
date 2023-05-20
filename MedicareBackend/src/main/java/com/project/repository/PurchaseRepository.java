package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
