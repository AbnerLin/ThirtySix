package com.thirtySix.repository;

import com.thirtySix.model.SeatPosition;

public interface SeatPositionDAO extends GenericDAO<SeatPosition> {
	
	/**
	 * 透過地圖編號 刪除做標點位
	 * 
	 * @param mapID
	 */
	public void deleteByMapID(String mapID);
}
