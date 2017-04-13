package com.thirtySix.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	 * 圖檔路徑
	 */
	@Column(name = "IMAGEPATH")
	private String imagePath;

	/**
	 * 項目價格
	 */
	@Column(name = "PRICE")
	private int price;

	/**
	 * 項目敘述
	 */
	@Column(name = "DESCRIPTION")
	private String description;
	
	/**
	 * 項目種類
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CLASSID", referencedColumnName = "CLASSID", nullable = false)
	private ItemClass itemClass;
	
	/**
	 * 是否顯示(1:顯示, 0:不顯示)
	 */
	@Column(name = "ISDISPLAY")
	private int isDisplay = 1;

	/**
	 * 訂單列表
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<Booking> bookingList;

	/**
	 * 取得項目編號
	 * 
	 * @return
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * 取得項目名稱
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 設定項目名稱
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得圖檔路徑
	 * 
	 * @return
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * 設定圖檔路徑
	 * 
	 * @param imagePath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * 取得價格
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 設定價格
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
	 * 取得該項目是否顯示(1:顯示, 0:不顯示)
	 * 
	 * @return
	 */
	public int getIsDisplay() {
		return isDisplay;
	}

	/**
	 * 設定該項目是否顯示(1:顯示, 0:不顯示)
	 * 
	 * @param block
	 */
	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}

	/**
	 * 取得訂單列表
	 * 
	 * @return
	 */
	public List<Booking> getBookingList() {
		return bookingList;
	}

	/**
	 * 取得項目種類
	 * 
	 * @return
	 */
	public ItemClass getItemClass() {
		return itemClass;
	}

	/**
	 * 設定項目種類
	 * 
	 * @param itemClass
	 */
	public void setItemClass(ItemClass itemClass) {
		this.itemClass = itemClass;
	}

}
