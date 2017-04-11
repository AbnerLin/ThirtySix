package com.thirtySix.Core;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.dao.CustomerDAO;
import com.thirtySix.po.Customer;

@Component
@Transactional
public class DBManager {

	@Autowired
	private CustomerDAO customerDAO = null;

	/**
	 * ���o���\�����U��
	 * 
	 * @return
	 */
	public List<Customer> getDiningCustomer() {
		return customerDAO.getDiningCustomer();
	}
	
	/**
	 * �s�W�U��
	 */
	public Serializable insertCustomer(Customer po) {
		return customerDAO.insert(po);
	}
}
