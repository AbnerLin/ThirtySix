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
	 * �q��s��
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKINGID")
	private String bookingID;

	/**
	 * �q��ɶ�
	 */
	@Column(name = "TIME")
	private Timestamp time;

	/**
	 * �Ȥ�s��
	 */
	@ManyToOne
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID", nullable = false)
	private Customer customer;

	/**
	 * ���ؽs��
	 */
	@ManyToOne
	@JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", nullable = false)
	private Item item;

	/**
	 * ���ضq
	 */
	@Column(name = "VOLUME")
	private int volume;

	/**
	 * ���o�q��s��
	 * 
	 * @return
	 */
	public String getBookingID() {
		return bookingID;
	}

	/**
	 * ���o�q��ɶ�
	 * 
	 * @return
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * �]�w�q��ɶ�
	 * 
	 * @param time
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	/**
	 * ���o�U�ȸ�T
	 * 
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * �]�w�U�Ƚs��
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * ���o���ظ�T
	 * 
	 * @return
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * �]�w���ظ�T
	 * 
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * ���o���� �q
	 * 
	 * @return
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * �]�w���� �q
	 * 
	 * @param volume
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

}
