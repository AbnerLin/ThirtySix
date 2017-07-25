package com.thirtySix.model;

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
@Table(name = "FURNISH")
public class Furnish {

	/**
	 * 擺設編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "com.thirtySix.model.id.generator.UuidGenerator")
	@Column(name = "FURNISHID")
	private String furnishID;

	/**
	 * furnish name.
	 */
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
	 * customer whom use this furnish
	 */
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "furnish")
	private List<Customer> customerList;

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
		return this.furnishID;
	}

	/**
	 * 設定擺設編號
	 * 
	 * @param furnishID
	 */
	public void setFurnishID(final String furnishID) {
		this.furnishID = furnishID;
	}

	/**
	 * 取得X座標
	 * 
	 * @return
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * 設定X座標
	 * 
	 * @param x
	 */
	public void setX(final int x) {
		this.x = x;
	}

	/**
	 * 取得Y座標
	 * 
	 * @return
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * 設定Y座標
	 * 
	 * @param y
	 */
	public void setY(final int y) {
		this.y = y;
	}

	/**
	 * 取得地圖資訊
	 * 
	 * @return
	 */
	public SeatMap getSeatMap() {
		return this.seatMap;
	}

	/**
	 * 設定地圖資訊
	 * 
	 * @param seatMap
	 */
	public void setSeatMap(final SeatMap seatMap) {
		this.seatMap = seatMap;
	}

	/**
	 * 取得裝飾類別
	 * 
	 * @return
	 */
	public FurnishClass getFurnishClass() {
		return this.furnishClass;
	}

	/**
	 * 設定裝飾類別
	 * 
	 * @param furnishClass
	 */
	public void setFurnishClass(final FurnishClass furnishClass) {
		this.furnishClass = furnishClass;
	}

	/**
	 * Get furnish name
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set furnish name
	 * 
	 * @param text
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get customer whom use the furnish.
	 * 
	 * @return
	 */
	public List<Customer> getCustomerList() {
		return this.customerList;
	}

}
