package com.thirtySix.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.SeatMap;
import com.thirtySix.service.MapService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class MapServiceTest {

	@Autowired
	private MapService mapService = null;

	@Test
	public void testSave() {
		{
			/** Get map */
			final Map<String, SeatMap> mapList = this.mapService
					.findAllSeatMap();
			Assert.assertEquals(1, mapList.size());

			/** Save map */
			final SeatMap map = new SeatMap();
			map.setName("hello");
			map.setWidth(100);
			map.setHeight(100);
			this.mapService.saveSeatMap(map);
			Assert.assertEquals(2, this.mapService.findAllSeatMap().size());
		}

		{
			/** Get furnish class */
			final Map<String, FurnishClass> furnishClassList = this.mapService
					.findAllFurnishClass();
			Assert.assertEquals(6, furnishClassList.size());
		}

		{
			/** Save furnish class */
			final FurnishClass furnishClass = new FurnishClass();
			furnishClass.setName(FurnishClass.CLASS.KITCHEN);
			this.mapService.saveFurnishClass(furnishClass);

			final Map<String, FurnishClass> furnishClassList = this.mapService
					.findAllFurnishClass();
			Assert.assertEquals(7, furnishClassList.size());
		}

		{
			/** Get furnish */
			final int furnishSize = (int) this.mapService.findAllSeatMap()
					.entrySet().stream().map(map -> map.getValue())
					.flatMap(value -> value.getFurnishList().stream()).count();
			Assert.assertEquals(15, furnishSize);
		}

		{
			/** Save furnish */
			final SeatMap map = this.mapService.findAllSeatMap()
					.get("4cd7c00c-cf6e-4cdc-afbb-c3b1c8b013b3");

			final FurnishClass furnishClass = this.mapService
					.findAllFurnishClass()
					.get("f8888e43-fe4b-4751-a1cd-3e9110a074a2");

			final Furnish furnish = new Furnish();
			final List<Furnish> newFurnishList = new ArrayList<Furnish>();
			furnish.setSeatMap(map);
			furnish.setFurnishClass(furnishClass);
			newFurnishList.add(furnish);
			this.mapService.saveFurnish(newFurnishList);

			final int furnishSize = (int) this.mapService.findAllSeatMap()
					.entrySet().stream().map(_map -> _map.getValue())
					.flatMap(value -> value.getFurnishList().stream()).count();

			Assert.assertEquals(16, furnishSize);
		}
	}
}
