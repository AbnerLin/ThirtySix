package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.model.Booking;
import com.thirtySix.repository.BookingRepository;
import com.thirtySix.service.BookingService;
import com.thirtySix.service.CustomerService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private BookingRepository repository;

	@Override
	public List<Booking> findAllBooking() {
		return (List<Booking>) this.repository.findAll();
	}

	@Override
	@Transactional
	public void saveBooking(final String customerId,
			final List<Booking> poList) {
		/** Save to DB */
		this.repository.save(poList);

		/** Update buffer */
		this.customerService.findDiningCustomer().get(customerId)
				.getBookingList().addAll(poList);
	}
}
