package com.thirtySix.dto;

/**
 * Customer check in information.
 */
public class CheckInInfoQDTO {

	/**
	 * Customer name.
	 */
	private String customerName;

	/**
	 * Customer contact phone.
	 */
	private String customerPhone;

	/**
	 * People Count.
	 */
	private Integer peopleCount;

	/**
	 * Furnish Id which customer uses.
	 */
	private String furnishId;

	/**
	 * @return Customer name.
	 */
	public String getCustomerName() {
		return this.customerName;
	}

	/**
	 * @param customerName
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return Customer contact phone.
	 */
	public String getCustomerPhone() {
		return this.customerPhone;
	}

	/**
	 * @param customerPhone
	 */
	public void setCustomerPhone(final String customerPhone) {
		this.customerPhone = customerPhone;
	}

	/**
	 * @return People count.
	 */
	public Integer getPeopleCount() {
		return this.peopleCount;
	}

	/**
	 * @param peopleCount
	 */
	public void setPeopleCount(final Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	/**
	 * @return Furnish id.
	 */
	public String getFurnishId() {
		return this.furnishId;
	}

	/**
	 * @param furnishId
	 */
	public void setFurnishId(final String furnishId) {
		this.furnishId = furnishId;
	}

}
