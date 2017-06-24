package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.model.Customer;
import com.thirtySix.repository.CustomerRepository;
import com.thirtySix.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public List<Customer> findDiningCustomer() {
		return repository.findByCheckOutTimeIsNull();
	}

	@Override
	public void saveCustomer(Customer po) {
		repository.save(po);
	}

}
