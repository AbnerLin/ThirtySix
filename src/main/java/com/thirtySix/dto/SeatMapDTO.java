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
	private String name;

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
		return this.mapID;
	}

	/**
	 * 設定地圖編號
	 * 
	 * @param mapID
	 */
	public void setMapID(final String mapID) {
		this.mapID = mapID;
	}

	/**
	 * Get name of map.
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set name of map.
	 * 
	 * @param location
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 取得地圖寬度
	 * 
	 * @return
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * 設定地圖寬度
	 * 
	 * @param width
	 */
	public void setWidth(final int width) {
		this.width = width;
	}

	/**
	 * 取得地圖高度
	 * 
	 * @return
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * 設定地圖高度
	 * 
	 * @param height
	 */
	public void setHeight(final int height) {
		this.height = height;
	}

	/**
	 * 取得擺設座標清單
	 * 
	 * @return
	 */
	public List<FurnishDTO> getSeatPositionList() {
		return this.seatPositionList;
	}

	/**
	 * 設定擺設座標清單
	 * 
	 * @param seatPositionList
	 */
	public void setSeatPositionList(final List<FurnishDTO> seatPositionList) {
		this.seatPositionList = seatPositionList;
	}
}
