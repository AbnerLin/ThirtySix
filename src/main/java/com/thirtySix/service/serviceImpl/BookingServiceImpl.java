package com.thirtySix.service.serviceImpl;

import java.sql.Timestamp;
import java.util.Calendar;
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

	@Override
	@Transactional
	public Booking setDeliveryMeal(final String customerId,
			final String bookingId) {

		final Booking booking = this.customerService.findDiningCustomer()
				.get(customerId).getBookingList().stream()
				.filter(_booking -> _booking.getBookingID().equals(bookingId))
				.findFirst().orElse(null);

		final Calendar now = Calendar.getInstance();
		final Timestamp nowTime = new Timestamp(now.getTimeInMillis());

		/** Update to Buffer */
		booking.setIsSend(Booking.SENDSTATUS.SENT.value());
		booking.setDeliveryTime(nowTime);

		/** Save to DB */
		this.repository.save(booking);

		return booking;
	}
}
