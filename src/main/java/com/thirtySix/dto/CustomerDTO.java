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
	 * �U�Ƚs��
	 */
	private String customerID;

	/**
	 * �ู
	 */
	private String deskNumber;

	/**
	 * �i���ɶ�
	 */
	private Timestamp checkInTime;

	/**
	 * �X���ɶ�
	 */
	private Timestamp checkOutTime;

	/**
	 * �U�ȦW��
	 */
	private String customerName;

	/**
	 * �U�ȹq��
	 */
	private String phoneNumber;

	/**
	 * �H�Y��
	 */
	private Integer peopleCount;

	/**
	 * �U�ȳƵ�
	 */
	private String remark;

	/**
	 * �q��C��
	 */
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
	 * �]�w�U�Ƚs��
	 * 
	 * @param customerID
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
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
	public Integer getPeopleCount() {
		return peopleCount;
	}

	/**
	 * �]�w�H�Y��
	 * 
	 * @param peopleCount
	 */
	public void setPeopleCount(Integer peopleCount) {
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

	@Override
	public String toString() {
		String result = "";

		result += "ID: " + this.customerID + "\n" + "NAME: "
				+ this.customerName + "\n" + "CheckInTime: " + this.checkInTime
				+ "\n" + "CheckOutTime: " + this.checkOutTime + "\n"
				+ "DeskNumber: " + this.deskNumber + "\n" + "PeopleCount:"
				+ this.peopleCount + "\n" + "Remark: " + this.remark + "\n"
				+ "PhoneNumber: " + this.phoneNumber + "\n";

		return result;
	}
}
