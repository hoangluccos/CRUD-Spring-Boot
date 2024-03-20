package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Staffs;

@Repository
public interface StaffRepository extends CrudRepository<Staffs, String> {

}
