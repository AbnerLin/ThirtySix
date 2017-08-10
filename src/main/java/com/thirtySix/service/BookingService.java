package com.thirtySix.service;

import java.util.List;

import com.thirtySix.model.Booking;

public interface BookingService {

	/**
	 * Save booking list.
	 * 
	 * @param poList
	 */
	public void saveBooking(String customerId, List<Booking> poList);

	/**
	 * List all booking entity.
	 * 
	 * @return
	 */
	public List<Booking> findAllBooking();

	/**
	 * Set the booking deliveried.
	 * 
	 * @param customerId
	 * @param bookingId
	 */
	public Booking setDeliveryMeal(String customerId, String bookingId);

}
