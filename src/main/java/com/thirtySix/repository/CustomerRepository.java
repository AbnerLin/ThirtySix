package com.thirtySix.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.Customer;

public interface CustomerRepository extends
		PagingAndSortingRepository<Customer, String> {

	/**
	 * 查找用餐中客人
	 * 
	 * @return
	 */
	public List<Customer> findByCheckOutTimeIsNull();

}
