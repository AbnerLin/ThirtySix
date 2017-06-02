package com.thirtySix.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.model.Booking;
import com.thirtySix.repository.BookingRepository;
import com.thirtySix.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository repository;

	@Override
	@Transactional
	public void save(Booking po) {
		repository.save(po);
	}
}
