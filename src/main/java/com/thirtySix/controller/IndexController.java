package com.thirtySix.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.thirtySix.core.Buffer;
import com.thirtySix.dto.AjaxDTO;
import com.thirtySix.dto.CheckOutDTO;
import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.dto.FurnishClassDTO;
import com.thirtySix.dto.OrderDTO;
import com.thirtySix.dto.SeatMapDTO;
import com.thirtySix.model.Booking;
import com.thirtySix.model.Customer;
import com.thirtySix.model.Furnish;
import com.thirtySix.model.FurnishClass;
import com.thirtySix.model.ItemClass;
import com.thirtySix.model.SeatMap;
import com.thirtySix.service.BookingService;
import com.thirtySix.service.CustomerService;
import com.thirtySix.service.MapService;
import com.thirtySix.util.ObjectConverter;

@Controller
public class IndexController {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private CustomerService customerService = null;

	@Autowired
	private MapService mapService = null;

	@Autowired
	private BookingService bookingService = null;

	@Autowired
	private ObjectConverter objConverter = null;

	@Autowired
	private SimpMessagingTemplate messageTemplate = null;

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Get Furnish class for design map.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getFurnishClass" })
	public AjaxDTO getFurnishClass(HttpServletRequest request, HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();

		List<FurnishClass> furnishClass = mapService.findAllFurnishClass();

		Map<String, FurnishClassDTO> map = new HashMap<String, FurnishClassDTO>();
		furnishClass.forEach(_class -> {
			FurnishClassDTO dto = new FurnishClassDTO();
			dto.setClassID(_class.getClassID());
			if(_class.getName().equals(FurnishClass.CLASS.TABLE.name()))
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
	 * 取得用餐中顧客
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = { "/getDiningCustomer" })
	public AjaxDTO getDiningCustomer(HttpServletRequest request, HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();
		Map<String, Customer> diningCustomer = buffer.getDiningCustomer();

		Map<String, CustomerDTO> diningCustomerDTO = new HashMap<String, CustomerDTO>();
		Iterator iter = diningCustomer.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry pair = (Entry) iter.next();
			String customerId = (String) pair.getKey();
			Customer customerPO = (Customer) pair.getValue();
			diningCustomerDTO.put(customerId, objConverter.customerPOtoDTO(customerPO));
		}

		result.setStatusOK();
		result.setData(diningCustomerDTO);

		return result;
	}

	/**
	 * 顧客check out
	 * 
	 * @param request
	 * @param response
	 * @param checkOutDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/customerCheckOut" })
	public AjaxDTO customerCheckOut(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CheckOutDTO checkOutDto) {
		AjaxDTO result = new AjaxDTO();

		Customer customer = this.buffer.getDiningCustomer().get(checkOutDto.getCustomerID());

		Calendar nowCalendar = Calendar.getInstance();
		Timestamp now = new Timestamp(nowCalendar.getTimeInMillis());
		customer.setCheckOutTime(now);

		/** update db */
		this.customerService.saveCustomer(customer);

		/** update buffer */
		this.buffer.getDiningCustomer().remove(customer.getCustomerID());

		result.setStatusOK();

		/** push socket to every client */
		customerCheckOutNotification(checkOutDto);

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
	public AjaxDTO customerCheckIn(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute CustomerDTO customerDTO) {
		AjaxDTO result = new AjaxDTO();

		/** 設定進場時間 */
		Date now = new Date();
		Timestamp time = new Timestamp(now.getTime());
		customerDTO.setCheckInTime(time);

		/** 新增資料庫 */
		Customer po = objConverter.customerDTOtoPO(customerDTO);
		customerService.saveCustomer(po);

		/** 更新buffer */
		Map<String, Customer> bufferMap = buffer.getDiningCustomer();
		bufferMap.put(po.getCustomerID(), po);

		/** push socket to every client */
		this.customerCheckInNotification(customerDTO.getFurnishID());
		this.customerInfoUpdateNotification(po);

		result.setStatusOK();
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
	private AjaxDTO saveSeatMap(HttpServletRequest request, HttpServletResponse response,
			@RequestBody SeatMapDTO seatMapDTO) {
		AjaxDTO result = new AjaxDTO();

		/** map save */
		SeatMap mapPO = objConverter.seatMapDTOtoPO(seatMapDTO);
		mapService.saveSeatMap(mapPO);

		/** furnish save */
		List<Furnish> furnishList = objConverter.furnishDTOtoPO(mapPO, seatMapDTO.getSeatPositionList());

		mapService.saveFurnish(mapPO, furnishList);

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
	private AjaxDTO getSeatMap(HttpServletRequest request, HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();

		List<SeatMap> mapList = mapService.getSeatMap();

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
	private AjaxDTO getMenu(HttpServletRequest request, HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();

		Map<String, ItemClass> menu = buffer.getMenu();

		result.setStatusOK();
		result.setData(menu);
		return result;
	}

	/**
	 * 下單
	 * 
	 * @param request
	 * @param response
	 * @param bookingDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/sendOrder" }, consumes = "application/json", produces = "application/json")
	private AjaxDTO sendOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestBody OrderDTO orderDTO) {
		AjaxDTO result = new AjaxDTO();

		/** save to db */
		List<Booking> bookingList = objConverter.bookingDTOtoPO(orderDTO);
		bookingService.saveBooking(bookingList);

		/** update buffer */
		Customer customer = this.buffer.getDiningCustomer().get(orderDTO.getCustomerId());
		customer.getBookingList().addAll(bookingList);

		/** send to client */
		this.customerInfoUpdateNotification(customer);

		result.setStatusOK();
		return result;
	}

	/**
	 * 送餐
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = { "/sendDishes" })
	private AjaxDTO sendDishes(HttpServletRequest request, HttpServletResponse response) {
		AjaxDTO result = new AjaxDTO();

		String bookingID = request.getParameter("bookingID").trim();

		Map<String, Customer> diningCustomer = buffer.getDiningCustomer();
		Customer customerData = null;
		Iterator iter = diningCustomer.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry pair = (Entry) iter.next();
			Customer customer = (Customer) pair.getValue();
			List<Booking> bookingList = customer.getBookingList();
			for (Booking booking : bookingList) {
				if (booking.getBookingID().equals(bookingID)) {
					Calendar now = Calendar.getInstance();
					Timestamp time = new Timestamp(now.getTimeInMillis());

					/** update buffer */
					booking.setDeliveryTime(time);
					booking.setIsSend(1);

					/** update db */
					bookingService.saveBooking(booking);

					customerData = customer;
					break;
				}
			}
		}
		/** update dining customer */
		// this.diningCustomerUpdate();
		this.customerInfoUpdateNotification(customerData);

