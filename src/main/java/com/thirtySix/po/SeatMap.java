package com.thirtySix.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "SEATMAP")
public class SeatMap {

	/**
	 * 地圖編號
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "MAPID")
	private String mapID;

	/**
	 * 位置
	 */
	@Column(name = "LOCATION")
	private String location;

	/**
	 * 地圖字串
	 */
	@Type(type = "text")
	@Column(name = "JSONSTRING")
	private String jsonString;

	/**
	 * 取得地圖編號
	 * 
	 * @return
	 */
	public String getMapID() {
		return mapID;
	}

	/**
	 * 設定地圖編號
	 * 
	 * @param mapID
	 */
	public void setMapID(String mapID) {
		this.mapID = mapID;
	}

	/**
	 * 取得位置
	 * 
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 設定位置
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 取得地圖字串
	 * 
	 * @return
	 */
	public String getJsonString() {
		return jsonString;
	}

	/**
	 * 設定地圖字串
	 * 
	 * @param jsonString
	 */
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

}
