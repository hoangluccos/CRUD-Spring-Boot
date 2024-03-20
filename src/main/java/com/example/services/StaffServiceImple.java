package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Departs;
import com.example.models.Staffs;
import com.example.repositories.DepartRepository;
import com.example.repositories.StaffRepository;

@Service

public class StaffServiceImple implements StaffService {
	@Autowired
	StaffRepository staffepository;

	@Autowired
	DepartRepository departRepository;
	 
	@Override
	public List<Departs> findAllDeparts(){
		return (List<Departs>)departRepository.findAll();
	}
	
	@Override
	public Staffs save(Staffs entity) {
		return staffepository.save(entity);
	}

	@Override
	public List<Staffs> saveAll(List<Staffs> entities) {
		return (List<Staffs>)staffepository.saveAll(entities);
	}

	@Override
	public Optional<Staffs> findById(String id) {
		return staffepository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return staffepository.existsById(id);
	}

	@Override
	public List<Staffs> findAll() {
		return (List<Staffs>)staffepository.findAll();
	}

	@Override
	public List<Staffs> findAllById(List<String> ids) {
		return (List<Staffs>)staffepository.findAllById(ids);
	}

	@Override
	public long count() {
		return staffepository.count();
	}

	@Override
	public void deleteById(String id) {
		staffepository.deleteById(id);
	}

	@Override
	public void delete(Staffs entity) {
		staffepository.delete(entity);
	}

	@Override
	public void deleteAllById(List<String> ids) {
		staffepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(List<Staffs> entities) {
		staffepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		staffepository.deleteAll();
	}
	
	
}
