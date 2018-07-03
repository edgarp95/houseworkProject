package com.housework.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.housework.person.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
	
	List<Person> findAllByOrderByNameAsc();

}
