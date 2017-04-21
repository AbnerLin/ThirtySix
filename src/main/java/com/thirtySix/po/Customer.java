package com.thirtySix.po;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	/**
	 * 顧客編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "CUSTOMERID")
	private String customerID;

	/**
	 * 桌號
	 */
	@Column(name = "TABLENUMBER")
	private String tableNumber;

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
	 * 人數
	 */
	@Column(name = "PEOPLECOUNT")
	private Integer peopleCount;

	/**
	 * 備註
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 * 訂單列表
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Booking> bookingList = new ArrayList<Booking>();

	/**
	 * 進場時間(字串)
	 */
	@Transient
	private String checkInTimeStringFormat = "";

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
	 * @param deskNumber
	 */
	public void setTableNumber(String tableNumber) {
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

	/**
	 * 取得進場時間 字串格式
	 * 
	 * @return
	 */
	public String getCheckInTimeStringFormat() {
		return checkInTimeStringFormat;
	}

	/**
	 * 設定進場時間 字串格式
	 * 
	 * @param checkInTimeStringFormat
	 */
	public void setCheckInTimeStringFormat(String checkInTimeStringFormat) {
		this.checkInTimeStringFormat = checkInTimeStringFormat;
	}

}
