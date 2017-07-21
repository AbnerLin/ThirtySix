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
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.core.Buffer;
import com.thirtySix.dto.AjaxDTO;
import com.thirtySix.dto.CheckOutDTO;
import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.dto.OrderDTO;
import com.thirtySix.model.Booking;
import com.thirtySix.model.Customer;
import com.thirtySix.model.ItemClass;
import com.thirtySix.service.BookingService;
import com.thirtySix.service.CustomerService;
import com.thirtySix.util.ObjectConverter;

@Controller
public class IndexController {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private CustomerService customerService = null;

	@Autowired
	private BookingService bookingService = null;

	@Autowired
	private ObjectConverter objConverter = null;

	private Logger logger = Logger.getLogger(this.getClass());

	@ResponseBody
	@RequestMapping(value = { "/test_admin" })
	@Secured("ROLE_ADMIN")
	public String testADMIN() {
		if (this.buffer == null)
			System.out.println("buffer null");

		if (this.bookingService == null)
			System.out.println("service null");

		return "admin";
	}

	@ResponseBody
	@RequestMapping(value = { "/test_staff" })
	@Secured("ROLE_STAFF")
	public String testSTAFF() {
		return "staff";
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
	public AjaxDTO getDiningCustomer(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxDTO result = new AjaxDTO();
		final Map<String, Customer> diningCustomer = this.buffer
				.getDiningCustomer();

		final Map<String, CustomerDTO> diningCustomerDTO = new HashMap<String, CustomerDTO>();
		final Iterator iter = diningCustomer.entrySet().iterator();
		while (iter.hasNext()) {
			final Map.Entry pair = (Entry) iter.next();
			final String customerId = (String) pair.getKey();
			final Customer customerPO = (Customer) pair.getValue();
			diningCustomerDTO.put(customerId,
					this.objConverter.customerPOtoDTO(customerPO));
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
	public AjaxDTO customerCheckOut(final HttpServletRequest request,
			final HttpServletResponse response,
			@ModelAttribute final CheckOutDTO checkOutDto) {
		final AjaxDTO result = new AjaxDTO();

		final Customer customer = this.buffer.getDiningCustomer()
				.get(checkOutDto.getCustomerID());

		final Calendar nowCalendar = Calendar.getInstance();
		final Timestamp now = new Timestamp(nowCalendar.getTimeInMillis());
		customer.setCheckOutTime(now);

		/** update db */
		this.customerService.saveCustomer(customer);

		/** update buffer */
		this.buffer.getDiningCustomer().remove(customer.getCustomerID());

		result.setStatusOK();

		/** push socket to every client */
		// customerCheckOutNotification(checkOutDto);

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
	public AjaxDTO customerCheckIn(final HttpServletRequest request,
			final HttpServletResponse response,
			@ModelAttribute final CustomerDTO customerDTO) {
		final AjaxDTO result = new AjaxDTO();

		/** 設定進場時間 */
		final Date now = new Date();
		final Timestamp time = new Timestamp(now.getTime());
		customerDTO.setCheckInTime(time);

		/** 新增資料庫 */
		final Customer po = this.objConverter.customerDTOtoPO(customerDTO);
		this.customerService.saveCustomer(po);

		/** 更新buffer */
		final Map<String, Customer> bufferMap = this.buffer.getDiningCustomer();
		bufferMap.put(po.getCustomerID(), po);

		/** push socket to every client */
		// this.customerCheckInNotification(customerDTO.getFurnishID());
		// this.customerInfoUpdateNotification(po);

		result.setStatusOK();
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
	@Secured("ROLE_STAFF")
	public AjaxDTO getMenu(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxDTO result = new AjaxDTO();

		final Map<String, ItemClass> menu = this.buffer.getMenu();

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
	@RequestMapping(value = {
			"/sendOrder" }, consumes = "application/json", produces = "application/json")
	public AjaxDTO sendOrder(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestBody final OrderDTO orderDTO) {
		final AjaxDTO result = new AjaxDTO();

		/** save to db */
		final List<Booking> bookingList = this.objConverter
				.bookingDTOtoPO(orderDTO);
		this.bookingService.saveBooking(bookingList);

		/** update buffer */
		final Customer customer = this.buffer.getDiningCustomer()
				.get(orderDTO.getCustomerId());
		customer.getBookingList().addAll(bookingList);

		/** send to client */
		// this.customerInfoUpdateNotification(customer);

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
	public AjaxDTO sendDishes(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxDTO result = new AjaxDTO();

		final String bookingID = request.getParameter("bookingID").trim();

		final Map<String, Customer> diningCustomer = this.buffer
				.getDiningCustomer();
		Customer customerData = null;
		final Iterator iter = diningCustomer.entrySet().iterator();
		while (iter.hasNext()) {
			final Map.Entry pair = (Entry) iter.next();
			final Customer customer = (Customer) pair.getValue();
			final List<Booking> bookingList = customer.getBookingList();
			for (final Booking booking : bookingList) {
				if (booking.getBookingID().equals(bookingID)) {
					final Calendar now = Calendar.getInstance();
					final Timestamp time = new Timestamp(now.getTimeInMillis());

					/** update buffer */
					booking.setDeliveryTime(time);
					booking.setIsSend(1);

					/** update db */
					this.bookingService.saveBooking(booking);

					customerData = customer;
					break;
				}
			}
		}
		/** update dining customer */
		// this.diningCustomerUpdate();
		// this.customerInfoUpdateNotification(customerData);

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
		this.logger.info("用餐中顧客更新，發佈WebSocket");

		final Map<String, Customer> diningCustomer = this.buffer
				.getDiningCustomer();

		final Map<String, CustomerDTO> diningCustomerDTO = new HashMap<String, CustomerDTO>();
		final Iterator iter = diningCustomer.entrySet().iterator();
		while (iter.hasNext()) {
			final Map.Entry pair = (Entry) iter.next();
			final String customerId = (String) pair.getKey();
			final Customer customerPO = (Customer) pair.getValue();
			diningCustomerDTO.put(customerId,
					this.objConverter.customerPOtoDTO(customerPO));
		}

		// this.messageTemplate.convertAndSend("/topic/customerUpdate",
		// diningCustomerDTO);
	}

	// /**
	// * specify customer update.
	// *
	// * @param customerId
	// */
	// public void customerInfoUpdateNotification(final Customer customer) {
	// this.messageTemplate.convertAndSend("/topic/specifyCustomerUpdate",
	// this.objConverter.customerPOtoDTO(customer));
	// }

	// /**
	// * 進場
	// *
	// * @param tableNumber
	// */
	// public void customerCheckInNotification(final String tableNumber) {
	// this.messageTemplate.convertAndSend("/topic/customerCheckIn",
	// tableNumber);
	// }
	//
	// /**
	// * 出場
	// *
	// * @param tableNumber
	// */
	// public void customerCheckOutNotification(final CheckOutDTO checkOutDto) {
	// this.messageTemplate.convertAndSend("/topic/customerCheckOut",
	// checkOutDto);
	// }

}
