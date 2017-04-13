package com.thirtySix.Core;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.dao.CustomerDAO;
import com.thirtySix.dao.ItemClassDAO;
import com.thirtySix.dao.ItemDAO;
import com.thirtySix.po.Customer;
import com.thirtySix.po.Item;
import com.thirtySix.po.ItemClass;

@Component
@Transactional
public class DBManager {

	@Autowired
	private CustomerDAO customerDAO = null;

	@Autowired
	private ItemClassDAO itemClassDAO = null;

	@Autowired
	private ItemDAO itemDAO = null;

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
}
