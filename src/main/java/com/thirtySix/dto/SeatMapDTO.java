package com.thirtySix.dto;

import java.util.List;

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
	 * 地圖寬度 px
	 */
	private int width;

	/**
	 * 地圖高度 px
	 */
	private int height;

	/**
	 * 擺設座標位置
	 */
	private List<FurnishDTO> seatPositionList;

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
	 * 取得地圖寬度
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 設定地圖寬度
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 取得地圖高度
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 設定地圖高度
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 取得擺設座標清單
	 * 
	 * @return
	 */
	public List<FurnishDTO> getSeatPositionList() {
		return seatPositionList;
	}

	/**
	 * 設定擺設座標清單
	 * 
	 * @param seatPositionList
	 */
	public void setSeatPositionList(List<FurnishDTO> seatPositionList) {
		this.seatPositionList = seatPositionList;
	}
}
