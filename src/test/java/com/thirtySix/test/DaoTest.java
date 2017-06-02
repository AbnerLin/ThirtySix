package com.thirtySix.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.model.Customer;
import com.thirtySix.model.Item;
import com.thirtySix.repository.CustomerDAO;
import com.thirtySix.repository.ItemDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DaoTest {

	@Autowired
	private CustomerDAO customerDAO = null;

	@Autowired
	private ItemDAO itemDAO = null;

	private Customer customer = null;

	@Before
	public void setup() {
		this.customer = new Customer();

		/** customer entity */
		Calendar cal = Calendar.getInstance();
		Timestamp now = new Timestamp(cal.getTimeInMillis());
		customer.setCheckInTime(now);
		customer.setTableNumber("A9");
		customer.setPeopleCount(6);
		customer.setCustomerName("Mr. hi");
		customer.setRemark("remark remark");
		customer.setPhoneNumber("0987654321");
	}

	@Test
	@Rollback(true)
	@Transactional
	public void testCustomerDAO() {
		customerDAO.insert(customer);

		assertNotNull(customer.getCustomerID());

		List<Customer> customerList = customerDAO.getAll();

		assertThat(customerList, CoreMatchers.hasItem(customer));
	}

	public void testItemDAO() {

	}

}
