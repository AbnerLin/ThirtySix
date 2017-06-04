package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.model.Booking;
import com.thirtySix.repository.BookingRepository;
import com.thirtySix.service.BookingService;

@Service
public class BookingServiceImpl extends CommonServiceImpl<Booking> implements
		BookingService {

	@Autowired
	private static BookingRepository repository;

	public BookingServiceImpl() {
		super(repository);
	}

	@Override
	@Transactional
	public void save(Booking po) {
		repository.save(po);
	}

	@Override
	public List<Booking> findAll() {
		return (List<Booking>) repository.findAll();
	}

}
