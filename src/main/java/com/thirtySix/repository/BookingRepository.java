package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.Booking;

public interface BookingRepository extends
		PagingAndSortingRepository<Booking, String> {
}
