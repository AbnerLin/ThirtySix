package com.thirtySix.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.core.Buffer;
import com.thirtySix.dto.AjaxDTO;
import com.thirtySix.dto.FurnishClassDTO;
import com.thirtySix.dto.SeatMapQDTO;
import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.SeatMap;
import com.thirtySix.service.MapService;
import com.thirtySix.util.ObjectConverter;
import com.thirtySix.webSocket.WebSocketUtil;

@Controller
@RequestMapping(value = { "/map" })
@PreAuthorize("isAuthenticated()")
public class MapController {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private MapService mapService = null;

	@Autowired
	private ObjectConverter objConverter = null;

	@Autowired
	private WebSocketUtil webSocket = null;

	/**
	 * Get Furnish class for design map.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getFurnishClass" })
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
	@Secured("ROLE_ADMIN")
	public AjaxDTO saveSeatMap(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestBody final List<SeatMapQDTO> mapList) {
		final AjaxDTO result = new AjaxDTO();

		mapList.forEach(map -> {
			SeatMap seatMap = new SeatMap();
			if (this.buffer.getSeatMap().containsKey(map.getMapID())) {
				seatMap = this.buffer.getSeatMap().get(map.getMapID());
			}
			seatMap.setMapID(map.getMapID());
			seatMap.setName(map.getName());
			seatMap.setWidth(map.getWidth());
			seatMap.setHeight(map.getHeight());
			final List<Furnish> furnishList = seatMap.getFurnishList();

			final List<Furnish> newFurnishList = this.objConverter
					.furnishDTOtoPO(seatMap, map.getNewFurnishList());
			furnishList.addAll(newFurnishList);

			map.getRemoveFurnishList().forEach(rmFurnishID -> {
				final Iterator<Furnish> iter = furnishList.iterator();
				while (iter.hasNext()) {
					final Furnish furnish = iter.next();
					if (furnish.getFurnishID().equalsIgnoreCase(rmFurnishID))
						iter.remove();
				}
			});

			/** Save to DB */
			this.mapService.saveSeatMap(seatMap);
			this.mapService.saveFurnish(seatMap, seatMap.getFurnishList());

			/** Update to buffer */
			this.buffer.getSeatMap().put(seatMap.getMapID(), seatMap);
		});

		/** Broadcase to client */
		this.webSocket.updateSeatMap(mapList);

		result.setStatusOK();
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

		final List<SeatMap> mapList = this.buffer.getSeatMap().values().stream()
				.collect(Collectors.toList());

		result.setStatusOK();
		result.setData(mapList);

		return result;
	}
}
