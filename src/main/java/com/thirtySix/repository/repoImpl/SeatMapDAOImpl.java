package com.thirtySix.repository.repoImpl;

import org.springframework.stereotype.Repository;

import com.thirtySix.model.SeatMap;
import com.thirtySix.repository.SeatMapDAO;

@Repository
public class SeatMapDAOImpl extends GenericDAOImpl<SeatMap> implements
		SeatMapDAO {

	public SeatMapDAOImpl() {
		super(SeatMap.class);
	}

}
