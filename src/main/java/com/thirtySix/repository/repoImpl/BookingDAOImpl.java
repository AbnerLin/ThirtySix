package com.thirtySix.repository.repoImpl;

import org.springframework.stereotype.Repository;

import com.thirtySix.model.Booking;
import com.thirtySix.repository.BookingDAO;

@Repository
public class BookingDAOImpl extends GenericDAOImpl<Booking> implements
		BookingDAO {

	public BookingDAOImpl() {
		super(Booking.class);
	}

}
