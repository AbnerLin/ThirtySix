package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.core.Buffer;
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

	@Autowired
	private Buffer buffer = null;

	@Override
	@Transactional
	public void saveSeatMap(final SeatMap po) {
		this.seatMapRepo.save(po);
	}

	@Override
	@Transactional
	public void saveFurnish(final SeatMap map,
			final List<Furnish> furnishList) {
		/** Delete first */
		this.furnishRepo.deleteBySeatMap(map);

		/** Save */
		this.furnishRepo.save(furnishList);

		/** Update buffer */
		map.setFurnishList(furnishList);
		this.buffer.getSeatMap().put(map.getMapID(), map);
	}

	@Override
	public List<SeatMap> findAllSeatMap() {
		return (List<SeatMap>) this.seatMapRepo.findAll();
	}

	@Override
	public FurnishClass findFurnishClass(final String classID) {
		return this.furnishClassRepo.findOne(classID);
	}

	@Override
	public List<Furnish> findAllFurnish() {
		return (List<Furnish>) this.furnishRepo.findAll();
	}

	@Override
	public List<FurnishClass> findAllFurnishClass() {
		return (List<FurnishClass>) this.furnishClassRepo.findAll();
	}

	@Override
	@Transactional
	public void saveFurnishClass(final FurnishClass furnishClass) {
		/** Save to DB */
		this.furnishClassRepo.save(furnishClass);

		/** Update buffer */
		this.buffer.getFurnishClass().put(furnishClass.getClassID(),
				furnishClass);
	}

}
