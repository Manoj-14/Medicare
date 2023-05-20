package com.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
	Medicine findById(int id);
	boolean existsById(int Id);
	List<Medicine> findByIdOrderByNameAsc(int id);
}
