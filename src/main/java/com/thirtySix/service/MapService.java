package com.thirtySix.service;

import java.util.List;
import java.util.Map;

import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.SeatMap;

public interface MapService {

	/**
	 * Save seat map.
	 * 
	 * @param po
	 */
	public void saveSeatMap(SeatMap po);

	/**
	 * Save furnish list.
	 * 
	 * @param furnishList
	 */
	public void saveFurnish(List<Furnish> furnishList);

	/**
	 * Delete furnish with list.
	 * 
	 * @param furnishIdList
	 */
	public void deleteFurnish(List<String> furnishIdList);

	/**
	 * Save furnish class.
	 * 
	 * @param furnishClass
	 */
	public void saveFurnishClass(FurnishClass furnishClass);

	/**
	 * Get seat map list.
	 * 
	 * @return
	 */
	public Map<String, SeatMap> findAllSeatMap();

	/**
	 * Find furnish class by id.
	 * 
	 * @param classID
	 * @return
	 */
	public FurnishClass findFurnishClass(String classID);

	/**
	 * Find all furnish class.
	 * 
	 * @return
	 */
	public Map<String, FurnishClass> findAllFurnishClass();

}
