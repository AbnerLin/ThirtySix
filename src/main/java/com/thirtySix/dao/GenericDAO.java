package com.thirtySix.dao;

import java.io.Serializable;


public interface GenericDAO<T> {

	/**
	 * INSERT
	 * 
	 * @param po
	 */
	public Serializable insert(T po);

	/**
	 * UPDATE
	 * 
	 * @param po
	 */
	public void update(T po);

	/**
	 * DELETE
	 * 
	 * @param po
	 */
	public void delete(T po);

}
