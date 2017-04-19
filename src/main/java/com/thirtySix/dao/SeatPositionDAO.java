package com.thirtySix.dao;

import com.thirtySix.po.SeatPosition;

public interface SeatPositionDAO extends GenericDAO<SeatPosition> {
	
	/**
	 * 透過地圖編號 刪除做標點位
	 * 
	 * @param mapID
	 */
	public void deleteByMapID(String mapID);
}
