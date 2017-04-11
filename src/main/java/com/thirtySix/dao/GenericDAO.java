package com.thirtySix.dao;

import java.io.Serializable;


public interface GenericDAO<T> {

	/**
	 * 新增
	 * 
	 * @param po
	 */
	public Serializable insert(T po);

	/**
	 * 更新
	 * 
	 * @param po
	 */
	public void update(T po);

	/**
	 * 刪除
	 * 
	 * @param po
	 */
	public void delete(T po);

}
