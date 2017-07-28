package com.thirtySix.service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.model.Customer;
import com.thirtySix.repository.CustomerRepository;
import com.thirtySix.service.CustomerService;
import com.thirtySix.util.TimeFormatter;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CustomerRepository repository;

	/** 用餐中顧客Buffer <customerId, po> */
	private Map<String, Customer> diningCustomerBuffer = new ConcurrentHashMap<String, Customer>();

	@PostConstruct
	public void init() {
		/** Load dining customer */
		this.loadDiningCustomer();

	}

	@Override
	public Map<String, Customer> findDiningCustomer() {
		return this.diningCustomerBuffer;
	}

	@Override
	public void saveCustomer(final Customer po) {
		/** Save to DB */
		this.repository.save(po);

		/** Update buffer */
		this.diningCustomerBuffer.put(po.getCustomerID(), po);
	}

	/**
	 * 載入用餐中顧客
	 */
	private void loadDiningCustomer() {
		final List<Customer> diningCustomerList = this.repository
				.findByCheckOutTimeIsNull();

		this.logger.info("載入用餐中顧客...");
		diningCustomerList.forEach(customer -> {
			final Date date = new Date(customer.getCheckInTime().getTime());
			customer.setCheckInTimeStringFormat(
					TimeFormatter.getInstance().getTime(date));

			this.diningCustomerBuffer.put(customer.getCustomerID(), customer);
			this.logger.info("顧客編號" + customer.getCustomerID() + " 進場時間"
					+ customer.getCheckInTime());
		});

		this.logger.info("用餐中顧客共" + this.diningCustomerBuffer.size() + "組。");
	}
}
