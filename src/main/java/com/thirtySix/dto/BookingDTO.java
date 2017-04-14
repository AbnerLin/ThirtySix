package com.thirtySix.dto;

import java.io.Serializable;
import java.util.List;

public class BookingDTO implements Serializable {

	private static final long serialVersionUID = 6147911624233901739L;

	/**
	 * 顧客編號
	 */
	private String customerId;

	/**
	 * 桌號
	 */
	private String tableNumber;

	/**
	 * 項目清單
	 */
	private List<ItemDTO> itemList;

	/**
	 * 取得顧客編號
	 * 
	 * @return
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * 設定顧客編號
	 * 
	 * @param customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 取得桌號
	 * 
	 * @return
	 */
	public String getTableNumber() {
		return tableNumber;
	}

	/**
	 * 設定桌號
	 * 
	 * @param tableNumber
	 */
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * 取得項目列表
	 * 
	 * @return
	 */
	public List<ItemDTO> getItemList() {
		return itemList;
	}

	/**
	 * 設定項目列表
	 * 
	 * @param itemList
	 */
	public void setItemList(List<ItemDTO> itemList) {
		this.itemList = itemList;
	}

}
