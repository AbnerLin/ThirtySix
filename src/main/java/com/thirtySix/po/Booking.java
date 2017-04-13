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
	@Column(name = "TIME")
	private Timestamp time;

	/**
	 * 顧客資訊
	 */
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
	private int isSend;

	/**
	 * 取得訂單編號
	 * 
	 * @return
	 */
	public String getBookingID() {
		return bookingID;
	}

	/**
	 * 取得訂單時間
	 * 
	 * @return
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * 設定訂單時間
	 * 
	 * @param time
	 */
	public void setTime(Timestamp time) {
		this.time = time;
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
