package com.thirtySix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FURNISH")
public class Furnish {

	/**
	 * 擺設編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "FURNISHID")
	private String furnishID;

	@Column(name = "NAME", unique = true, nullable = true)
	private String name;

	/**
	 * X座標
	 */
	@Column(name = "X")
	private int x;

	/**
	 * Y座標
	 */
	@Column(name = "Y")
	private int y;

	/**
	 * 裝飾類別
	 */
	@ManyToOne
	@JoinColumn(name = "FURNISHCLASS", referencedColumnName = "CLASSID", nullable = false)
	private FurnishClass furnishClass;

	/**
	 * 地圖資訊
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "MAPID", referencedColumnName = "MAPID", nullable = false)
	private SeatMap seatMap;

	/**
	 * 取得擺設編號
	 * 
	 * @return
	 */
	public String getFurnishID() {
		return furnishID;
	}

	/**
	 * 設定擺設編號
	 * 
	 * @param furnishID
	 */
	public void setFurnishID(String furnishID) {
		this.furnishID = furnishID;
	}

	/**
	 * 取得X座標
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * 設定X座標
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 取得Y座標
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * 設定Y座標
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 取得地圖資訊
	 * 
	 * @return
	 */
	public SeatMap getSeatMap() {
		return seatMap;
	}

	/**
	 * 設定地圖資訊
	 * 
	 * @param seatMap
	 */
	public void setSeatMap(SeatMap seatMap) {
		this.seatMap = seatMap;
	}

	/**
	 * 取得裝飾類別
	 * 
	 * @return
	 */
	public FurnishClass getFurnishClass() {
		return furnishClass;
	}

	/**
	 * 設定裝飾類別
	 * 
	 * @param furnishClass
	 */
	public void setFurnishClass(FurnishClass furnishClass) {
		this.furnishClass = furnishClass;
	}

	/**
	 * Get furnish name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set furnish name
	 * 
	 * @param text
	 */
	public void setName(String name) {
		this.name = name;
	}

}
