package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Departs;

@Repository
public interface DepartRepository extends CrudRepository<Departs, String> {

}
