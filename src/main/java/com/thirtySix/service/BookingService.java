package com.thirtySix.service;

import java.util.List;

import com.thirtySix.model.Booking;

public interface BookingService {

	/**
	 * Save booking.
	 * 
	 * @param po
	 */
	public void saveBooking(Booking po);
	
	/**
	 * Save booking list.
	 * 
	 * @param poList
	 */
	public void saveBooking(List<Booking> poList);

	/**
	 * List all booking entity.
	 * 
	 * @return
	 */
	public List<Booking> findAllBooking();

}
