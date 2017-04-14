package com.thirtySix.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.thirtySix.po.Booking;

public class CustomerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8633191169307671028L;

	/**
	 * 顧客編號
	 */
	private String customerID;

	/**
	 * 桌號
	 */
	private String tableNumber;

	/**
	 * 進場時間
	 */
	private Timestamp checkInTime;

	/**
	 * 出場時間
	 */
	private Timestamp checkOutTime;

	/**
	 * 顧客名稱
	 */
	private String customerName;

	/**
	 * 顧客電話
	 */
	private String phoneNumber;

	/**
	 * 人數
	 */
	private Integer peopleCount;

	/**
	 * 備註
	 */
	private String remark;

	/**
	 * 訂單資訊
	 */
	private List<Booking> bookingList;

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
	public void setDeskNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * 取得顧客名稱
	 * 
	 * @return
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 設定顧客名稱
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 取得顧客電話
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 設定顧客電話
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 取得備註
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 設定備註
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 取得進場時間
	 * 
	 * @return
	 */
	public Timestamp getCheckInTime() {
		return checkInTime;
	}

	/**
	 * 設定進場時間
	 * 
	 * @param checkInTime
	 */
	public void setCheckInTime(Timestamp checkInTime) {
		this.checkInTime = checkInTime;
	}

	/**
	 * 取得出場時間
	 * 
	 * @return
	 */
	public Timestamp getCheckOutTime() {
		return checkOutTime;
	}

	/**
	 * 設定出場時間
	 * 
	 * @param checkOutTime
	 */
	public void setCheckOutTime(Timestamp checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	/**
	 * 取得人數
	 * 
	 * @return
	 */
	public Integer getPeopleCount() {
		return peopleCount;
	}

	/**
	 * 設定人數
	 * 
	 * @param peopleCount
	 */
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	/**
	 * 取得訂單列表
	 * 
	 * @return
	 */
	public List<Booking> getBookingList() {
		return bookingList;
	}

	@Override
	public String toString() {
		String result = "";

		result += "ID: " + this.customerID + "\n" + "NAME: "
				+ this.customerName + "\n" + "CheckInTime: " + this.checkInTime
				+ "\n" + "CheckOutTime: " + this.checkOutTime + "\n"
				+ "DeskNumber: " + this.tableNumber + "\n" + "PeopleCount:"
				+ this.peopleCount + "\n" + "Remark: " + this.remark + "\n"
				+ "PhoneNumber: " + this.phoneNumber + "\n";

		return result;
	}
}
