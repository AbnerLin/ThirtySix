package com.thirtySix.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thirtySix.util.TimeFormatter;

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
	 * Order time string format.
	 */
	@Transient
	private String orderTimeStringFormat;

	/**
	 * DeliveryTimeStringFormat;
	 */
	@Transient
	private String deliveryTimeStringFormat;

	@PrePersist
	public void setTime() {
		final Calendar now = Calendar.getInstance();
		this.orderTime = new Timestamp(now.getTimeInMillis());

		setTimeFormat();
	}

	@PostLoad
	public void setTimeFormat() {
		final Date checkInDate = new Date(this.orderTime.getTime());
		this.orderTimeStringFormat = TimeFormatter.getInstance()
				.getTime(checkInDate);

		if (this.deliveryTime != null) {
			final Date deliveryDate = new Date(this.deliveryTime.getTime());
			this.deliveryTimeStringFormat = TimeFormatter.getInstance()
					.getTime(deliveryDate);
		}
	}

	/**
	 * 取得訂單編號
	 * 
	 * @return
	 */
	public String getBookingID() {
		return this.bookingID;
	}

	/**
	 * 取得下單時間
	 * 
	 * @return
	 */
	public Timestamp getOrderTime() {
		return this.orderTime;
	}

	/**
	 * 設定下單時間
	 * 
	 * @param orderTime
	 */
	public void setOrderTime(final Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * 取得出貨時間
	 * 
	 * @return
	 */
	public Timestamp getDeliveryTime() {
		return this.deliveryTime;
	}

	/**
	 * 設定出貨時間
	 * 
	 * @param deliveryTime
	 */
	public void setDeliveryTime(final Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * 取得顧客資訊
	 * 
	 * @return
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * 設定顧客資訊
	 * 
	 * @param customer
	 */
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	/**
	 * 取得項目資訊
	 * 
	 * @return
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * 設定項目資訊
	 * 
	 * @param item
	 */
	public void setItem(final Item item) {
		this.item = item;
	}

	/**
	 * 取得 量
	 * 
	 * @return
	 */
	public int getVolume() {
		return this.volume;
	}

	/**
	 * 設定 量
	 * 
	 * @param volume
	 */
	public void setVolume(final int volume) {
		this.volume = volume;
	}

	/**
	 * 取得是否已出餐
	 * 
	 * @return
	 */
	public int getIsSend() {
		return this.isSend;
	}

	/**
	 * 設定是否已出餐(1:出餐, 0:未出餐)
	 * 
	 * @param isSend
	 */
	public void setIsSend(final int isSend) {
		this.isSend = isSend;
	}

	/**
	 * Get order time in string format.
	 * 
	 * @return
	 */
	public String getOrderTimeStringFormat() {
		return this.orderTimeStringFormat;
	}

	/**
	 * Set order time in string format.
	 * 
	 * @param orderTimeStringFormat
	 */
	public void setOrderTimeStringFormat(final String orderTimeStringFormat) {
		this.orderTimeStringFormat = orderTimeStringFormat;
	}

	/**
	 * Get delivery time in string format.
	 * 
	 * @return
	 */
	public String getDeliveryTimeStringFormat() {
		return this.deliveryTimeStringFormat;
	}

	/**
	 * Set delivery time in string format.
	 * 
	 * @param deliveryTimeStringFormat
	 */
	public void setDeliveryTimeStringFormat(
			final String deliveryTimeStringFormat) {
		this.deliveryTimeStringFormat = deliveryTimeStringFormat;
	}

}
