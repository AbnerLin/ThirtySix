package com.thirtySix.po;

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
@Table(name = "SEATPOSITION")
public class SeatPosition {

	/**
	 * 擺設編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "FURNISHID")
	private String furnishID;

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
	 * 名稱
	 */
	@Column(name = "DISPLAYTEXT")
	private String displayText;

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
	 * 取得名稱
	 * 
	 * @return
	 */
	public String getDisplayText() {
		return displayText;
	}

	/**
	 * 設定名稱
	 * 
	 * @param text
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
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

}
