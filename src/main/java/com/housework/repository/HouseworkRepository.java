package com.housework.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.housework.housework.Housework;

public interface HouseworkRepository extends CrudRepository<Housework, Long>{
	
	List<Housework> findAllByOrderByNameAsc();
	
	List<Housework> findByid(int id);

}
