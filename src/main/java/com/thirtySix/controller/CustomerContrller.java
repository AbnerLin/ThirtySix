package com.thirtySix.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.dto.AjaxRDTO;
import com.thirtySix.dto.CheckInInfoQDTO;
import com.thirtySix.model.Customer;
import com.thirtySix.model.Furnish;
import com.thirtySix.service.CustomerService;
import com.thirtySix.service.MapService;
import com.thirtySix.webSocket.WebSocketUtil;

@Controller
@RequestMapping(value = { "/customer" })
public class CustomerContrller {

	@Autowired
	private CustomerService customerService = null;

	@Autowired
	private MapService mapService = null;

	@Autowired
	private WebSocketUtil webSocket = null;

	/**
	 * Get dining customer.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getDiningCustomer" })
	public AjaxRDTO getDiningCustomer(final HttpServletRequest request,
			final HttpServletResponse response) {

		final AjaxRDTO result = new AjaxRDTO();

		final Map<String, Customer> diningCustomer = this.customerService
				.findDiningCustomer();

		result.setStatusOK();
		result.setData(diningCustomer);

		return result;
	}

	/**
	 * Customer check in.
	 * 
	 * @param request
	 * @param response
	 * @param checkInInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/checkIn" })
	public AjaxRDTO checkIn(final HttpServletRequest request,
			final HttpServletResponse response,
			@ModelAttribute final CheckInInfoQDTO checkInInfo) {

		final AjaxRDTO result = new AjaxRDTO();

		/** Find whether this furnish is using ? */
		final boolean isFurnishUsed = this.customerService.findDiningCustomer()
				.entrySet().stream().map(map -> map.getValue())
				.filter(customer -> customer.getFurnish()
						.equals(checkInInfo.getFurnishId()))
				.findAny().isPresent();

		if (isFurnishUsed) {
			result.setStatusFail();
			result.setMessage("Furnish are using...");
			return result;
		}

		/** Find whether the furnish is exist in seatmap */
		final Furnish furnish = this.mapService.findAllSeatMap().entrySet()
				.stream().map(map -> map.getValue())
				.flatMap(value -> value.getFurnishList().stream())
				.filter(usingFurnish -> usingFurnish.getFurnishID()
						.equals(checkInInfo.getFurnishId()))
				.findAny().orElse(null);

		if (furnish == null) {
			result.setStatusFail();
			result.setMessage("Furnish not found.");
			return result;
		}

		final Customer customer = new Customer();
		customer.setCustomerName(checkInInfo.getCustomerName());
		customer.setPeopleCount(checkInInfo.getPeopleCount());
		customer.setPhoneNumber(checkInInfo.getCustomerPhone());
		customer.setFurnish(furnish);

		this.customerService.checkInCustomer(customer);
		this.webSocket.customerCheckIn(customer);

		return result;
	}

	/**
	 * Customer check out.
	 * 
	 * @param request
	 * @param response
	 * @param customerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/checkOut" })
	public AjaxRDTO checkOut(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam final String customerId) {

		final AjaxRDTO result = new AjaxRDTO();

		return result;
	}
}
