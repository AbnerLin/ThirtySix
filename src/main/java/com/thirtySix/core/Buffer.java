package com.thirtySix.core;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirtySix.model.Customer;
import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;
import com.thirtySix.service.CustomerService;
import com.thirtySix.service.ItemService;
import com.thirtySix.service.MapService;
import com.thirtySix.util.TimeFormatter;

@Component
public class Buffer {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ItemService itemService = null;

	@Autowired
	private CustomerService customerService = null;

	@Autowired
	private MapService mapService = null;

	/** 用餐中顧客Buffer <customerId, po> */
	private Map<String, Customer> diningCustomerBuffer = new ConcurrentHashMap<String, Customer>();

	/** 菜單(分類)<String, ItemClass> */
	private Map<String, ItemClass> itemMenuBuffer = new HashMap<String, ItemClass>();

	/** 菜單 <String, Item> */
	private Map<String, Item> itemBuffer = new HashMap<String, Item>();

	/** furnish class buffer <classID, furnishClass> */
	private Map<String, FurnishClass> furnishClass = new HashMap<String, FurnishClass>();

	/** Furnish Buffer */
	private Map<String, Furnish> furnishBuffer = new HashMap<String, Furnish>();

	@PostConstruct
	public void init() {

		/** 載入用餐中顧客 */
		this.loadDiningCustomer();

		/** 載入菜單 */
		this.loadItemMenu();

		/** load furnish */
		this.loadFurnish();
	}

	/**
	 * load furnish to buffer.
	 */
	private void loadFurnish() {
		final List<FurnishClass> furnishClassList = this.mapService
				.findAllFurnishClass();

		furnishClassList.forEach(furnishClass -> {
			/** Set class buffer */
			this.furnishClass.put(furnishClass.getClassID(), furnishClass);

			/** Set furnish buffer */
			Hibernate.initialize(furnishClass.getFurnishList());

			furnishClass.getFurnishList().forEach(furnish -> {
				this.furnishBuffer.put(furnish.getFurnishID(), furnish);
			});
		});

		// final List<Furnish> furnishList = this.mapService.findAllFurnish();
		//
		// for (final Furnish furnish : furnishList) {
		// this.furnishBuffer.put(furnish.getFurnishID(), furnish);
		// }
	}

	/**
	 * 載入菜單
	 */
	private void loadItemMenu() {
		final List<ItemClass> itemClassList = this.itemService
				.findAllItemClass();

		this.logger.info("載入菜單...");

		itemClassList.forEach(itemClass -> {
			this.itemMenuBuffer.put(itemClass.getClassID(), itemClass);
			this.logger.info("菜單種類編號：" + itemClass.getClassID() + " 種類："
					+ itemClass.getClassName());

			itemClass.getItemList().forEach(item -> {
				this.itemBuffer.put(item.getItemID(), item);
				this.logger.info(
						"菜色編號：" + item.getItemID() + " 名稱：" + item.getName());
			});
		});

		this.logger.info("菜單載入完畢。");
	}

	/**
	 * 載入用餐中顧客
	 */
	private void loadDiningCustomer() {
		final List<Customer> diningCustomerList = this.customerService
				.findDiningCustomer();

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

	/**
	 * 取得用餐中顧客Buffer
	 * 
	 * @return
	 */
	public Map<String, Customer> getDiningCustomer() {
		return this.diningCustomerBuffer;
	}

	/**
	 * 取得項目列表(分類)
	 * 
	 * @return
	 */
	public Map<String, ItemClass> getMenu() {
		return this.itemMenuBuffer;
	}

	/**
	 * 取得項目列表
	 * 
	 * @return
	 */
	public Map<String, Item> getItems() {
		return this.itemBuffer;
	}

	/**
	 * Get furnish from buffer.
	 * 
	 * @return
	 */
	public Map<String, Furnish> getFurnish() {
		return this.furnishBuffer;
	}

	/**
	 * Get furnish class from buffer.
	 * 
	 * @return
	 */
	public Map<String, FurnishClass> getFurnishClass() {
		return this.furnishClass;
	}

}
