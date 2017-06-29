package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.SeatMap;
import com.thirtySix.repository.FurnishClassRepository;
import com.thirtySix.repository.FurnishRepository;
import com.thirtySix.repository.SeatMapRepository;
import com.thirtySix.service.MapService;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private SeatMapRepository seatMapRepo = null;

	@Autowired
	private FurnishRepository furnishRepo = null;

	@Autowired
	private FurnishClassRepository furnishClassRepo = null;

	@Override
	@Transactional
	public void saveSeatMap(SeatMap po) {
		seatMapRepo.save(po);
	}

	@Override
	@Transactional
	public void saveFurnish(SeatMap map, List<Furnish> furnishList) {
		/** delete first */
		furnishRepo.deleteBySeatMap(map.getMapID());

		/** save */
		furnishRepo.save(furnishList);
	}

	@Override
	public List<SeatMap> getSeatMap() {
		return (List<SeatMap>) seatMapRepo.findAll();
	}

	@Override
	public FurnishClass findFurnishClass(String classID) {
		return furnishClassRepo.findOne(classID);
	}

	@Override
	public List<Furnish> findAllFurnish() {
		return (List<Furnish>) furnishRepo.findAll();
	}

	@Override
	public List<FurnishClass> findAllFurnishClass() {
		return (List<FurnishClass>) furnishClassRepo.findAll();
	}

}
