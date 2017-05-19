package com.thirtySix.dto;

import java.io.Serializable;

public class CheckOutDTO implements Serializable {

	private static final long serialVersionUID = 6868833168439814817L;

	/**
	 * 顧客編號
	 */
	private String customerID;

	/**
	 * 桌號
	 */
	private String tableNumber;

	/**
	 * 取得顧客編號
	 * 
	 * @return
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * 設定顧客編號
	 * 
	 * @param customerID
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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

}
