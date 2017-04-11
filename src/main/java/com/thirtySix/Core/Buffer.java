package com.thirtySix.Core;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirtySix.po.Customer;

@Component
public class Buffer {

	@Autowired
	private DBManager dbManager = null;

	private Logger logger = Logger.getLogger(this.getClass());

	/** 用餐中顧客Buffer <customerId, po> */
	private Map<String, Customer> diningCustomerBuffer = new ConcurrentHashMap<String, Customer>();

	@PostConstruct
	public void init() {
		/** 載入用餐中的顧客 */
		this.loadDiningCustomer();
	}

	/**
	 * 取得用餐中的顧客
	 */
	private void loadDiningCustomer() {
		List<Customer> diningCustomerList = dbManager.getDiningCustomer();
		logger.info("載入用餐中顧客..");
		int count = 0;
		for (Customer customer : diningCustomerList) {
			String id = customer.getCustomerID();
			this.diningCustomerBuffer.put(id, customer);
			count++;
			logger.info("顧客編號：" + id + " 進場時間：" + customer.getCheckInTime());
		}
		logger.info("目前共 " + count + "組客人用餐中。");
	}

	/**
	 * 取得用餐中的顧客
	 * 
	 * @return
	 */
	public Map<String, Customer> getDiningCustomer() {
		return this.diningCustomerBuffer;
	}

	/**
	 * 取得使用中的桌號
	 * 
	 * @return
	 */
	public List<String> getDiningDesk() {
		// TODO

		return null;
	}

}
