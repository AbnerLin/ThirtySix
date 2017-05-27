package com.thirtySix.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {

	/**
	 * SELECT by ID
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/**
	 * SELECT ALL
	 * 
	 * @return
	 */
	public List<T> getAll();

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
