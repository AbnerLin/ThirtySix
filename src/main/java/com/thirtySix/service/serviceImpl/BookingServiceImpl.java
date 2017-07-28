package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.model.Booking;
import com.thirtySix.repository.BookingRepository;
import com.thirtySix.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository repository;

	@Override
	public List<Booking> findAllBooking() {
		return (List<Booking>) this.repository.findAll();
	}

	// @Override
	// @Transactional
	// public void saveBooking(List<Booking> poList) {
	// repository.save(poList);
	// }
	//
	// @Override
	// public void saveBooking(Booking po) {
	// repository.save(po);
	// }

}
