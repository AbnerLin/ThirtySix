package com.thirtySix.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.thirtySix.model.Customer;
import com.thirtySix.repository.CustomerRepository;

public class CustomerServiceImpl extends CommonServiceImpl<Customer> {

	@Autowired
	private static CustomerRepository repository;

	public CustomerServiceImpl() {
		super(repository);
	}

}
