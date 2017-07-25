package com.thirtySix.core;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirtySix.model.Customer;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;
import com.thirtySix.model.SeatMap;
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

	/** 菜單<String, ItemClass> */
	private Map<String, ItemClass> itemMenuBuffer = new HashMap<String, ItemClass>();

	/** furnish class buffer <classID, furnishClass> */
	private Map<String, FurnishClass> furnishClass = new HashMap<String, FurnishClass>();

	/** map buffer <mapId, SeatMap> */
	private Map<String, SeatMap> mapBuffer = new HashMap<String, SeatMap>();

	@PostConstruct
	public void init() {

		/** 載入用餐中顧客 */
		this.loadDiningCustomer();

		/** 載入菜單 */
		this.loadItemMenu();

		/** load map */
		this.loadMap();

		/** load furnish */
		this.loadFurnishClass();
	}

	/**
	 * Load map info.
	 */
	private void loadMap() {
		final List<SeatMap> mapList = this.mapService.findAllSeatMap();
		mapList.forEach(map -> {
			this.mapBuffer.put(map.getMapID(), map);
		});
	}

	/**
	 * load furnishClass to buffer.
	 */
	private void loadFurnishClass() {
		final List<FurnishClass> furnishClassList = this.mapService
				.findAllFurnishClass();

		furnishClassList.forEach(furnishClass -> {
			this.furnishClass.put(furnishClass.getClassID(), furnishClass);
		});
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
				// this.itemBuffer.put(item.getItemID(), item);
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
	 * Get item by id from buffer.
	 * 
	 * @param itemId
	 * @return
	 */
	public Item getItemById(final String itemId) {
		return this.itemMenuBuffer.entrySet().stream() //
				.map(map -> map.getValue()) //
				.flatMap(value -> value.getItemList().stream()) //
				.filter(item -> item.getItemID().equalsIgnoreCase(itemId)) //
				.findFirst() //
				.orElse(null);
	}

	// /**
	// * 取得項目列表
	// *
	// * @return
	// */
	// public Map<String, Item> getItems() {
	// return this.itemBuffer;
	// }

	/**
	 * Get furnish class from buffer.
	 * 
	 * @return
	 */
	public Map<String, FurnishClass> getFurnishClass() {
		return this.furnishClass;
	}

	/**
	 * Get seat map fuffer.
	 * 
	 * @return
	 */
	public Map<String, SeatMap> getSeatMap() {
		return this.mapBuffer;
	}

}
