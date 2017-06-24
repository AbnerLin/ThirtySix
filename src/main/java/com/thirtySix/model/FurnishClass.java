package com.thirtySix.model;

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
@Table(name = "FURNISHCLASS")
public class FurnishClass {

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
	@Column(name = "NAME")
	private String name;

	/**
	 * 圖檔路徑
	 */
	@Column(name = "IMAGEPATH")
	private String imagePath;

	/**
	 * 座位清單
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "furnishClass")
	private List<Furnish> seatList;

	/**
	 * 取得類別編號
	 * 
	 * @return
	 */
	public String getClassID() {
		return classID;
	}

	/**
	 * 設定類別編號
	 * 
	 * @param classID
	 */
	public void setClassID(String classID) {
		this.classID = classID;
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
	 * @param text
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得屬於該類別的項目
	 * 
	 * @return
	 */
	public List<Furnish> getSeatList() {
		return seatList;
	}

	/**
	 * 設定該類別的所屬項目
	 * 
	 * @param seatList
	 */
	public void setSeatList(List<Furnish> seatList) {
		this.seatList = seatList;
	}

}
