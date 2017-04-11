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

	/** ���\���U��Buffer <customerId, po> */
	private Map<String, Customer> diningCustomerBuffer = new ConcurrentHashMap<String, Customer>();

	@PostConstruct
	public void init() {
		/** ���J���\�����U�� */
		this.loadDiningCustomer();
	}

	/**
	 * ���o���\�����U��
	 */
	private void loadDiningCustomer() {
		List<Customer> diningCustomerList = dbManager.getDiningCustomer();
		logger.info("���J���\���U��..");
		int count = 0;
		for (Customer customer : diningCustomerList) {
			String id = customer.getCustomerID();
			this.diningCustomerBuffer.put(id, customer);
			count++;
			logger.info("�U�Ƚs���G" + id + " �i���ɶ��G" + customer.getCheckInTime());
		}
		logger.info("�ثe�@ " + count + "�իȤH���\���C");
	}

	/**
	 * ���o���\�����U��
	 * 
	 * @return
	 */
	public Map<String, Customer> getDiningCustomer() {
		return this.diningCustomerBuffer;
	}

	/**
	 * ���o�ϥΤ����ู
	 * 
	 * @return
	 */
	public List<String> getDiningDesk() {
		// TODO

		return null;
	}

}
