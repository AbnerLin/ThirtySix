package com.thirtySix.dao;

import java.util.List;

import com.thirtySix.po.Item;

public interface ItemDAO extends GenericDAO<Item> {

	/**
	 * 顯示
	 */
	public static int SHOW = 1;

	/**
	 * 隱藏
	 */
	public static int HIDE = 0;

	/**
	 * 取得可顯示的項目
	 * 
	 * @return
	 */
	public List<Item> getShowItem();
}
