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
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FURNISHCLASS")
public class FurnishClass {

	public static enum CLASS {
		TABLE, TREE, RESTROOM, DOOR, BAR, EMPTY_TABLE;
	}

	/**
	 * 類別編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "CLASSID")
	private String classID;

	/**
	 * 名稱
	 */
	@Column(name = "NAME", unique = true)
	@Enumerated(EnumType.STRING)
	private CLASS name;

	/**
	 * 圖檔路徑
	 */
	@Column(name = "IMAGEPATH")
	private String imagePath;

	/**
	 * Whether show on setting option.
	 */
	@Type(type = "true_false")
	@Column(name = "VISIBLE")
	private Boolean isVisible;

	/**
	 * This furnish capable of being named ?
	 */
	@Type(type = "true_false")
	@Column(name = "NAMEABLE")
	private Boolean isNameable;

	/**
	 * 座位清單
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "furnishClass")
	private List<Furnish> seatList;

	/**
	 * 取得類別編號
	 * 
	 * @return
	 */
	public String getClassID() {
		return this.classID;
	}

	/**
	 * 設定類別編號
	 * 
	 * @param classID
	 */
	public void setClassID(final String classID) {
		this.classID = classID;
	}

	/**
	 * 取得圖檔路徑
	 * 
	 * @return
	 */
	public String getImagePath() {
		return this.imagePath;
	}

	/**
	 * 設定圖檔路徑
	 * 
	 * @param imagePath
	 */
	public void setImagePath(final String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * 取得名稱
	 *
	 * @return
	 */
	public CLASS getName() {
		return this.name;
	}

	/**
	 * 設定名稱
	 *
	 * @param text
	 */
	public void setName(final CLASS name) {
		this.name = name;
	}

	/**
	 * 取得屬於該類別的項目
	 * 
	 * @return
	 */
	public List<Furnish> getSeatList() {
		return this.seatList;
	}

	/**
	 * 設定該類別的所屬項目
	 * 
	 * @param seatList
	 */
	public void setSeatList(final List<Furnish> seatList) {
		this.seatList = seatList;
	}

	/**
	 * Get visible
	 * 
	 * @return
	 */
	public Boolean getIsVisible() {
		return this.isVisible;
	}

	/**
	 * Set visible.
	 * 
	 * @param isVisible
	 */
	public void setIsVisible(final Boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Get nameable.
	 * 
	 * @return
	 */
	public Boolean getIsNameable() {
		return this.isNameable;
	}

	/**
	 * Set this furnish class is nameable.
	 * 
	 * @param nameable
	 */
	public void setIsNameable(final Boolean nameable) {
		this.isNameable = nameable;
	}

}
