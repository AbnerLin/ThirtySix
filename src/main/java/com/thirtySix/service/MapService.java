package com.thirtySix.service;

import java.util.List;

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
	 * Save furnish.
	 * 
	 * @param map
	 * @param furhish
	 */
	public void saveFurnish(SeatMap map, List<Furnish> furnish);

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
	public List<SeatMap> findAllSeatMap();

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
	public List<FurnishClass> findAllFurnishClass();

	/**
	 * Find all furnish.
	 * 
	 * @return
	 */
	public List<Furnish> findAllFurnish();

}
