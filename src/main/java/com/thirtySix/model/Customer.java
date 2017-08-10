package com.thirtySix.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
	 * table number.
	 */
	@ManyToOne
	@JoinColumn(name = "FURNISH", referencedColumnName = "FURNISHID", nullable = false)
	private Furnish furnish;

	/**
	 * 訂單列表
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Booking> bookingList = new ArrayList<Booking>();

	@PrePersist
	public void setTime() {
		final Calendar now = Calendar.getInstance();
		this.checkInTime = new Timestamp(now.getTimeInMillis());
	}

	/**
	 * 取得顧客編號
	 * 
	 * @return
	 */
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * 設定顧客編號
	 * 
	 * @param customerID
	 */
	public void setCustomerID(final String customerID) {
		this.customerID = customerID;
	}

	/**
	 * Get which table customer use.
	 * 
	 * @return
	 */
	public Furnish getFurnish() {
		return this.furnish;
	}

	/**
	 * Set which table customer use.
	 * 
	 * @param furnish
	 */
	public void setFurnish(final Furnish furnish) {
		this.furnish = furnish;
	}

	/**
	 * 取得顧客名稱
	 * 
	 * @return
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * 設定顧客名稱
	 * 
	 * @param customerName
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 取得顧客電話
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 設定顧客電話
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 取得備註
	 * 
	 * @return
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 設定備註
	 * 
	 * @param remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 取得進場時間
	 * 
	 * @return
	 */
	public Timestamp getCheckInTime() {
		return this.checkInTime;
	}

	/**
	 * 設定進場時間
	 * 
	 * @param checkInTime
	 */
	public void setCheckInTime(final Timestamp checkInTime) {
		this.checkInTime = checkInTime;
	}

	/**
	 * 取得出場時間
	 * 
	 * @return
	 */
	public Timestamp getCheckOutTime() {
		return this.checkOutTime;
	}

	/**
	 * 設定出場時間
	 * 
	 * @param checkOutTime
	 */
	public void setCheckOutTime(final Timestamp checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	/**
	 * 取得人數
	 * 
	 * @return
	 */
	public Integer getPeopleCount() {
		return this.peopleCount;
	}

	/**
	 * 設定人數
	 * 
	 * @param peopleCount
	 */
	public void setPeopleCount(final Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	/**
	 * 取得訂單列表
	 * 
	 * @return
	 */
	public List<Booking> getBookingList() {
		return this.bookingList;
	}

}
