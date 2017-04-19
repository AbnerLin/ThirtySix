package com.thirtySix.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class BookingDTO implements Serializable {

	private static final long serialVersionUID = 6147911624233901739L;

	/**
	 * 顧客編號
	 */
	private String customerId;

	/**
	 * 訂單時間
	 */
	private Timestamp time;

	/**
	 * 項目清單
	 */
	private List<ItemDTO> itemList;

	public BookingDTO() {
		Calendar now = Calendar.getInstance();
		this.time = new Timestamp(now.getTimeInMillis());
	}

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
	 * 取得時間
	 * 
	 * @return
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * 設定時間
	 * 
	 * @param time
	 */
	public void setTime(Timestamp time) {
		this.time = time;
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
