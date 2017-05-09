package com.thirtySix.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.thirtySix.po.Customer;
import com.thirtySix.po.Item;
import com.thirtySix.util.TimeFormatter;

public class BookingDTO implements Serializable {

	private static final long serialVersionUID = -3673877985938233369L;

	/**
	 * 訂單編號
	 */
	private String bookingID;

	/**
	 * 訂單時間
	 */
	private Timestamp orderTime;

	/**
	 * 訂單時間 字串格式
	 */
	private String orderTimeStringFormat;

	/**
	 * 出貨時間
	 */
	private Timestamp deliveryTime;

	/**
	 * 出貨時間 字串格式
	 */
	private String deliveryTimeStringFormat;

	/**
	 * 顧客資訊
	 */
	private Customer customer;

	/**
	 * 項目資訊
	 */
	private Item item;

	/**
	 * 項目 量
	 */
	private int volume;

	/**
	 * 是否已出餐(1:出餐, 0:未出餐)
	 */
	private int isSend = 0;

	/**
	 * 設定訂單編號
	 * 
	 * @param bookingID
	 */
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
	
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

		Date time = new Date(orderTime.getTime());
		this.setOrderTimeStringFormat(TimeFormatter.getInstance().getTime(time));
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

		if (deliveryTime != null) {
			Date time = new Date(deliveryTime.getTime());
			this.setDeliveryTimeStringFormat(TimeFormatter.getInstance()
					.getTime(time));
		}
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

	/**
	 * 取得訂單時間 字串格式
	 * 
	 * @return
	 */
	public String getOrderTimeStringFormat() {
		return orderTimeStringFormat;
	}

	/**
	 * 設定訂單時間 字串格式
	 * 
	 * @param orderTimeStringFormat
	 */
	public void setOrderTimeStringFormat(String orderTimeStringFormat) {
		this.orderTimeStringFormat = orderTimeStringFormat;
	}

	/**
	 * 取得出貨時間 字串格式
	 * 
	 * @return
	 */
	public String getDeliveryTimeStringFormat() {
		return deliveryTimeStringFormat;
	}

	/**
	 * 設定出貨時間 字串格式
	 * 
	 * @param deliveryTimeStringFormat
	 */
	public void setDeliveryTimeStringFormat(String deliveryTimeStringFormat) {
		this.deliveryTimeStringFormat = deliveryTimeStringFormat;
	}

}
