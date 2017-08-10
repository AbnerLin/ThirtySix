package com.thirtySix.dto;

import com.thirtySix.model.Booking;

public class DeliveryMealRDTO {

	/**
	 * Customer id.
	 */
	private String customerId;

	/**
	 * Booking .
	 */
	private Booking booking;

	public DeliveryMealRDTO(final String customerId, final Booking booking) {
		super();
		this.customerId = customerId;
		this.booking = booking;
	}

	/**
	 * Get Customer id.
	 * 
	 * @return
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * Set Customer id.
	 * 
	 * @param customerId
	 */
	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Get booking obj.
	 * 
	 * @return
	 */
	public Booking getBooking() {
		return this.booking;
	}

	/**
	 * Set booking obj.
	 * 
	 * @param booking
	 */
	public void setBooking(final Booking booking) {
		this.booking = booking;
	}

}
