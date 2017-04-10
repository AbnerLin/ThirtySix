package com.thirtySix.po;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKING")
public class Booking {

	/**
	 * 訂單編號
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKINGID")
	private String bookingID;

	/**
	 * 訂單時間
	 */
	@Column(name = "TIME")
	private Timestamp time;

	/**
	 * 客戶編號
	 */
	@ManyToOne
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID", nullable = false)
	private Customer customer;

	/**
	 * 項目編號
	 */
	@ManyToOne
	@JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", nullable = false)
	private Item item;

	/**
	 * 項目量
	 */
	@Column(name = "VOLUME")
	private int volume;

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
	 * 設定顧客編號
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
	 * 取得項目 量
	 * 
	 * @return
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * 設定項目 量
	 * 
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

}
