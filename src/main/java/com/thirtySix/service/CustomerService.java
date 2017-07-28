package com.thirtySix.service;

import java.util.Map;

import com.thirtySix.model.Customer;

public interface CustomerService {

	/**
	 * Find all customer.
	 * 
	 * @return
	 */
	public Map<String, Customer> findAllCustomer();

	/**
	 * Find dining customers.
	 * 
	 * @return
	 */
	public Map<String, Customer> findDiningCustomer();

	/**
	 * Save customer.
	 */
	public void saveCustomer(Customer po);

	/**
	 * Customer checkout.
	 * 
	 * @param customerId
	 */
	public void checkOutCustomer(String customerId);
}
