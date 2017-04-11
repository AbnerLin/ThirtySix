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
	 * 項目編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ITEMID")
	private String itemID;

	/**
	 * 項目名稱
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 項目圖檔位置
	 */
	@Column(name = "IMAGEPATH")
	private String imagePath;

	/**
	 * 項目價錢
	 */
	@Column(name = "PRICE")
	private int price;

	/**
	 * 項目敘述
	 */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * 是否顯示(1:顯示, 0:不顯示)
	 */
	@Column(name = "BLOCK")
	private int block = 1;

	/**
	 * 訂單資訊
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<Booking> bookingList;

	/**
	 * 取得編號
	 * 
	 * @return
	 */
	public String getMenuID() {
		return itemID;
	}

	/**
	 * 取得名稱
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 設定名稱
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得圖案路徑
	 * 
	 * @return
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * 設定圖案路徑
	 * 
	 * @param imagePath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * 取得標價
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 設定標價
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 取得敘述
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 設定敘述
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 取得該項目 是否顯示 (1:顯示, 0:不顯示)
	 * 
	 * @return
	 */
	public int getBlock() {
		return block;
	}

	/**
	 * 設定該項目是否顯示 (1:顯示, 0:不顯示)
	 * 
	 * @param block
	 */
	public void setBlock(int block) {
		this.block = block;
	}

	/**
	 * 取得訂單資訊
	 * 
	 * @return
	 */
	public List<Booking> getOrderList() {
		return bookingList;
	}

}
