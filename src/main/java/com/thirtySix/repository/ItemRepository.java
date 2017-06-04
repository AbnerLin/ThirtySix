package com.thirtySix.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.Item;

public interface ItemRepository extends
		PagingAndSortingRepository<Item, String> {

	static String SHOW = "1";
	static String HIDE = "0";

	/**
	 * 查詢顯示的項目
	 * 
	 * @param isDisplay
	 * @return
	 */
	public List<Item> findByIsDisplayIs(int isDisplay);
}
