package com.thirtySix.dto;

/**
 * Item of Order.
 */
public class ItemOfOrderQDTO {

	/**
	 * Item id.
	 */
	private String itemId;

	/**
	 * Item amount.
	 */
	private Integer amount;

	/**
	 * Get item's id.
	 * 
	 * @return
	 */
	public String getItemId() {
		return this.itemId;
	}

	/**
	 * Set item's id.
	 * 
	 * @param itemId
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}

	/**
	 * Get item's amount.
	 * 
	 * @return
	 */
	public Integer getAmount() {
		return this.amount;
	}

	/**
	 * Set item's amount.
	 * 
	 * @param amount
	 */
	public void setAmount(final Integer amount) {
		this.amount = amount;
	}

}
