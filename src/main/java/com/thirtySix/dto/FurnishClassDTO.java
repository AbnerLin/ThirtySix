package com.thirtySix.dto;

import java.io.Serializable;

import com.thirtySix.model.FurnishClass;

public class FurnishClassDTO implements Serializable {

	private static final long serialVersionUID = -1379112567946869030L;

	/**
	 * 類別編號
	 */
	private String classID;

	/**
	 * If true, it can operator. etc table.
	 */
	private boolean enable;

	/**
	 * furnish class detail
	 */
	private FurnishClass detail;

	/**
	 * Get class id.
	 * 
	 * @return
	 */
	public String getClassID() {
		return classID;
	}

	/**
	 * Set class id.
	 * 
	 * @param classID
	 */
	public void setClassID(String classID) {
		this.classID = classID;
	}

	/**
	 * Get if element enable, if true, it can operator.
	 * 
	 * @return
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * Set if this element enable.
	 * 
	 * @param enable
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * Get furnish detail.
	 * 
	 * @return
	 */
	public FurnishClass getDetail() {
		return detail;
	}

	/**
	 * Set furnish detail.
	 * 
	 * @param detail
	 */
	public void setDetail(FurnishClass detail) {
		this.detail = detail;
	}

}
