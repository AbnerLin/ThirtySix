package com.thirtySix.service;

import java.util.List;

import com.thirtySix.model.Customer;

public interface CustomerService {

	/**
	 * Find dining customers.
	 * 
	 * @return
	 */
	public List<Customer> findDiningCustomer();

	/**
	 * Save customer.
	 */
	// public void saveCustomer(Customer po);
}
