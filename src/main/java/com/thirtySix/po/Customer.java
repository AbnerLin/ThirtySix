package com.thirtySix.po;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	/**
	 * 顧客編號
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMERID")
	private String customerID;

	/**
	 * 桌號
	 */
	@Column(name = "DESKNUMBER")
	private String deskNumber;

	/**
	 * 進場時間
	 */
	@Column(name = "CHECKINTIME")
	private Timestamp checkInTime;

	/**
	 * 出場時間
	 */
	@Column(name = "CHECKOUTTIME")
	private Timestamp checkOutTime;

	/**
	 * 顧客名稱
	 */
	@Column(name = "NAME")
	private String customerName;

	/**
	 * 顧客電話
	 */
	@Column(name = "PHONE")
	private String phoneNumber;

	/**
	 * 人頭數
	 */
	@Column(name = "PEOPLECOUNT")
	private int peopleCount;

	/**
	 * 顧客備註
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 * 訂單列表
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
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
	 * 取得桌號
	 * 
	 * @return
	 */
	public String getDeskNumber() {
		return deskNumber;
	}

	/**
	 * 設定桌號
	 * 
	 * @param deskNumber
	 */
	public void setDeskNumber(String deskNumber) {
		this.deskNumber = deskNumber;
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
	 * 取得人頭數
	 * 
	 * @return
	 */
	public int getPeopleCount() {
		return peopleCount;
	}

	/**
	 * 設定人頭數
	 * 
	 * @param peopleCount
	 */
	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	/**
	 * 取得使用者的訂單
	 * 
	 * @return
	 */
	public List<Booking> getBookingList() {
		return bookingList;
	}

}
