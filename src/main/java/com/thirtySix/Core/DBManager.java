package com.thirtySix.Core;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.dao.BookingDAO;
import com.thirtySix.dao.CustomerDAO;
import com.thirtySix.dao.ItemClassDAO;
import com.thirtySix.dao.ItemDAO;
import com.thirtySix.dao.SeatMapDAO;
import com.thirtySix.po.Booking;
import com.thirtySix.po.Customer;
import com.thirtySix.po.Item;
import com.thirtySix.po.ItemClass;
import com.thirtySix.po.SeatMap;

@Component
@Transactional
public class DBManager {

	@Autowired
	private CustomerDAO customerDAO = null;

	@Autowired
	private ItemClassDAO itemClassDAO = null;

	@Autowired
	private ItemDAO itemDAO = null;
	
	@Autowired
	private BookingDAO bookingDAO = null;
	
	@Autowired
	private SeatMapDAO seatMapDAO = null;

	/**
	 * 新增座位表
	 * 
	 * @param po
	 */
	public void insertSeatMap(SeatMap po) {
		seatMapDAO.insert(po);
	}
	
	/**
	 * 更新座位表
	 * 
	 * @param po
	 */
	public void updateSeatMap(SeatMap po) {
		seatMapDAO.update(po);
	}
	
	/**
	 * 取得座位表
	 * 
	 * @return
	 */
	public List<SeatMap> getSeatMap() {
		return seatMapDAO.getAll();
	}
	
	/**
	 * 取得用餐中顧客
	 * 
	 * @return
	 */
	public List<Customer> getDiningCustomer() {
		return customerDAO.getDiningCustomer();
	}

	/**
	 * 新增用餐中顧客
	 */
	public Serializable insertCustomer(Customer po) {
		return customerDAO.insert(po);
	}

	/**
	 * 取得所有項目種類
	 * 
	 * @return
	 */
	public List<ItemClass> getAllItemClass() {
		return itemClassDAO.getAll();
	}

	/**
	 * 新增項目種類
	 * 
	 * @param po
	 */
	public void insertItemClass(ItemClass po) {
		itemClassDAO.insert(po);
	}

	/**
	 * 新增項目
	 * 
	 * @param po
	 */
	public void insertItem(Item po) {
		itemDAO.insert(po);
	}
	
	/**
	 * 新增訂單
	 * 
	 * @param po
	 */
	public void insertBooking(Booking po) {
		bookingDAO.insert(po);
	}
}
