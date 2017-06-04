package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.SeatMap;

public interface SeatMapRepository extends
		PagingAndSortingRepository<SeatMap, String> {

}
