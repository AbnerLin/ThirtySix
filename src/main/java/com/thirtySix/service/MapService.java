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
	 * Get seat map list.
	 * 
	 * @return
	 */
	public List<SeatMap> getSeatMap();

	/**
	 * Find furnish class by id.
	 * 
	 * @param classID
	 * @return
	 */
	public FurnishClass findFurnishClass(String classID);
}
