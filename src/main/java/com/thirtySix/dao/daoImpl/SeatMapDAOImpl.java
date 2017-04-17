package com.thirtySix.dao.daoImpl;

import org.springframework.stereotype.Repository;

import com.thirtySix.dao.SeatMapDAO;
import com.thirtySix.po.SeatMap;

@Repository
public class SeatMapDAOImpl extends GenericDAOImpl<SeatMap> implements
		SeatMapDAO {

	public SeatMapDAOImpl() {
		super(SeatMap.class);
	}

}
