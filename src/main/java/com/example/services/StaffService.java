package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.models.Departs;
import com.example.models.Staffs;

public interface StaffService {

	void deleteAll();

	void deleteAll(List<Staffs> entities);

	void deleteAllById(List<String> ids);

	void delete(Staffs entity);

	void deleteById(String id);

	long count();

	List<Staffs> findAllById(List<String> ids);

	List<Staffs> findAll();

	boolean existsById(String id);

	Optional<Staffs> findById(String id);

	List<Staffs> saveAll(List<Staffs> entities);

	Staffs save(Staffs entity);

	List<Departs> findAllDeparts();

	
}
