package com.thirtySix.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ITEM")
public class Item {

	/**
	 * ���ؽs��
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ITEMID")
	private String itemID;

	/**
	 * ���ئW��
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * ���ع��ɦ�m
	 */
	@Column(name = "IMAGEPATH")
	private String imagePath;

	/**
	 * ���ػ���
	 */
	@Column(name = "PRICE")
	private int price;

	/**
	 * ���رԭz
	 */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * �O�_���(1:���, 0:�����)
	 */
	@Column(name = "BLOCK")
	private int block = 1;

	/**
	 * �q���T
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<Booking> bookingList;

	/**
	 * ���o�s��
	 * 
	 * @return
	 */
	public String getMenuID() {
		return itemID;
	}

	/**
	 * ���o�W��
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * �]�w�W��
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���o�Ϯ׸��|
	 * 
	 * @return
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * �]�w�Ϯ׸��|
	 * 
	 * @param imagePath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * ���o�л�
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * �]�w�л�
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * ���o�ԭz
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * �]�w�ԭz
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ���o�Ӷ��� �O�_��� (1:���, 0:�����)
	 * 
	 * @return
	 */
	public int getBlock() {
		return block;
	}

	/**
	 * �]�w�Ӷ��جO�_��� (1:���, 0:�����)
	 * 
	 * @param block
	 */
	public void setBlock(int block) {
		this.block = block;
	}

	/**
	 * ���o�q���T
	 * 
	 * @return
	 */
	public List<Booking> getOrderList() {
		return bookingList;
	}

}
