package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.Furnish;

public interface FurnishRepository extends
		PagingAndSortingRepository<Furnish, String> {
	
	/**
	 * Delete by furnish class.
	 * 
	 * @param furnishClass
	 */
	public void deleteBySeatMap(String mapID);
	
}
