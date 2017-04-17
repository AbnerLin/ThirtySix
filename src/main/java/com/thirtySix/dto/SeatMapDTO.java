package com.thirtySix.dto;


public class SeatMapDTO {
	/**
	 * 地圖編號
	 */
	private String mapID;

	/**
	 * 位置
	 */
	private String location;

	/**
	 * 地圖字串
	 */
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
