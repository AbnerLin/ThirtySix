package com.thirtySix.test;

import org.junit.Assert;
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
		{
			/** Test save */
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

			final int customerSize = this.customerService.findAllCustomer()
					.size();
			Assert.assertEquals(4, customerSize);
		}

		{
			/** Test select */
			final int customerSize = this.customerService.findDiningCustomer()
					.size();
			Assert.assertEquals(3, customerSize);
		}

		{
			/** Checkout test */
			// fa64f616-4a09-4eb7-b976-43856dd1d462
			final Customer customer = this.customerService.findDiningCustomer()
					.get("fa64f616-4a09-4eb7-b976-43856dd1d462");
			this.customerService.checkOutCustomer(customer.getCustomerID());

			final int customerSize = this.customerService.findDiningCustomer()
					.size();
			Assert.assertEquals(2, customerSize);
		}

	}
}
