package com.thirtySix.dto;

import java.util.List;

/**
 * Order query dto.
 */
public class OrderQDTO {

	/**
	 * Customer's id.
	 */
	private String customerId;

	private List<ItemOfOrderQDTO> itemList;

	/**
	 * Get Customer's id.
	 * 
	 * @return
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * Set Customer's id.
	 * 
	 * @param customerId
	 */
	public void setCustomerId(final String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Get item list.
	 * 
	 * @return
	 */
	public List<ItemOfOrderQDTO> getItemList() {
		return this.itemList;
	}

	/**
	 * Set item list.
	 * 
	 * @param itemList
	 */
	public void setItemList(final List<ItemOfOrderQDTO> itemList) {
		this.itemList = itemList;
	}

}