		result.setStatusOK();
		return result;
	}

	/**
	 * 用餐中顧客 更新(發布socket topic)
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void diningCustomerUpdate() {
		logger.info("用餐中顧客更新，發佈WebSocket");

		Map<String, Customer> diningCustomer = buffer.getDiningCustomer();

		Map<String, CustomerDTO> diningCustomerDTO = new HashMap<String, CustomerDTO>();
		Iterator iter = diningCustomer.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry pair = (Entry) iter.next();
			String customerId = (String) pair.getKey();
			Customer customerPO = (Customer) pair.getValue();
			diningCustomerDTO.put(customerId, objConverter.customerPOtoDTO(customerPO));
		}

		this.messageTemplate.convertAndSend("/topic/customerUpdate", diningCustomerDTO);
	}

	/**
	 * specify customer update.
	 * 
	 * @param customerId
	 */
	public void customerInfoUpdateNotification(Customer customer) {
		this.messageTemplate.convertAndSend("/topic/specifyCustomerUpdate", objConverter.customerPOtoDTO(customer));
	}

	/**
	 * 進場
	 * 
	 * @param tableNumber
	 */
	public void customerCheckInNotification(String tableNumber) {
		this.messageTemplate.convertAndSend("/topic/customerCheckIn", tableNumber);
	}

	/**
	 * 出場
	 * 
	 * @param tableNumber
	 */
	public void customerCheckOutNotification(CheckOutDTO checkOutDto) {
		this.messageTemplate.convertAndSend("/topic/customerCheckOut", checkOutDto);
	}
}
