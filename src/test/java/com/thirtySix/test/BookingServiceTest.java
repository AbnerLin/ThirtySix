package com.thirtySix.test;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thirtySix.model.Booking;
import com.thirtySix.model.Customer;
import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;
import com.thirtySix.service.serviceImpl.BookingServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class BookingServiceTest {

	@Autowired
	private BookingServiceImpl bookService;
	
	@Test
	public void test1() {
		Customer customer = new Customer();
		
		Date now = new Date();
		Timestamp time = new Timestamp(now.getTime()); 
		
		customer.setCheckInTime(time);
		customer.setCustomerName("Mr.Hello");
		customer.setPeopleCount(2);
		customer.setTableNumber("A1");
		customer.setPhoneNumber("0987654321");
		
		ItemClass itemClass = new ItemClass();
		itemClass.setStyle("Meat");
		
		Item item = new Item();
		item.setName("Steak");
		item.setPrice(120);
		item.setItemClass(itemClass);
		
		Booking po = new Booking();
		po.setCustomer(customer);
		
		
	}
}
