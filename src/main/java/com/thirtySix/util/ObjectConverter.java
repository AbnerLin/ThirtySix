package com.thirtySix.util;

import org.springframework.stereotype.Component;

import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.po.Customer;

@Component
public class ObjectConverter {

	public Customer customerDTOtoPO(CustomerDTO dto) {
		Customer po = new Customer();

		po.setCustomerID(dto.getCustomerID());
		po.setCheckInTime(dto.getCheckInTime());
		po.setCheckOutTime(dto.getCheckOutTime());
		po.setCustomerName(dto.getCustomerName());
		po.setPhoneNumber(dto.getPhoneNumber());
		po.setDeskNumber(dto.getDeskNumber());
		po.setPeopleCount(dto.getPeopleCount());
		po.setRemark(dto.getRemark());

		return po;
	}

//	public Customer cus 
	
}
