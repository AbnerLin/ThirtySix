package com.thirtySix.dto;

import java.util.List;

import com.thirtySix.model.Booking;

/**
 * Response dto.
 */
public class OrderRDTO {

	/**
	 * Customer id.
	 */
	private String customerId;

	/**
	 * Booking list.
	 */
	private List<Booking> bookingList;

	/**
	 * Construct.
	 * 
	 * @param customerId
	 * @param bookingList
	 */
	public OrderRDTO(final String customerId, final List<Booking> bookingList) {
		this.customerId = customerId;
		this.bookingList = bookingList;
	}

	/**
	 * Get customer id.
	 * 
	 * @return
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * Set customer id.
	 * 
	 * @param customerId
	 */
	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Get booking list.
	 * 
	 * @return
	 */
	public List<Booking> getBookingList() {
		return this.bookingList;
	}

	/**
	 * Set booking list.
	 * 
	 * @param bookingList
	 */
	public void setBookingList(final List<Booking> bookingList) {
		this.bookingList = bookingList;
	}

}
