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
	 * �U�Ƚs��
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMERID")
	private String customerID;

	/**
	 * �ู
	 */
	@Column(name = "DESKNUMBER")
	private String deskNumber;

	/**
	 * �i���ɶ�
	 */
	@Column(name = "CHECKINTIME")
	private Timestamp checkInTime;

	/**
	 * �X���ɶ�
	 */
	@Column(name = "CHECKOUTTIME")
	private Timestamp checkOutTime;

	/**
	 * �U�ȦW��
	 */
	@Column(name = "NAME")
	private String customerName;

	/**
	 * �U�ȹq��
	 */
	@Column(name = "PHONE")
	private String phoneNumber;

	/**
	 * �H�Y��
	 */
	@Column(name = "PEOPLECOUNT")
	private int peopleCount;

	/**
	 * �U�ȳƵ�
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 * �q��C��
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<Booking> bookingList;

	/**
	 * ���o�U�Ƚs��
	 * 
	 * @return
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * ���o�ู
	 * 
	 * @return
	 */
	public String getDeskNumber() {
		return deskNumber;
	}

	/**
	 * �]�w�ู
	 * 
	 * @param deskNumber
	 */
	public void setDeskNumber(String deskNumber) {
		this.deskNumber = deskNumber;
	}

	/**
	 * ���o�U�ȦW��
	 * 
	 * @return
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * �]�w�U�ȦW��
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * ���o�U�ȹq��
	 * 
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * �]�w�U�ȹq��
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * ���o�Ƶ�
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * �]�w�Ƶ�
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���o�i���ɶ�
	 * 
	 * @return
	 */
	public Timestamp getCheckInTime() {
		return checkInTime;
	}

	/**
	 * �]�w�i���ɶ�
	 * 
	 * @param checkInTime
	 */
	public void setCheckInTime(Timestamp checkInTime) {
		this.checkInTime = checkInTime;
	}

	/**
	 * ���o�X���ɶ�
	 * 
	 * @return
	 */
	public Timestamp getCheckOutTime() {
		return checkOutTime;
	}

	/**
	 * �]�w�X���ɶ�
	 * 
	 * @param checkOutTime
	 */
	public void setCheckOutTime(Timestamp checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	/**
	 * ���o�H�Y��
	 * 
	 * @return
	 */
	public int getPeopleCount() {
		return peopleCount;
	}

	/**
	 * �]�w�H�Y��
	 * 
	 * @param peopleCount
	 */
	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	/**
	 * ���o�ϥΪ̪��q��
	 * 
	 * @return
	 */
	public List<Booking> getBookingList() {
		return bookingList;
	}

}
