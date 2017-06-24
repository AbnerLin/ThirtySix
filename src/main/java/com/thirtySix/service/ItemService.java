package com.thirtySix.service;

import java.util.List;

import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;

public interface ItemService {

	/**
	 * Get item class list.
	 * 
	 * @return
	 */
	public List<ItemClass> findAllItemClass();

	/**
	 * Get item list.
	 * 
	 * @return
	 */
	public List<Item> findAllItem();

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
