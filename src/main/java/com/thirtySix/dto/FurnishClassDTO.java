package com.thirtySix.dto;

import java.io.Serializable;

public class FurnishClassDTO implements Serializable {

	private static final long serialVersionUID = -1379112567946869030L;

	/**
	 * 類別編號
	 */
	private String classID;

	/**
	 * 名稱
	 */
	private String displayText;

	/**
	 * 圖檔路徑
	 */
	private String imagePath;

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

}
