package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.Furnish;
import com.thirtySix.model.SeatMap;

public interface FurnishRepository
		extends PagingAndSortingRepository<Furnish, String> {

	/**
	 * Delete by furnish class.
	 * 
	 * @param furnishClass
	 */
	public void deleteBySeatMap(SeatMap map);

}
