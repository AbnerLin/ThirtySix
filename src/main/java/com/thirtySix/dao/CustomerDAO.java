package com.thirtySix.dao;

import java.util.List;

import com.thirtySix.po.Customer;

public interface CustomerDAO extends GenericDAO<Customer> {

	/**
	 * ���o���\�����U��
	 */
	public List<Customer> getDiningCustomer();

}
