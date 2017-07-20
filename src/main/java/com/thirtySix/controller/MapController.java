package com.thirtySix.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.core.Buffer;
import com.thirtySix.dto.AjaxDTO;
import com.thirtySix.dto.FurnishClassDTO;
import com.thirtySix.dto.FurnishDTO;
import com.thirtySix.dto.SeatMapQDTO;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.SeatMap;
import com.thirtySix.service.MapService;
import com.thirtySix.util.ObjectConverter;

@Controller
@RequestMapping(value = { "/map" })
public class MapController {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private MapService mapService = null;

	@Autowired
	private ObjectConverter objConverter = null;

	/**
	 * Get Furnish class for design map.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getFurnishClass" })
	@PreAuthorize("isAuthenticated()")
	public AjaxDTO getFurnishClass(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxDTO result = new AjaxDTO();

		final Map<String, FurnishClass> furnishClass = this.buffer
				.getFurnishClass();

		final Map<String, FurnishClassDTO> map = new HashMap<String, FurnishClassDTO>();
		furnishClass.forEach((final String key, final FurnishClass _class) -> {
			final FurnishClassDTO dto = new FurnishClassDTO();
			dto.setClassID(_class.getClassID());

			if (_class.getName().equals(FurnishClass.CLASS.TABLE.name()))
				dto.setEnable(true);
			else
				dto.setEnable(false);
			dto.setDetail(_class);

			map.put(_class.getClassID(), dto);
		});

		result.setStatusOK();
		result.setData(map);

		return result;
	}

	/**
	 * 取得座位表
	 * 
	 * @param request
	 * @param response
	 * @param seatMapDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getSeatMap" })
	public AjaxDTO getSeatMap(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxDTO result = new AjaxDTO();

		final List<SeatMap> mapList = this.mapService.findAllSeatMap();

		result.setStatusOK();
		result.setData(mapList);

		return result;
	}

	/**
	 * 儲存座位表
	 * 
	 * @param request
	 * @param response
	 * @param seatMapDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/saveSeatMap" }, //
			consumes = "application/json", //
			produces = "application/json")
	public AjaxDTO saveSeatMap(final HttpServletRequest request, //
			final HttpServletResponse response, //
			@RequestBody final List<SeatMapQDTO> seatMapDTO) {
		final AjaxDTO result = new AjaxDTO();

		for (final SeatMapQDTO seatMapDTO1 : seatMapDTO) {
			System.out.println(seatMapDTO1.getMapID());
			System.out.println(seatMapDTO1.getName());
			System.out.println(seatMapDTO1.getWidth());
			System.out.println(seatMapDTO1.getHeight());
			System.out.println(seatMapDTO1.getNewFurnishList().size());

			for (final FurnishDTO furnish : seatMapDTO1.getNewFurnishList()) {
				System.out.println(furnish.getFurnishID());
				System.out.println(furnish.getFurnishClassID());
				System.out.println(furnish.getName());
				System.out.println(furnish.getX());
				System.out.println(furnish.getY());
			}

			for (final String delete : seatMapDTO1.getRemoveFurnishList())
				System.out.println(delete);
		}
		// TODO broadcast to update map.

		// /** map save */
		// final SeatMap mapPO = this.objConverter.seatMapDTOtoPO(seatMapDTO);
		// this.mapService.saveSeatMap(mapPO);
		//
		// /** furnish save */
		// final List<Furnish> furnishList = this.objConverter
		// .furnishDTOtoPO(mapPO, seatMapDTO.getFurnishList());
		//
		// this.mapService.saveFurnish(mapPO, furnishList);
		result.setStatusOK();
		return result;
	}
}
