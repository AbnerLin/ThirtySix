package com.thirtySix.dto;

public class FurnishQDTO {

	/**
	 * 擺設編號
	 */
	private String furnishID;

	/**
	 * X座標
	 */
	private int x;

	/**
	 * Y座標
	 */
	private int y;

	/**
	 * 名稱
	 */
	private String name;

	/**
	 * 裝飾類別
	 */
	private String furnishClassID;

	// /**
	// * 地圖資訊
	// */
	// private SeatMap seatMap;

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
	 * 取得名稱
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 設定名稱
	 * 
	 * @param text
	 */
	public void setName(final String name) {
		this.name = name;
	}

	// /**
	// * 取得地圖資訊
	// *
	// * @return
	// */
	// public SeatMap getSeatMap() {
	// return seatMap;
	// }
	//
	// /**
	// * 設定地圖資訊
	// *
	// * @param seatMap
	// */
	// public void setSeatMap(final SeatMap seatMap) {
	// this.seatMap = seatMap;
	// }

	/**
	 * 取得裝飾類別
	 * 
	 * @return
	 */
	public String getFurnishClassID() {
		return this.furnishClassID;
	}

	/**
	 * 設定裝飾類別
	 * 
	 * @param furnishClass
	 */
	public void setFurnishClassID(final String furnishClassID) {
		this.furnishClassID = furnishClassID;
	}

}
