package com.thirtySix.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BOOKING")
public class Booking {

	/**
	 * 訂單編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "BOOKINGID")
	private String bookingID;

	/**
	 * 訂單時間
	 */
	@Column(name = "ORDERTIME")
	private Timestamp orderTime;

	/**
	 * 出貨時間
	 */
	@Column(name = "DELIVERYTIME")
	private Timestamp deliveryTime;

	/**
	 * 顧客資訊
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID", nullable = false)
	private Customer customer;

	/**
	 * 項目資訊
	 */
	@ManyToOne
	@JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", nullable = false)
	private Item item;

	/**
	 * 項目 量
	 */
	@Column(name = "VOLUME")
	private int volume;

	/**
	 * 是否已出餐(1:出餐, 0:未出餐)
	 */
	@Column(name = "ISSEND")
	private int isSend = 0;

	/**
	 * 取得訂單編號
	 * 
	 * @return
	 */
	public String getBookingID() {
		return bookingID;
	}

	/**
	 * 取得下單時間
	 * 
	 * @return
	 */
	public Timestamp getOrderTime() {
		return orderTime;
	}

	/**
	 * 設定下單時間
	 * 
	 * @param orderTime
	 */
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * 取得出貨時間
	 * 
	 * @return
	 */
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * 設定出貨時間
	 * 
	 * @param deliveryTime
	 */
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * 取得顧客資訊
	 * 
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * 設定顧客資訊
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 取得項目資訊
	 * 
	 * @return
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * 設定項目資訊
	 * 
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * 取得 量
	 * 
	 * @return
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * 設定 量
	 * 
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * 取得是否已出餐
	 * 
	 * @return
	 */
	public int getIsSend() {
		return isSend;
	}

	/**
	 * 設定是否已出餐(1:出餐, 0:未出餐)
	 * 
	 * @param isSend
	 */
	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

}
