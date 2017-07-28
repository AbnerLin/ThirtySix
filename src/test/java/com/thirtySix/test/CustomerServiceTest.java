package com.thirtySix.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thirtySix.model.Customer;
import com.thirtySix.model.Furnish;
import com.thirtySix.service.CustomerService;
import com.thirtySix.service.MapService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService = null;

	@Autowired
	private MapService mapService = null;

	@Test
	public void testSave() {
		final Furnish furnish = this.mapService.findAllSeatMap().entrySet()
				.stream().map(map -> map.getValue())
				.flatMap(map -> map.getFurnishList().stream())
				.filter(tmpFurnish -> tmpFurnish.getFurnishID()
						.equals("0402028d-04f6-4bab-a0f7-20b626dffa88"))
				.findFirst().get();

		final Customer customer = new Customer();
		customer.setCustomerName("Lin");
		customer.setPeopleCount(10);
		customer.setFurnish(furnish);
		this.customerService.saveCustomer(customer);

		// this.customerService.findDiningCustomer()
	}
}
