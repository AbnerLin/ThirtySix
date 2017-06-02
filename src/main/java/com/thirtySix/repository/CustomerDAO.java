package com.thirtySix.repository;

import java.util.List;

import com.thirtySix.model.Customer;

public interface CustomerDAO extends GenericDAO<Customer> {

	/**
	 * 取得用餐中顧客
	 */
	public List<Customer> getDiningCustomer();

}
