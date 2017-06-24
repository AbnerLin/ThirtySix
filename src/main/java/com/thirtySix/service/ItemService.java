package com.thirtySix.service;

import java.util.List;

import com.thirtySix.model.ItemClass;

public interface ItemService {

	/**
	 * Get item class list.
	 * 
	 * @return
	 */
	public List<ItemClass> findAllItemClass();

}
