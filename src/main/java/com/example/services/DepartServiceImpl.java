package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Departs;
import com.example.repositories.DepartRepository;

@Service
public class DepartServiceImpl implements DepartService {
	@Autowired
	DepartRepository departRepository;

	
	@Override
	public Departs save(Departs entity) {
		return departRepository.save(entity);
	}

	@Override
	public List<Departs> saveAll(List<Departs> entities) {
		return (List<Departs>)departRepository.saveAll(entities);
	}

	@Override
	public Optional<Departs> findById(String id) {
		return departRepository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return departRepository.existsById(id);
	}

	@Override
	public List<Departs> findAll() {
		return (List<Departs>)departRepository.findAll();
	}

	@Override
	public List<Departs> findAllById(List<String> ids) {
		return (List<Departs>)departRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return departRepository.count();
	}

	@Override
	public void deleteById(String id) {
		departRepository.deleteById(id);
	}

	@Override
	public void delete(Departs entity) {
		departRepository.delete(entity);
	}

	@Override
	public void deleteAllById(List<String> ids) {
		departRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(List<Departs> entities) {
		departRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		departRepository.deleteAll();
	}
	
	
	
}
