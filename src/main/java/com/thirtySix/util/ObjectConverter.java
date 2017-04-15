package com.thirtySix.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirtySix.Core.Buffer;
import com.thirtySix.dto.BookingDTO;
import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.po.Booking;
import com.thirtySix.po.Customer;
import com.thirtySix.po.Item;

@Component
public class ObjectConverter {

	@Autowired
	private Buffer buffer = null;

	public Customer customerDTOtoPO(CustomerDTO dto) {
		Customer po = new Customer();

		po.setCustomerID(dto.getCustomerID());
		po.setCheckInTime(dto.getCheckInTime());
		po.setCheckOutTime(dto.getCheckOutTime());
		po.setCustomerName(dto.getCustomerName());
		po.setPhoneNumber(dto.getPhoneNumber());
		po.setTableNumber(dto.getTableNumber());
		po.setPeopleCount(dto.getPeopleCount());
		po.setRemark(dto.getRemark());
		

		return po;
	}

	public Booking bookingDTOtoPO(BookingDTO dto, String itemId, int volume) {
		Booking po = new Booking();
		Customer customer = buffer.getDiningCustomer().get(dto.getCustomerId());
		po.setCustomer(customer);
		po.setTime(dto.getTime());
		Item item = this.buffer.getItems().get(itemId);
		po.setItem(item);
		po.setVolume(volume);

		return po;
	}

}
