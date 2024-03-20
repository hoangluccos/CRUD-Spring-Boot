package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.models.Departs;

public interface DepartService {

	void deleteAll();

	void deleteAll(List<Departs> entities);

	void deleteAllById(List<String> ids);

	void delete(Departs entity);

	void deleteById(String id);

	long count();

	List<Departs> findAllById(List<String> ids);

	List<Departs> findAll();

	boolean existsById(String id);

	Optional<Departs> findById(String id);

	List<Departs> saveAll(List<Departs> entities);

	Departs save(Departs entity);

}
