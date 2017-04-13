package com.thirtySix.dao;

import java.util.List;

import com.thirtySix.po.Customer;

public interface CustomerDAO extends GenericDAO<Customer> {

	/**
	 * 取得用餐中顧客
	 */
	public List<Customer> getDiningCustomer();

}
