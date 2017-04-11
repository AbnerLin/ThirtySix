package com.thirtySix.dao;

import java.io.Serializable;


public interface GenericDAO<T> {

	/**
	 * �s�W
	 * 
	 * @param po
	 */
	public Serializable insert(T po);

	/**
	 * ��s
	 * 
	 * @param po
	 */
	public void update(T po);

	/**
	 * �R��
	 * 
	 * @param po
	 */
	public void delete(T po);

}
