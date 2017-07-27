package com.thirtySix.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thirtySix.core.Buffer;
import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.SeatMap;
import com.thirtySix.service.MapService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class MapServiceTest {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private MapService mapService = null;

	@Test
	public void testSave() {
		/** Get map */
		final List<SeatMap> mapList = this.mapService.findAllSeatMap();
		Assert.assertEquals(1, mapList.size());
		Assert.assertEquals(1, this.buffer.getSeatMap().size());

		/** Save map */
		final SeatMap map = new SeatMap();
		map.setName("hello");
		map.setWidth(100);
		map.setHeight(100);
		this.mapService.saveSeatMap(map);
		Assert.assertEquals(2, this.mapService.findAllSeatMap().size());

		/** Get furnish class */
		List<FurnishClass> furnishClassList = this.mapService
				.findAllFurnishClass();
		Assert.assertEquals(6, furnishClassList.size());
		Assert.assertEquals(6, this.buffer.getFurnishClass().size());

		/** Save furnish class */
		final FurnishClass furnishClass = new FurnishClass();
		furnishClass.setName(FurnishClass.CLASS.KITCHEN);
		this.mapService.saveFurnishClass(furnishClass);

		furnishClassList = this.mapService.findAllFurnishClass();
		Assert.assertEquals(7, furnishClassList.size());

		/** Get furnish */
		List<Furnish> furnishList = this.mapService.findAllFurnish();
		Assert.assertEquals(15, furnishList.size());

		/** Save furnish */
		final Furnish furnish = new Furnish();
		final List<Furnish> newFurnishList = new ArrayList<Furnish>();
		furnish.setSeatMap(map);
		furnish.setFurnishClass(furnishClass);
		newFurnishList.add(furnish);
		this.mapService.saveFurnish(map, newFurnishList);

		furnishList = this.mapService.findAllFurnish();
		Assert.assertEquals(16, furnishList.size());
	}
}
