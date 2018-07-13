package com.housework.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.housework.history.History;

public interface HistoryRepository extends CrudRepository<History, Long> {
	List<History> findAllByOrderByNameAsc();

	List<History> findBypersonId(int personId);
}
