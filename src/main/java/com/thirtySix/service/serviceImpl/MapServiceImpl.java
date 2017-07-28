package com.thirtySix.service.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

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

	/** furnish class buffer <classID, furnishClass> */
	private Map<String, FurnishClass> furnishClassBuffer = new ConcurrentHashMap<String, FurnishClass>();

	/** map buffer <mapId, SeatMap> */
	private Map<String, SeatMap> mapBuffer = new ConcurrentHashMap<String, SeatMap>();

	@PostConstruct
	public void init() {
		/** load map */
		this.loadMap();

		/** load furnish */
		this.loadFurnishClass();
	}

	@Override
	@Transactional
	public void saveSeatMap(final SeatMap po) {
		/** Save to DB */
		this.seatMapRepo.save(po);

		/** Update Buffer */
		this.mapBuffer.put(po.getMapID(), po);
	}

	@Override
	@Transactional
	public void saveFurnish(final List<Furnish> furnishList) {
		/** Save to DB */
		this.furnishRepo.save(furnishList);

		/** Update buffer */
		furnishList.forEach(furnish -> {
			this.mapBuffer.get(furnish.getSeatMap().getMapID()).getFurnishList()
					.add(furnish);
		});
	}

	@Override
	@Transactional
	public void deleteFurnish(final List<String> furnishIdList) {
		furnishIdList.forEach(id -> {
			/** Delete from DB */
			this.furnishRepo.delete(id);

			/** Update buffer */
			this.mapBuffer.forEach((final String mapId, final SeatMap map) -> {
				map.getFurnishList()
						.removeIf(furnish -> furnish.getFurnishID().equals(id));
			});
		});
	}

	@Override
	public Map<String, SeatMap> findAllSeatMap() {
		return this.mapBuffer;
	}

	@Override
	public FurnishClass findFurnishClass(final String classID) {
		if (this.furnishClassBuffer.containsKey(classID))
			return this.furnishClassBuffer.get(classID);
		return null;
	}

	@Override
	public Map<String, FurnishClass> findAllFurnishClass() {
		return this.furnishClassBuffer;
	}

	@Override
	@Transactional
	public void saveFurnishClass(final FurnishClass furnishClass) {
		/** Save to DB */
		this.furnishClassRepo.save(furnishClass);

		/** Update buffer */
		this.furnishClassBuffer.put(furnishClass.getClassID(), furnishClass);
	}

	/**
	 * Load map info.
	 */
	private void loadMap() {
		final List<SeatMap> mapList = (List<SeatMap>) this.seatMapRepo
				.findAll();
		mapList.forEach(map -> {
			this.mapBuffer.put(map.getMapID(), map);
		});
	}

	/**
	 * load furnishClass to buffer.
	 */
	private void loadFurnishClass() {
		final List<FurnishClass> furnishClassList = (List<FurnishClass>) this.furnishClassRepo
				.findAll();

		furnishClassList.forEach(furnishClass -> {
			this.furnishClassBuffer.put(furnishClass.getClassID(),
					furnishClass);
		});
	}

}
