package com.thirtySix.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.thirtySix.model.SeatMap;
import com.thirtySix.repository.SeatMapRepository;
import com.thirtySix.service.MapService;

public class MapServiceImpl implements MapService {

	private class SeatMapServcieImpl extends CommonServiceImpl<SeatMap> {
		
		@Autowired
		private static SeatMapRepository repository;
		
		public SeatMapServcieImpl(CrudRepository repository) {
			super(repository);
			// TODO Auto-generated constructor stub
		}

	}
}
