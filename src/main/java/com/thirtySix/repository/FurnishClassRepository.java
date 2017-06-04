package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.FurnishClass;

public interface FurnishClassRepository extends
		PagingAndSortingRepository<FurnishClass, String> {

}
