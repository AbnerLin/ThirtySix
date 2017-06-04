package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.SeatPosition;

public interface SeatPositionRepository extends
		PagingAndSortingRepository<SeatPosition, String> {

}
