package com.thirtySix.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ITEMCLASS")
public class ItemClass {

	public static enum CLASS {
		HOTPOT, MEAT, VEGETABLE, SEAFOOD, INDEPENDENT;
	}

	/**
	 * 種類編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "CLASSID")
	private String classID;

	/**
	 * 種類名稱
	 */
	@Column(name = "CLASSNAME")
	private String className;

	/**
	 * Image path.
	 */
	@Column(name = "IMAGEPATH")
	private String imagePath;

	/**
	 * Meal class description.
	 */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * Meal type.
	 */
	@Column(name = "MEALTYPE")
	@Enumerated(EnumType.STRING)
	private CLASS mealType;

	/**
	 * 項目
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "itemClass")
	private List<Item> itemList;

	/**
	 * 取得種類編號
	 * 
	 * @return
	 */
	public String getClassID() {
		return this.classID;
	}

	/**
	 * 取得種類名稱
	 * 
	 * @return
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * 設定種類名稱
	 * 
	 * @param className
	 */
	public void setClassName(final String className) {
		this.className = className;
	}

	/**
	 * Get the meal type.
	 * 
	 * @return
	 */
	public CLASS getMealType() {
		return this.mealType;
	}

	/**
	 * Set the meal type.
	 * 
	 * @param mealType
	 */
	public void setMealType(final CLASS mealType) {
		this.mealType = mealType;
	}

	/**
	 * 取得項目列表
	 * 
	 * @return
	 */
	public List<Item> getItemList() {
		return this.itemList;
	}

	/**
	 * 設定項目列表
	 * 
	 * @param itemList
	 */
	public void setItemList(final List<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * Get image file path.
	 * 
	 * @return
	 */
	public String getImagePath() {
		return this.imagePath;
	}

	/**
	 * Set image file path.
	 * 
	 * @param imagePath
	 */
	public void setImagePath(final String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Get meal description.
	 * 
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set meal description.
	 * 
	 * @param description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

}
