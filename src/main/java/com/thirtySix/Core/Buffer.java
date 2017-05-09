package com.thirtySix.Core;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirtySix.po.Customer;
import com.thirtySix.po.Item;
import com.thirtySix.po.ItemClass;
import com.thirtySix.util.TimeFormatter;

@Component
public class Buffer {

	@Autowired
	private DBManager dbManager = null;

	private Logger logger = Logger.getLogger(this.getClass());

	/** 用餐中顧客Buffer <customerId, po> */
	private Map<String, Customer> diningCustomerBuffer = new ConcurrentHashMap<String, Customer>();

	/** 菜單(分類)<String, ItemClass> */
	private Map<String, ItemClass> itemMenuBuffer = new HashMap<String, ItemClass>();
	/** 菜單 <String, Item> */
	private Map<String, Item> itemBuffer = new HashMap<String, Item>();

	@PostConstruct
	public void init() {

		/** 載入用餐中顧客 */
		this.loadDiningCustomer();

		/** 載入菜單 */
		this.loadItemMenu();
	}

	/**
	 * 載入菜單
	 */
	private void loadItemMenu() {
		List<ItemClass> itemClassList = dbManager.getAllItemClass();
		logger.info("載入菜單...");
		for (ItemClass itemClass : itemClassList) {
			this.itemMenuBuffer.put(itemClass.getClassID(), itemClass);
			logger.info("菜單種類編號：" + itemClass.getClassID() + " 種類："
					+ itemClass.getClassName());

			for (Item item : itemClass.getItemList()) {
				this.itemBuffer.put(item.getItemID(), item);

				logger.info("菜色編號：" + item.getItemID() + " 名稱："
						+ item.getName());
			}
		}
		logger.info("菜單載入完畢。");
	}

	/**
	 * 載入用餐中顧客
	 */
	private void loadDiningCustomer() {
		List<Customer> diningCustomerList = dbManager.getDiningCustomer();
		logger.info("載入用餐中顧客...");
		int count = 0;
		for (Customer customer : diningCustomerList) {
			String id = customer.getCustomerID();

			Date date = new Date(customer.getCheckInTime().getTime());
			customer.setCheckInTimeStringFormat(TimeFormatter.getInstance()
					.getTime(date));

			this.diningCustomerBuffer.put(id, customer);
			count++;
			logger.info("顧客編號" + id + " 進場時間" + customer.getCheckInTime());
		}
		logger.info("用餐中顧客共" + count + "組。");
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
	 * 取得使用中桌號
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Set<String> getDiningTable() {
		Set<String> table = new HashSet<String>();

		Iterator iter = this.diningCustomerBuffer.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry pair = (Map.Entry) iter.next();
			Customer customer = (Customer) pair.getValue();
			String tableNumber = customer.getTableNumber();

			table.add(tableNumber);
		}

		return table;
	}

}
