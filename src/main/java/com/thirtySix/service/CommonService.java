package com.thirtySix.service;

import java.util.List;

public interface CommonService<T> {
	
	/**
	 * insert data.
	 * 
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * select *.
	 * 
	 * @return
	 */
	public List<T> findAll();
}
