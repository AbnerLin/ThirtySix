package com.thirtySix.service;

import java.util.Map;

import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;

public interface ItemService {

	/**
	 * Get item class list.
	 * 
	 * @return
	 */
	public Map<String, ItemClass> findAllItemClass();

	/**
	 * Get item from Id;
	 * 
	 * @return
	 */
	public Item findItem(String itemId);

	/**
	 * Save item.
	 */
	public void saveItem(Item po);

	/**
	 * Save item class.
	 * 
	 * @param po
	 */
	public void saveItemClass(ItemClass po);

	/**
	 * Delete item by id.
	 * 
	 * @param itemID
	 */
	public void deleteItem(String itemID);

	/**
	 * Delete item by class id.
	 * 
	 * @param classID
	 */
	public void deleteItemClass(String classID);
}
