package com.thirtySix.dto;

import java.util.List;

public class SeatMapQDTO {

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
	 * New add furnish list.
	 */
	private List<FurnishQDTO> newFurnishList;

	/**
	 * Furnishs which have to remove.
	 */
	private List<String> removeFurnishList;

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
	 * Get new add furnishs.
	 * 
	 * @return
	 */
	public List<FurnishQDTO> getNewFurnishList() {
		return this.newFurnishList;
	}

	/**
	 * Set new add furnishs.
	 * 
	 * @param newFurnishList
	 */
	public void setNewFurnishList(final List<FurnishQDTO> newFurnishList) {
		this.newFurnishList = newFurnishList;
	}

	/**
	 * Get furnishs which have to remove.
	 * 
	 * @return
	 */
	public List<String> getRemoveFurnishList() {
		return this.removeFurnishList;
	}

	/**
	 * Set furnishs which have to remove.
	 * 
	 * @param removeFurnishList
	 */
	public void setRemoveFurnishList(final List<String> removeFurnishList) {
		this.removeFurnishList = removeFurnishList;
	}

}
