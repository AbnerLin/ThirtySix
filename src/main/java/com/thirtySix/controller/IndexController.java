package com.thirtySix.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thirtySix.Core.Buffer;
import com.thirtySix.Core.DBManager;
import com.thirtySix.dto.AjaxDTO;
import com.thirtySix.dto.BookingDTO;
import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.dto.ItemDTO;
import com.thirtySix.dto.SeatMapDTO;
import com.thirtySix.po.Booking;
import com.thirtySix.po.Customer;
import com.thirtySix.po.ItemClass;
import com.thirtySix.po.SeatMap;
import com.thirtySix.po.SeatPosition;
import com.thirtySix.util.ObjectConverter;

@Controller
public class IndexController {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private DBManager dbManager = null;

	@Autowired
	private ObjectConverter objConverter = null;

	@Autowired
	private SimpMessagingTemplate messageTemplate = null;

	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value = { "/1" })
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("test");
	}

	/**
	 * 取得用餐中顧客
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getDiningCustomer" })
	public AjaxDTO getDiningCustomer(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();
		Map<String, Customer> diningCustomer = buffer.getDiningCustomer();
		result.setStatusOK();
		result.setData(diningCustomer);

		return result;
	}

	/**
	 * 顧客check in
	 * 
	 * @param request
	 * @param response
	 * @param customerDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/customerCheckIn" })
	public AjaxDTO customerCheckIn(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute CustomerDTO customerDTO) {
		AjaxDTO result = new AjaxDTO();

		/** 設定進場時間 */
		Date now = new Date();
		Timestamp time = new Timestamp(now.getTime());
		customerDTO.setCheckInTime(time);

		/** 新增資料庫 */
		Customer po = objConverter.customerDTOtoPO(customerDTO);
		dbManager.insertCustomer(po);

		/** 更新buffer */
		Map<String, Customer> bufferMap = buffer.getDiningCustomer();
		bufferMap.put(po.getCustomerID(), po);

		/** push socket to every client */
		this.diningCustomerUpdate(bufferMap);

		result.setStatusOK();
		result.setData(po);
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
	@RequestMapping(value = { "/saveSeatMap" }, consumes = "application/json", produces = "application/json")
	private AjaxDTO saveSeatMap(HttpServletRequest request,
			HttpServletResponse response, @RequestBody SeatMapDTO seatMapDTO) {
		AjaxDTO result = new AjaxDTO();

		/** map save */
		SeatMap mapPO = objConverter.seatMapDTOtoPO(seatMapDTO);
		if (mapPO.getMapID().equals(""))
			dbManager.insertSeatMap(mapPO);
		else
			dbManager.updateSeatMap(mapPO);

		/** position save */
		List<SeatPosition> positionList = objConverter.seatPositionDTOtoPO(
				mapPO, seatMapDTO.getSeatPositionList());
		dbManager.insertSeatPosition(mapPO, positionList);

		//TODO
		// for(SeatPositionDTO position : seatMapDTO.getSeatPositionList()) {
		// System.out.println(position.getFurnishID());
		// System.out.println(position.getDisplayText());
		// System.out.println(position.getX());
		// System.out.println(position.getY());
		// }

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
	private AjaxDTO getSeatMap(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();

		List<SeatMap> mapList = dbManager.getSeatMap();

		result.setStatusOK();
		result.setData(mapList);

		return result;
	}

	/**
	 * 取得菜單
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getMenu" })
	private AjaxDTO getMenu(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();

		Map<String, ItemClass> menu = buffer.getMenu();

		result.setStatusOK();
		result.setData(menu);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = { "/sendOrder" }, consumes = "application/json", produces = "application/json")
	private AjaxDTO sendOrder(HttpServletRequest request,
			HttpServletResponse response, @RequestBody BookingDTO bookingDTO) {
		AjaxDTO result = new AjaxDTO();

		for (ItemDTO item : bookingDTO.getItemList()) {
			Booking booking = objConverter.bookingDTOtoPO(bookingDTO,
					item.getItemId(), item.getVolume());

			/** save to db */
			dbManager.insertBooking(booking);

			/** update buffer */
			String customerId = bookingDTO.getCustomerId();
			Customer customer = buffer.getDiningCustomer().get(customerId);
			customer.getBookingList().add(booking);

			/** send to client */
			this.diningCustomerUpdate(buffer.getDiningCustomer());
		}

		result.setStatusOK();
		return result;
	}

	/**
	 * 用餐中顧客 更新(發布socket topic)
	 * 
	 * @return
	 */
	public void diningCustomerUpdate(Map<String, Customer> bufferMap) {
		logger.info("用餐中顧客更新，發佈WebSocket");
		this.messageTemplate.convertAndSend("/topic/customerUpdate", bufferMap);
	}
}
